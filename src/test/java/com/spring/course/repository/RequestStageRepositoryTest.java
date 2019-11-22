package com.spring.course.repository;

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
import com.spring.course.domain.RequestStage;
import com.spring.course.domain.User;
import com.spring.course.domain.enums.RequestState;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestStageRepositoryTest {
	
	@Autowired
	private RequestStageRepository requestStageRepository;
	
	@BeforeClass
	@Test
	public void saveTest() {
		User owner = new User();
		owner.setId(1);
		
		Request request = new Request();
		request.setId(1);
		
		RequestStage stage = new RequestStage(null, new Date(), "Foi comprado um notebook", request, owner, RequestState.CLOSED);
		RequestStage createdStage = requestStageRepository.save(stage);
		
		assertThat(createdStage.getId()).isEqualTo(1);
	}
	
	@Test
	public void searchByIdTest() {
		Optional<RequestStage> result = requestStageRepository.findById(1);
		RequestStage stage = result.get();
		
		assertThat(stage.getDescription()).isEqualTo("Foi comprado um notebook");
	}
	
	@Test
	public void listByRequestIdTest() {
		List<RequestStage> stages = requestStageRepository.findAllByRequestId(1);
		
		assertThat(stages.size()).isEqualTo(1);
	}
}
