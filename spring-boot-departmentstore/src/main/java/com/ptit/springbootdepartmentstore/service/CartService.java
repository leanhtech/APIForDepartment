package com.ptit.springbootdepartmentstore.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.CartDTO;
import com.ptit.springbootdepartmentstore.entity.Cart;
import com.ptit.springbootdepartmentstore.entity.CartId;
import com.ptit.springbootdepartmentstore.repository.CartRepository;
import com.ptit.springbootdepartmentstore.repository.ProductRepository;
import com.ptit.springbootdepartmentstore.repository.UserRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	public void addProductToCart(CartDTO cartDTO) {
		CartId cartId = new CartId(
				userRepository.findById(cartDTO.getUserId())
						.orElseThrow(() -> new EntityNotFoundException("User not found")),
				productRepository.findById(cartDTO.getProductId())
						.orElseThrow(() -> new EntityNotFoundException("Product not found")));
		Cart cart = cartRepository.findById(cartId).orElseGet(() -> {
			Cart newCart = new Cart();
			newCart.setId(cartId);
			newCart.setQuantity(1);
			return newCart;
		});
		cart.setQuantity(cart.getQuantity() + cartDTO.getQuantity());
		cartRepository.save(cart);
	}

//	public List<CartDto> getProductsInCartByUser(User user) {
//		List<Cart> carts = cartRepository.findByIdUser(user);
//		return carts.stream().map(this::mapToDto).collect(Collectors.toList());
//	}
//
//	public void updateCart(CartDto cartDto) {
//		CartId cartId = new CartId(cartDto.getUser(), cartDto.getProduct());
//		Cart cart = cartRepository.findById(cartId).orElse(null);
//		if (cart == null) {
//			throw new EntityNotFoundException("Cart not found");
//		}
//		cart.setQuantity(cartDto.getQuantity());
//		cartRepository.save(cart);
//	}
// private CartDto mapToDto(Cart cart) {
// CartDto cartDto = new CartDto();
// cartDto.setUser(cart.getId().getUser());
// cartDto.setProduct(cart.getId().getProduct());
// cartDto.setQuantity(cart.getQuantity());
// return cartDto;
// }
}