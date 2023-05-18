package com.ptit.springbootdepartmentstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptit.springbootdepartmentstore.dto.AddressDTO;
import com.ptit.springbootdepartmentstore.service.AddressService;

@RestController
@RequestMapping("/addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GetMapping("/{id}")
	public ResponseEntity<AddressDTO> getAddress(@PathVariable int id) {
		AddressDTO addressDTO = addressService.getAddress(id);
		if (addressDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(addressDTO);
	}

	@PostMapping
	public ResponseEntity<AddressDTO> saveAddress(@RequestBody AddressDTO addressDTO) {
		addressService.saveAddress(addressDTO);
		return ResponseEntity.ok(addressDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AddressDTO> updateAddress(@PathVariable int id, @RequestBody AddressDTO addressDTO) {
		if (addressDTO.getId() != id) {
			return ResponseEntity.badRequest().build();
		}
		AddressDTO updatedAddressDTO = addressService.updateAddress(addressDTO);
		if (updatedAddressDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedAddressDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAddress(@PathVariable int id) {
		addressService.deleteAddress(id);
		return ResponseEntity.noContent().build();
	}
}