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
		if (searchKeyword == null) { //검색 키워드 없으면
			adminlist = service.adminList(); //전체리스트 출력
		} else {					//있으면
			adminlist = service.seachNm(searchKeyword); //검색한 리스트 출력
		}
		model.addAttribute("hobbyList", hobbylist);
		model.addAttribute("deptList", deptlist);
		model.addAttribute("adminList", adminlist);
		return "/admin/list";
	}
	
	//검색한 리스트 화면을 고정한 상태로 view를 보이고 싶었음. 그래서 비동기인 ajax를 사용하기 위해 json형태로 데이터를 받으니 @ResponseBody
	@ResponseBody
	@GetMapping("/admin/view")
	HashMap<String, Object> userView(UserDto userDto, Model model){ //ajax로 보낸  userID 정보를 userDto에 담아서 받아옴 
		HashMap<String, Object> map = new HashMap<>(); //attribute로 html 태그 안으로 데이터를 넘길 때는 Model, script 태그 안에는 map
		List<DeptEntity> deptlist = service.deptList(); // 부서리스트 받음
		UserEntity entity = UserEntity.toUserEntity(userDto); //유저dto를 엔티티로 변환
		UserEntity tempentity = service.view(entity); //엔티티(유저ID)를 보내서 뷰 정보를 받음
		String userId =entity.getUserId();
		
		List<HobbyDataEntity> hobbyDataList = service.HobbyDataView(userId); //유저ID로 취미데이터 리스트도 불러옴
		for (int i = 0; i < hobbyDataList.size(); i++) {
			map.put("userHobbyChoice"+i, hobbyDataList.get(i).getHobbyEntity().getHobbyCd()); //각각 여러 취미데이터를 다른 아이디로 저장
		}
		map.put("deptList", deptlist); //받아온 부서 데이터 저장 후 script에서 불러낼것
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