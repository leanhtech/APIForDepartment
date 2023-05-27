package com.ptit.springbootdepartmentstore.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ptit.springbootdepartmentstore.dto.UserDTO;
import com.ptit.springbootdepartmentstore.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public List<UserDTO> getAllUsers() {
		return userService.getAllUser();
	}

	@GetMapping("/{id}")
	public UserDTO getUserById(@PathVariable int id) {
		return userService.getUser(id);
	}

	@PostMapping("/")
	public UserDTO createUser(@RequestBody UserDTO userDTO) {
		return userService.createUser(userDTO);
	}

	@PutMapping("/{id}")
	public UserDTO updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
		userDTO.setId(id);
		return userService.updateUser(userDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam String name, @RequestParam String password) {
		try {
			UserDTO userDTO = userService.authenticate(name, password);
			// TODO: create session and set user as logged in
			return ResponseEntity.ok(userDTO);
		} catch (EntityNotFoundException | IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout() {
		// TODO: destroy session and log out user
		return ResponseEntity.ok().build();
	}
}