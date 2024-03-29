package com.demo.project.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.project.employee.exceptions.InvalidCredentialsException;
import com.demo.project.employee.model.RecruitlyEmployee;
import com.demo.project.employee.service.RecruitlyEmployeeService;

@Controller
public class RecruitlyEmployeeController {
	

	@Autowired
	//private EmployeeService employeeService;
	private RecruitlyEmployeeService employeeService;
	
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "login";
	}
	
	
	// display list of employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "name", "asc", model);		
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		//Employee employee = new Employee();
		RecruitlyEmployee employee = new RecruitlyEmployee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") RecruitlyEmployee employee) {
		// save employee to database
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") int id, Model model) {
		
		// get employee from the service
		//Employee employee = employeeService.getEmployeeById(id);
		RecruitlyEmployee employee = employeeService.getEmployeeById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") int id) {
		
		// call delete employee method 
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 3;
		
		Page<RecruitlyEmployee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<RecruitlyEmployee> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEmployees", listEmployees);
		return "index";
	}
	
	
	@PostMapping("/validate")
	public String validate(@RequestParam("email")String email, @RequestParam("password")String password, Model model)
	throws InvalidCredentialsException{
		boolean isValidUser=false;
		if(employeeService.validateUser(email, password))
			return viewHomePage(model);
		else
			return "login";
	}
	
	/*
	@GetMapping("/filteremp")
    public String getAllEmployees(@RequestParam("filter") String filter, Model model) {
        List<RecruitlyEmployee> employees;
        System.out.println(filter);
        if (filter != null && !filter.isEmpty()) {
            // Filter employees by name or department
            employees = employeeService.getEmployeesFiltered(filter);
            System.out.println(employees);
        } else {
            // If no filter provided, get all employees
            employees = employeeService.getAllEmployees();
        }
        model.addAttribute("listEmployees", employees);
        return viewHomePage(model);
    }
	
	*/
}
