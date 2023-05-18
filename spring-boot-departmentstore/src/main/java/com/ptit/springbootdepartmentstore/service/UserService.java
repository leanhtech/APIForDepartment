package com.ptit.springbootdepartmentstore.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.UserDTO;
import com.ptit.springbootdepartmentstore.entity.User;
import com.ptit.springbootdepartmentstore.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PermissionService permissionService;

	@Autowired
	AddressService addressService;

	public UserDTO convertToUserDTO(User user) {
		return new UserDTO(user.getId(), user.getName(), user.getPassword(), user.getFirstName(), user.getLastName(),
				user.getImage(), user.getEmail(), user.getPhone(), user.getCreateDate(), user.getStatus(),
				addressService.convertToListAddressDTO(user.getAddressList()),
				permissionService.getPermission(user.getPermission().getId()));
	}

	public List<UserDTO> convertToListUserDTO(List<User> users) {
		return users.stream().map(this::convertToUserDTO).collect(Collectors.toList());
	}

	public User convertToUser(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setImage(userDTO.getImage());
		user.setEmail(userDTO.getEmail());
		user.setPhone(userDTO.getPhone());
		user.setCreateDate(userDTO.getCreateDate());
		user.setStatus(userDTO.getStatus());
		user.setAddressList(addressService.convertToListAddress(userDTO.getAddressList()));
		user.setPermission(permissionService.convertToPermission(userDTO.getPermission()));
		return user;
	}

	public List<UserDTO> getAllUser() {
		List<User> users = userRepository.findAll();
		return convertToListUserDTO(users);
	}

	public UserDTO getUser(int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
		return convertToUserDTO(user);
	}

	@Transactional(rollbackOn = Exception.class)
	public UserDTO createUser(UserDTO userDTO) {
		User user = convertToUser(userDTO);
		User savedUser = userRepository.save(user);
		return convertToUserDTO(savedUser);
	}

	@Transactional(rollbackOn = Exception.class)
	public UserDTO updateUser(UserDTO userDTO) {
		User user = userRepository.findById(userDTO.getId())
				.orElseThrow(() -> new EntityNotFoundException("User not found"));
		user = convertToUser(userDTO);
		User savedUser = userRepository.save(user);
		return convertToUserDTO(savedUser);
	}

	@Transactional(rollbackOn = Exception.class)
	public void deleteUser(int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
		userRepository.delete(user);
	}

	public UserDTO authenticate(String name, String password) {
		User user = userRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("User not found"));
		if (user.getPassword().equals(password)) {
			return convertToUserDTO(user);
		} else {
			throw new IllegalArgumentException("Invalid password");
		}
	}
}