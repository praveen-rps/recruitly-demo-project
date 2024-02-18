package com.demo.project.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.project.employee.model.RecruitlyEmployee;

public interface RecruitlyEmployeeRepository extends JpaRepository<RecruitlyEmployee,Integer> {
	
	

}
