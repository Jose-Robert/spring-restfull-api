package com.spring.course.domain;

import java.util.Date;

import com.spring.course.domain.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request {
	
	private Long id;
	private String subject;
	private String description;
	private Date creationDate;
	private RequestStage requestStage;
	private User user;
	private RequestState states;
	
}
