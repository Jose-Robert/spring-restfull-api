package com.spring.course.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.course.domain.RequestStage;

@Repository
public interface RequestStageRepository extends JpaRepository<RequestStage, Integer>{
	
	public List<RequestStage> findAllByRequestId(Integer id);
}
