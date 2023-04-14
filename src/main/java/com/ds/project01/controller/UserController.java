package com.ds.project01.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ds.project01.domain.DeptEntity;
import com.ds.project01.domain.UserEntity;
import com.ds.project01.dto.UserDto;
import com.ds.project01.service.UserService;


@Controller
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/user/write")
	public String user_write(Model model) {
		List<DeptEntity> list = service.DeptList();
		model.addAttribute("deptList", list);
		return "/user/write";
	}
	
	@PostMapping("/user/save")
	public String user_save(UserDto dto, Model model) {
		UserEntity entity = UserEntity.toUserEntity(dto);
		service.insert(entity);
		System.out.println(dto);
		return "redirect:/user/write";
	}
	
	@PostMapping("/user/update")
	public String user_update(UserDto dto, Model model) {
		UserEntity entity = UserEntity.toUserEntity(dto);
		UserEntity tempentity = service.view(entity);
		model.addAttribute("UserEntityupdate", tempentity);
		return "/user/write";
	}
	
	
}


