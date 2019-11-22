package com.spring.course.repository.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.course.domain.Request;
import com.spring.course.domain.User;
import com.spring.course.domain.enums.RequestState;
import com.spring.course.repository.RequestRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestRepositoryTest {
	
	@Autowired
	private RequestRepository requestRepository;
	
	@BeforeClass
	@Test
	public void saveTest() {
		User owner = new User();
		owner.setId(1L);
		
		Request resquest =  new Request(null, "Novo Notebook Dell", "Pretendo Obter Notebook Dell", new Date(), null, owner, RequestState.OPEN);
		Request createdRequest = requestRepository.save(resquest);
		
		assertThat(createdRequest.getId()).isEqualTo(1L);
	}
	
	@Test
	public void updateTest() {
		User owner = new User();
		owner.setId(1L);
		
		Request resquest =  new Request(1L, "Novo Notebook Dell", "Pretendo Obter Notebook Dell, de 8GB RAM", new Date(), null, owner, RequestState.OPEN);
		Request updateRequest = requestRepository.save(resquest);
		
		assertThat(updateRequest.getDescription()).isEqualTo("Pretendo Obter Notebook Dell, de 8GB RAM");
	}
	
	@Test
	public void searchByIdTest() {
		Optional<Request> result = requestRepository.findById(1L);
		Request resquest = result.get();
		
		assertThat(resquest.getSubject()).isEqualTo("Novo Notebook Dell");
	}
	
	@Test
	public void listTest() {
		List<Request> requests = requestRepository.findAll();
		
		assertThat(requests.size()).isEqualTo(1);
	}
	
	@Test
	public void ListByOwnerIdTest() {
		List<Request> requests = requestRepository.findAllByOwnerId(1L);
		
		assertThat(requests.size()).isEqualTo(1);
	}
	
	@Test
	public void updateStatusTest() {
		int affectedRows = requestRepository.updateStatus(1L, RequestState.IN_PROGRESS);
		
		assertThat(affectedRows).isEqualTo(1);
	}
}
