package com.spring.course.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.course.domain.User;
import com.spring.course.dto.UserLoginDTO;
import com.spring.course.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user){
		User createdUser = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id") Integer id, @RequestBody User user){
		user.setId(id);
		User updateUser = userService.update(user);
		return ResponseEntity.ok(updateUser);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> searchById(@PathVariable("id") Integer id ){
		User user = userService.searchById(id);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> listAll(){
		List<User> users = userService.listAll();
		return ResponseEntity.ok(users);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLoginDTO user){
		User loggedUser = userService.login(user.getEmail(), user.getPassword());
		return ResponseEntity.ok(loggedUser);
	}
	
}
