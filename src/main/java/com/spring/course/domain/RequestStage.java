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
public class RequestStage {
	
	private Long id;
	private Date realizationDate;
	private String description;
	private Request request;
	private User user;
	private RequestState state;
}
