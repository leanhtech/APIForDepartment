package com.ptit.springbootdepartmentstore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.PermissionDTO;
import com.ptit.springbootdepartmentstore.entity.Permission;

@Component
public class PermissionMapper {
	
	public PermissionDTO toDTO(Permission permisson) {
		PermissionDTO permissionDTO = new PermissionDTO();
		permissionDTO.setId(permisson.getId());
		permissionDTO.setPermissionName(permisson.getName());
		return permissionDTO;
	}
	
	public Permission toEntity(PermissionDTO permissionDTO) {
		Permission permission = new Permission();
		permission.setId(permissionDTO.getId());
		permission.setName(permissionDTO.getPermissionName());
		return permission;
	}
	
	public List<PermissionDTO> toListDTO(List<Permission> permissions) {
		return permissions.stream()
				.map(this::toDTO)
				.collect(Collectors.toList());
	}

}
