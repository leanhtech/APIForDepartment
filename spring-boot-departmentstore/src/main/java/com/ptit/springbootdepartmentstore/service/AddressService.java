package com.ptit.springbootdepartmentstore.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.AddressDTO;
import com.ptit.springbootdepartmentstore.entity.Address;
import com.ptit.springbootdepartmentstore.entity.User;
import com.ptit.springbootdepartmentstore.repository.AddressRepository;
import com.ptit.springbootdepartmentstore.repository.UserRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	public List<String> getListTextAddress(List<AddressDTO> addressDTOs) {
		return addressDTOs.stream()
				.map(addressDTO -> addressDTO.getAddressSpecific() + " " + addressDTO.getAddressGeneral())
				.collect(Collectors.toList());
	}

	public AddressDTO convertToAddressDTO(Address address) {
		return new AddressDTO(address.getId(), address.getAddressGeneral(), address.getAddressSpecific(),
				address.getUser().getId());
	}

	public List<AddressDTO> convertToListAddressDTO(List<Address> addresses) {
		return addresses.stream().map(this::convertToAddressDTO).collect(Collectors.toList());
	}

	public Address convertToAddress(AddressDTO addressDTO) {
		User user = userRepository.findById(addressDTO.getUserId())
				.orElseThrow(() -> new EntityNotFoundException("Address not found"));
		Address address = new Address();
		address.setAddressGeneral(addressDTO.getAddressGeneral());
		address.setAddressSpecific(addressDTO.getAddressSpecific());
		address.setUser(user);
		return address;
	}

	public List<Address> convertToListAddress(List<AddressDTO> addressDTOs) {
		return addressDTOs.stream().map(this::convertToAddress).collect(Collectors.toList());
	}

	@Transactional(rollbackOn = Exception.class)
	public AddressDTO saveAddress(AddressDTO addressDTO) {
		Address address = convertToAddress(addressDTO);
		addressRepository.save(address);
		return convertToAddressDTO(address);
	}

	@Transactional(rollbackOn = Exception.class)
	public AddressDTO updateAddress(AddressDTO addressDTO) {
		Address address = addressRepository.findById(addressDTO.getId())
				.orElseThrow(() -> new EntityNotFoundException("Address not found"));
		address.setAddressGeneral(addressDTO.getAddressGeneral());
		address.setAddressSpecific(addressDTO.getAddressSpecific());
		address.setUser(userRepository.findById(addressDTO.getUserId())
				.orElseThrow(() -> new EntityNotFoundException("User not found")));
		addressRepository.save(address);
		return convertToAddressDTO(address);
	}

	@Transactional(rollbackOn = Exception.class)
	public void deleteAddress(int id) {
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Address not found"));
		addressRepository.delete(address);
	}

	public AddressDTO getAddress(int id) {
		Address address = addressRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Address not found"));
		return convertToAddressDTO(address);
	}
}