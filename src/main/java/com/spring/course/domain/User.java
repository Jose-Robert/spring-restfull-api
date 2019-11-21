package com.spring.course.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.spring.course.domain.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "user")
public class User {
	
	@Id
	private Long id;
	
	@Column(length = 75, nullable = false)
	private String name;
	
	@Column(length = 75, nullable = false, unique = true)
	private String email;
	
	@Column(length = 100, nullable = false)
	private String password;
	
	@Column(length = 20)
	private String phone;
	
	private Role role;
	private List<Request> requests = new ArrayList<Request>();
	private List<RequestStage> stages =  new ArrayList<RequestStage>();

}
