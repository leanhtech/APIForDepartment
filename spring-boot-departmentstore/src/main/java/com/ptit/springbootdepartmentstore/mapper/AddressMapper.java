package com.ptit.springbootdepartmentstore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.AddressDTO;
import com.ptit.springbootdepartmentstore.entity.Address;
import com.ptit.springbootdepartmentstore.repository.UserRepository;

@Component
public class AddressMapper {

	@Autowired
	private UserRepository userRepository;

	public AddressDTO toDTO(Address address) {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setId(address.getId());
		addressDTO.setAddressGeneral(address.getAddressGeneral());
		addressDTO.setAddressSpecific(address.getAddressSpecific());
		if(address.getUser() != null)
			addressDTO.setUserId(address.getUser().getId());
		return addressDTO;
	}

	public Address toEntity(AddressDTO addressDTO) {
		Address address = new Address();
		address.setId(addressDTO.getId());
		address.setAddressGeneral(addressDTO.getAddressGeneral());
		address.setAddressSpecific(addressDTO.getAddressSpecific());
		if(addressDTO.getUserId() != null)
			address.setUser(userRepository.findById(addressDTO.getUserId())
					.orElse(null));
		return address;
	}

	public List<AddressDTO> toListDTO(List<Address> addresses) {
		return addresses.stream().map(this::toDTO).collect(Collectors.toList());
	}

	public List<Address> toListEntity(List<AddressDTO> addressDTOs) {
		return addressDTOs.stream().map(this::toEntity).collect(Collectors.toList());
	}

	public List<Integer> toListId(List<Address> addresses) {
		return addresses.stream().map(Address::getId).collect(Collectors.toList());
	}

}