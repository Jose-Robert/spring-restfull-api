package com.spring.course.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.course.domain.Request;
import com.spring.course.domain.RequestStage;
import com.spring.course.model.PageModel;
import com.spring.course.model.PageRequestModel;
import com.spring.course.service.RequestService;
import com.spring.course.service.RequestStageService;

@RestController
@RequestMapping(value = "requests")
public class RequestResource {

	@Autowired
	private RequestService requestService;
	
	@Autowired 
	private RequestStageService stageService;
	
	@PostMapping 
	public ResponseEntity<Request> save(@RequestBody Request request){
		Request createdRequest =  requestService.save(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Request> update(@PathVariable(name = "id") Integer id, @RequestBody Request request){
		request.setId(id);
		
		Request updateRequest = requestService.save(request);
		return ResponseEntity.ok(updateRequest);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Request> searchById(@PathVariable(name = "id") Integer id){
		Request request = requestService.searchById(id);
		return ResponseEntity.ok(request);
	}
	
	@GetMapping
	public ResponseEntity<PageModel<Request>> listAll(
			@RequestParam(value = "page", defaultValue="0") int page,
			@RequestParam(value = "size", defaultValue="5") int size) {
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listAllOnLazyMode(pr);
		return ResponseEntity.ok(pm);
	}
	
	@GetMapping("/{id}/request-satges")
	public ResponseEntity<PageModel<RequestStage>> listAllStagesById(
			@PathVariable(name = "id") Integer id,
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="size", defaultValue="5") int size){
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<RequestStage> pm = stageService.listAllByRequestIdOnLazyMode(id, pr);
		return ResponseEntity.ok(pm);
	}
}
