package com.ptit.springbootdepartmentstore.mapper;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.UserDTO;
import com.ptit.springbootdepartmentstore.entity.Address;
import com.ptit.springbootdepartmentstore.entity.User;
import com.ptit.springbootdepartmentstore.repository.AddressRepository;
import com.ptit.springbootdepartmentstore.repository.PermissionRepository;

@Component
public class UserMapper {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private PermissionRepository permissionRepository;

//	private String generateAvatarUrl(byte[] avatar) {
//		if (avatar == null) {
//			return null;
//		}
//		return "data:image/png;base64," + Base64.getEncoder().encodeToString(avatar);
//	}

	public UserDTO toDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUserName(user.getName());
		userDTO.setPassword(user.getPassword());
		userDTO.setFirstName(user.getFirstName());
		userDTO.setLastName(user.getLastName());
		userDTO.setEmail(user.getEmail());
		userDTO.setPhone(user.getPhone());
		userDTO.setCreateDate(user.getCreateDate());
		userDTO.setStatus(user.getStatus());
		if (user.getAddressList() != null) {
			List<Integer> listIdAddress = user.getAddressList().stream().map(Address::getId)
					.collect(Collectors.toList());
			userDTO.setAddressIds(listIdAddress);
		}
		userDTO.setPermissionId(user.getPermission().getId());
		userDTO.setAvatar(user.getAvatar());
//		userDTO.setAvatarUrl(generateAvatarUrl(user.getAvatar()));
		return userDTO;
	}

	public User toEntity(UserDTO userDTO) {
		User user = new User();
		user.setId(userDTO.getId());
		user.setName(userDTO.getUserName());
		user.setPassword(userDTO.getPassword());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmail(userDTO.getEmail());
		user.setPhone(userDTO.getPhone());
		user.setCreateDate(userDTO.getCreateDate());
		user.setStatus(userDTO.getStatus());
		if (userDTO.getAddressIds() != null) {
			List<Address> list = new ArrayList<>();
			for (Integer id : userDTO.getAddressIds()) {
				Address temp = addressRepository.findById(id)
								.orElseThrow(() -> new EntityNotFoundException("Address not found"));
				temp.setUser(user);
				list.add(temp);
			}
			
			System.out.println("da vao");
			System.out.println(list.toString());
			user.setAddressList(list);
		}
		if (userDTO.getPermissionId() != null)
			user.setPermission(permissionRepository.findById(userDTO.getPermissionId())
					.orElseThrow(() -> new EntityNotFoundException("Permission not found")));
		if (userDTO.getAvatar() != null)
			user.setAvatar(userDTO.getAvatar());
//		else {
//			String base64Data = userDTO.getAvatarUrl().substring(userDTO.getAvatarUrl().indexOf(",") + 1);
//			byte[] imageData = Base64.getDecoder().decode(base64Data);
//			user.setAvatar(imageData);
//		}
		return user;
	}

	public List<UserDTO> toListDTO(List<User> users) {
		return users.stream().map(this::toDTO).collect(Collectors.toList());
	}
}