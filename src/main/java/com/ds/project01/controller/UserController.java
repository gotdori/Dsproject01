package com.ds.project01.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@PostMapping("/user/save")
	public String user_save(UserDto dto, HobbyDataDto hdDto, Model model) {
		UserEntity entity = UserEntity.toUserEntity(dto);
		String splitChoice = hdDto.getHobbyCd();
		String[] ArraysStr = splitChoice.split(",");
		
		service.insert(entity);
		HobbyDataDto hobbyDataDto = new HobbyDataDto();
		for(String s : ArraysStr) {
			hobbyDataDto.setHobbyCd(s);
			hobbyDataDto.setUserId(dto.getUserId());
			HobbyDataEntity hobbyDataEntity = HobbyDataEntity.toHobbyDataEntity(hobbyDataDto);
			service.HobbyDataInsert(hobbyDataEntity);
		}
		return "redirect:/";
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
		
		List<HobbyDataEntity> a = service.HobbyDataView(userId);
		for (int i = 0; i < a.size(); i++) {
			model.addAttribute("취미"+i, a.get(i).getHobbyEntity().getHobbyCd());;
			System.out.println(a.get(i).getHobbyEntity().getHobbyCd());
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
	public String admin_delete(UserDto dto) {
		UserEntity entity = UserEntity.toUserEntity(dto);
		service.delete(entity);
		return "redirect:/";
	}


}