package com.ptit.springbootdepartmentstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptit.springbootdepartmentstore.entity.Address;
import com.ptit.springbootdepartmentstore.entity.User;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	public List<Address> findByUser(User user);

}
