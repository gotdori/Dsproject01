package com.ds.project01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ds.project01.domain.DeptEntity;
import com.ds.project01.domain.UserEntity;
import com.ds.project01.repository.DeptRepository;
import com.ds.project01.repository.UserRepository;



@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private DeptRepository deptRepo;
	
	public void insert(UserEntity entity) {
		userRepo.save(entity);
	}
	
	public void update(UserEntity entity) {
		userRepo.save(entity);
	}
	
	public void delete(UserEntity entity) {
		userRepo.delete(entity);
	}
	
	public UserEntity view(UserEntity entity) {
		return userRepo.findByUserId(entity.getUserId());
	}
	
	public List<DeptEntity> DeptList(){
		return deptRepo.findAll();
	}
}
