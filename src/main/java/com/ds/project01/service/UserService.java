package com.ds.project01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ds.project01.domain.DeptEntity;
import com.ds.project01.domain.HobbyDataEntity;
import com.ds.project01.domain.HobbyEntity;
import com.ds.project01.domain.UserEntity;
import com.ds.project01.repository.DeptRepository;
import com.ds.project01.repository.HobbyDataRepository;
import com.ds.project01.repository.HobbyRepository;
import com.ds.project01.repository.UserRepository;



@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private DeptRepository deptRepo;
	
	@Autowired
	private HobbyRepository hobbyRepo;
	
	@Autowired
	private HobbyDataRepository hobbyDataRepo;
	
	
	public List<UserEntity> adminList(){
		return userRepo.findAll();
	}
	
	public List<UserEntity> seachNm(String searchKeyword){
		return userRepo.findByUserNmContaining(searchKeyword);
	}
	
	public void insert(UserEntity entity) {
		userRepo.save(entity);
	}
	
	public void delete(UserEntity entity) {
		
		userRepo.delete(entity);
	}
	
	public UserEntity view(UserEntity entity) {
		return userRepo.findByUserId(entity.getUserId());
	}
	
	public List<DeptEntity> deptList(){
		return deptRepo.findAll();
	}
	
	public List<HobbyEntity> hobbyList(){
		return hobbyRepo.findAll();
	}
	
	public void HobbyDataInsert(HobbyDataEntity entity) {
		hobbyDataRepo.save(entity);
	}
	
	public List<HobbyDataEntity> HobbyDataView(String userId) {
		return hobbyDataRepo.findByUserEntity_UserId(userId);
	}
	
	public void HobbyDataDelete(String userId) {
		hobbyDataRepo.deleteByUserEntity_UserId(userId);
	}
}
