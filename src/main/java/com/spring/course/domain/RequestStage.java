package com.spring.course.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.spring.course.domain.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "request_stage")
public class RequestStage implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "realization_date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date realizationDate;
	
	@Column(columnDefinition = "text")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "request_id", nullable = false)
	private Request request;
	
	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private User owner;
	
	@Column(length = 12, nullable = false)
	@Enumerated(EnumType.STRING)
	private RequestState state;
}
