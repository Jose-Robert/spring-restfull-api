package com.spring.course.domain;

import java.util.ArrayList;
import java.util.List;

import com.spring.course.domain.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
	
	private Long id;
	private String name;
	private String email;
	private String password;
	private Role role;
	private List<Request> requests = new ArrayList<Request>();
	private List<RequestStage> stages =  new ArrayList<RequestStage>();

}