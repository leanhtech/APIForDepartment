package com.ptit.springbootdepartmentstore.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.PermissionDTO;
import com.ptit.springbootdepartmentstore.entity.Permission;
import com.ptit.springbootdepartmentstore.repository.PermissionRepository;

@Service
@Transactional
public class PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	public PermissionDTO convertToPermissionDTO(Permission permission) {
		PermissionDTO permissionDTO = new PermissionDTO();
		permissionDTO.setId(permission.getId());
		permissionDTO.setPermissionName(permission.getName());
		return permissionDTO;
	}

	public PermissionDTO convertToListPermissionDTO(Permission permission) {
		PermissionDTO permissionDTO = new PermissionDTO();
		permissionDTO.setId(permission.getId());
		permissionDTO.setPermissionName(permission.getName());
		return permissionDTO;
	}

	public List<PermissionDTO> convertToLisPermissionDTO(List<Permission> permissions) {
		return permissions.stream().map(this::convertToPermissionDTO).collect(Collectors.toList());
	}

	public Permission convertToPermission(PermissionDTO permissionDTO) {
		Permission permission = new Permission();
		permission.setName(permissionDTO.getPermissionName());
		return permission;
	}

	public PermissionDTO getPermission(int id) {
		Permission permission = permissionRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Permission not found"));
		return convertToPermissionDTO(permission);
	}

	public List<PermissionDTO> getListPermission() {
		return convertToLisPermissionDTO(permissionRepository.findAll());
	}

	@Transactional(rollbackOn = Exception.class)
	public void savePermission(PermissionDTO permissionDTO) {
		Permission permission = convertToPermission(permissionDTO);
		permissionRepository.save(permission);
	}

	@Transactional(rollbackOn = Exception.class)
	public void updatePermission(PermissionDTO permissionDTO) {
		Permission permission = permissionRepository.findById(permissionDTO.getId())
				.orElseThrow(() -> new EntityNotFoundException("Permission not found"));
		permission.setName(permissionDTO.getPermissionName());
		permissionRepository.save(permission);
	}

	@Transactional(rollbackOn = Exception.class)
	public void deletePermission(int id) {
		Permission permission = permissionRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Permission not found"));
		if (!permission.getUserList().isEmpty()) {
			throw new RuntimeException("Permission is still assigned to user");
		}
		permissionRepository.delete(permission);
	}
}