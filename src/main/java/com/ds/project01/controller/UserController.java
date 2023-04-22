package com.ds.project01.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.project01.domain.DeptEntity;
import com.ds.project01.domain.HobbyDataEntity;
import com.ds.project01.domain.HobbyEntity;
import com.ds.project01.domain.UserEntity;
import com.ds.project01.dto.HobbyDataDto;
import com.ds.project01.dto.UserDto;
import com.ds.project01.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/user/write")
	public String user_write(Model model) {
		List<DeptEntity> deptlist = service.deptList();
		List<HobbyEntity> hobbylist = service.hobbyList();

		model.addAttribute("deptList", deptlist);
		model.addAttribute("hobbyList", hobbylist);
		return "/user/write";
	}

	@PostMapping("/user/save") //내 코드 기준으로 userId가 있으면 update, 없으면 insert
	public String user_save(UserDto dto, Model model, HobbyDataDto hdDto) {
		service.HobbyDataDelete(dto.getUserId());	//유저취미 업데이트하기 위해 취미 데이터를 먼저 싹 지우고 밑에서 다시 추가함
		UserEntity entity = UserEntity.toUserEntity(dto); //dto를 entity로 변환
		String splitChoice = hdDto.getHobbyCd();	//dto로 취미코드 1,2,3 받은거 저장
		String[] ArraysStr = splitChoice.split(",");
		
		service.insert(entity); //유저 정보가 먼저 들어가 있어야 참조 하고있는 취미데이터에도 정보를 넣을 수 있음 
		HobbyDataDto hobbyDataDto = new HobbyDataDto();	//빈 Dto 만들어서 취미데이터 넣어줌
		for(String s : ArraysStr) {
			hobbyDataDto.setHobbyCd(s);
			hobbyDataDto.setUserId(dto.getUserId());
			HobbyDataEntity hobbyDataEntity = HobbyDataEntity.toHobbyDataEntity(hobbyDataDto); //Dto를 entity로 변환
			service.HobbyDataInsert(hobbyDataEntity); //취미데이터도 저장
		}
		
		return "redirect:/user/write";
	}

	@GetMapping("/admin/list")
	public String admin_list(UserDto dto, Model model, String searchKeyword) {
		List<UserEntity> adminlist = new ArrayList<>();
		List<DeptEntity> deptlist = service.deptList();
		List<HobbyEntity> hobbylist = service.hobbyList();
		if (searchKeyword == null) {
			adminlist = service.adminList();
		} else {
			adminlist = service.seachNm(searchKeyword);
		}
		model.addAttribute("hobbyList", hobbylist);
		model.addAttribute("deptList", deptlist);
		model.addAttribute("adminList", adminlist);
		return "/admin/list";
	}
	
	@ResponseBody
	@GetMapping("/admin/view")
	HashMap<String, Object> userView(UserDto userDto, Model model){
		HashMap<String, Object> map = new HashMap<>();
		List<DeptEntity> deptlist = service.deptList();
		UserEntity entity = UserEntity.toUserEntity(userDto);
		UserEntity tempentity = service.view(entity);
		String userId =entity.getUserId();
		
		List<HobbyDataEntity> hobbyDataList = service.HobbyDataView(userId);
		for (int i = 0; i < hobbyDataList.size(); i++) {
			map.put("userHobbyChoice"+i, hobbyDataList.get(i).getHobbyEntity().getHobbyCd());
			System.out.println(hobbyDataList.get(i).getHobbyEntity().getHobbyCd());
		}
		map.put("deptList", deptlist);
		map.put("getUerId",tempentity.getUserId());
		map.put("getUserNm",tempentity.getUserNm());
		map.put("getUserEmlAddr",tempentity.getUserEmlAddr());
		map.put("getUserTelno",tempentity.getUserTelno());
		map.put("getUserDeptNo",tempentity.getDeptEntity().getDeptNo());
		map.put("getUserAprvYn",tempentity.getUserAprvYn());
		
		return map;
	}
	
	@PostMapping("/admin/delete")
	public String user_delete(UserDto dto) {
		UserEntity entity = UserEntity.toUserEntity(dto);
		
		service.HobbyDataDelete(dto.getUserId());
		service.delete(entity);
		return "redirect:/admin/list";
	}
	
}