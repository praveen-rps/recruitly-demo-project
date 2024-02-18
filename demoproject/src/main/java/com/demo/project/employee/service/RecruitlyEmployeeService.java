package com.demo.project.employee.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.demo.project.employee.exceptions.InvalidCredentialsException;
import com.demo.project.employee.model.RecruitlyEmployee;

public interface RecruitlyEmployeeService {
	
	public boolean validateUser(String email,String password) throws InvalidCredentialsException;
	List<RecruitlyEmployee> getAllEmployees();
	RecruitlyEmployee saveEmployee(RecruitlyEmployee employee);
	RecruitlyEmployee getEmployeeById(int id);
	void deleteEmployeeById(int id);
	Page<RecruitlyEmployee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	public List<RecruitlyEmployee> getEmployeesFiltered(String filter);
}
