package com.demo.project.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="recruitemployee")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class RecruitlyEmployee {

	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	private String department;
	private String status;
	private String password;
	public RecruitlyEmployee(String name, String email, String department, String status, String password) {
		super();
		this.name = name;
		this.email = email;
		this.department = department;
		this.status = status;
		this.password=password;
	}
	
}
