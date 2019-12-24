package com.spring.course.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.course.domain.RequestStage;
import com.spring.course.domain.enums.RequestState;
import com.spring.course.exception.NotFoundException;
import com.spring.course.model.PageModel;
import com.spring.course.model.PageRequestModel;
import com.spring.course.repository.RequestRepository;
import com.spring.course.repository.RequestStageRepository;

@Service
public class RequestStageService {

	@Autowired
	private RequestStageRepository requestStageRepository;

	@Autowired
	private RequestRepository requestRepository;

	public RequestStage save(RequestStage stage) {
		stage.setRealizationDate(new Date());

		RequestStage createdStage = requestStageRepository.save(stage);
		Integer requestId = stage.getRequest().getId();
		RequestState state = stage.getState();

		requestRepository.updateStatus(requestId, state);
		return createdStage;
	}

	public RequestStage searchById(Integer id) {
		Optional<RequestStage> result = requestStageRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("There are not request stage with id = " + id));

	}

	public List<RequestStage> listAllByRequestId(Integer requestId) {
		List<RequestStage> stages = requestStageRepository.findAllByRequestId(requestId);
		return stages;
	}
	
	public PageModel<RequestStage> listAllByRequestIdOnLazyMode(Integer requestId, PageRequestModel pr) {
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<RequestStage> page = requestStageRepository.findAllByRequestId(requestId, pageable);
		
		PageModel<RequestStage> pm = new PageModel<>((int) page.getTotalElements(), page.getSize(), page.getTotalPages(),
				page.getContent());
		return pm;
	}

}
