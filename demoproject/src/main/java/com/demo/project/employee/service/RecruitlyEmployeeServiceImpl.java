package com.demo.project.employee.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.project.employee.exceptions.InvalidCredentialsException;
import com.demo.project.employee.model.RecruitlyEmployee;
import com.demo.project.employee.repository.RecruitlyEmployeeRepository;



@Service
public class RecruitlyEmployeeServiceImpl implements RecruitlyEmployeeService {

	@Autowired
	private RecruitlyEmployeeRepository employeeRepository;

	@Override
	public List<RecruitlyEmployee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public RecruitlyEmployee saveEmployee(RecruitlyEmployee employee) {
		return this.employeeRepository.save(employee);
	}

	@Override
	public RecruitlyEmployee getEmployeeById(int id) {
		Optional<RecruitlyEmployee> optional = employeeRepository.findById(id);
		RecruitlyEmployee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return employee;
	}

	@Override
	public void deleteEmployeeById(int id) {
		this.employeeRepository.deleteById(id);
	}

	@Override
	public Page<RecruitlyEmployee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.employeeRepository.findAll(pageable);
	}

	@Override
	public boolean validateUser(String email, String password) throws InvalidCredentialsException{
		// TODO Auto-generated method stub
		
	Optional<RecruitlyEmployee> recruitemployee=	employeeRepository.findAll().stream().filter(employee->employee.getEmail().equals(email)&&
				employee.getPassword().equals(password)).map(emp->emp).findFirst();
	
	if(recruitemployee.isEmpty())
		throw new InvalidCredentialsException();
	else
		return true;
	
	
	}

	@Override
	public List<RecruitlyEmployee> getEmployeesFiltered(String filter) {
		// TODO Auto-generated method stub
		return employeeRepository.findAll().stream().filter(employee->employee.getName().equals(filter)||employee.getDepartment().equals(filter))
		.map(emp->emp).collect(Collectors.toList());
		
	}

	
}
