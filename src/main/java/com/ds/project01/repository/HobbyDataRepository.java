package com.ds.project01.repository;


import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ds.project01.domain.HobbyDataEntity;


public interface HobbyDataRepository extends JpaRepository<HobbyDataEntity, String>{

	// findByUser참조받는메소드(맨앞대문자)_참조받는테이블Dto명(맨앞대문자)
	List<HobbyDataEntity> findByUserEntiy_UserId(String userID);
	
    @Transactional
    @Modifying
	@Query(value = "delete from hobby_data_tb where user_id=:id", nativeQuery = true)
	void deleteHBdata(@Param(value = "id") String id);
    
    void deleteByUserEntiy_UserId(String userId);
}