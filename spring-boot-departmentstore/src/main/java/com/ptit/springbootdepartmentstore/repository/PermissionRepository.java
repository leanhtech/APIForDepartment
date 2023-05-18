package com.ptit.springbootdepartmentstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptit.springbootdepartmentstore.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {

}
