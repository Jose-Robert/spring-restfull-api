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
import com.spring.course.domain.User;
import com.spring.course.dto.UserLoginDTO;
import com.spring.course.model.PageModel;
import com.spring.course.model.PageRequestModel;
import com.spring.course.service.RequestService;
import com.spring.course.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResource {

	@Autowired
	private UserService userService;

	@Autowired
	private RequestService requestService;

	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) {
		User createdUser = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id") Integer id, @RequestBody User user) {
		user.setId(id);
		User updateUser = userService.update(user);
		return ResponseEntity.ok(updateUser);

	}

	@GetMapping("/{id}")
	public ResponseEntity<User> searchById(@PathVariable("id") Integer id) {
		User user = userService.searchById(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping
	public ResponseEntity<PageModel<User>> listAll(
			@RequestParam(value = "page", defaultValue="0") int page,
			@RequestParam(value = "size", defaultValue="5") int size) {
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<User> pm = userService.listAllOnLazyMode(pr);
		return ResponseEntity.ok(pm);
	}

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLoginDTO user) {
		User loggedUser = userService.login(user.getEmail(), user.getPassword());
		return ResponseEntity.ok(loggedUser);
	}

	@GetMapping("/{id}/requests")
	public ResponseEntity<PageModel<Request>> listAllRequestsById(
			@PathVariable(name = "id") Integer id,
			@RequestParam(value="page", defaultValue="0")int page,
			@RequestParam(value="size", defaultValue="5")int size) {
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listAllByOwnerIdOnLazyMode(id, pr);
		return ResponseEntity.ok(pm);
	}

}
