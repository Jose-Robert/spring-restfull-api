package com.spring.course.repository.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.course.domain.User;
import com.spring.course.domain.enums.Role;
import com.spring.course.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	@BeforeClass
	public void saveTest() {
		User user = new User(null, "Robert", "robert@gmail.com", "admin", "83986670767", Role.ADMINISTRATOR, null, null);
		User createdUser = userRepository.save(user);
		
		assertThat(createdUser.getId()).isEqualTo(1L);
	}
	
	@Test
	public void updateTest() {
		User user = new User(1L, "José Robert", "robert@gmail.com", "admin", "83986670767", Role.ADMINISTRATOR, null, null);
		User updateUser = userRepository.save(user);
		
		assertThat(updateUser.getName()).isEqualTo("José Robert");
	}
	
	@Test
	public void searchByIdTest() {
		Optional<User> result = userRepository.findById(1L);
		User user = result.get();
		
		assertThat(user.getPassword()).isEqualTo("admin");
	}
	
	@Test
	public void listTest() {
		List<User> users = userRepository.findAll();
		
		assertThat(users.size()).isEqualTo(1);
	}
	
	@Test
	public void loginTest() {
		Optional<User> result = userRepository.login("robert@gmail.com", "admin");
		User loggerUser = result.get();
		
		assertThat(loggerUser.getId()).isEqualTo(1);
	}
}
