package tn.esprit.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.entites.User;
import tn.esprit.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("add")
	public ResponseEntity<User> addUser(@Valid @RequestBody User entity) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.add(entity));
	}

	@GetMapping("get-all")
	public ResponseEntity<List<User>> getAll() {
		List<User> Users = userService.getAll();

		return ResponseEntity.status(HttpStatus.OK).body(Users);
	}

	@GetMapping("get-by-id/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.getById(id));
	}

	@PutMapping("edit")
	public ResponseEntity<User> editUser(@RequestBody User entity) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.edit(entity));
	}

	@DeleteMapping("delete-by-id/{id}")
	public ResponseEntity<Boolean> deleteUserById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.delete(id));
	}

}
