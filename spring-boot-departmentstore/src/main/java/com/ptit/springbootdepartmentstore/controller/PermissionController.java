package com.ptit.springbootdepartmentstore.controller;

import java.util.List;

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

import com.ptit.springbootdepartmentstore.dto.PermissionDTO;
import com.ptit.springbootdepartmentstore.service.PermissionService;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;

	@GetMapping
	public List<PermissionDTO> getPermissionList() {
		return permissionService.getListPermission();
	}

	@GetMapping("/{id}")
	public ResponseEntity<PermissionDTO> getPermission(@PathVariable int id) {
		PermissionDTO permissionDTO = permissionService.getPermission(id);
		if (permissionDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(permissionDTO);
	}

	@PostMapping
	public ResponseEntity<Void> savePermission(@RequestBody PermissionDTO permissionDTO) {
		permissionService.savePermission(permissionDTO);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updatePermission(@PathVariable int id, @RequestBody PermissionDTO permissionDTO) {
		if (permissionDTO.getId() != id) {
			return ResponseEntity.badRequest().build();
		}
		permissionService.updatePermission(permissionDTO);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePermission(@PathVariable int id) {
		try {
			permissionService.deletePermission(id);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}