package com.ptit.springbootdepartmentstore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.CartDTO;
import com.ptit.springbootdepartmentstore.entity.Cart;
import com.ptit.springbootdepartmentstore.entity.CartId;
import com.ptit.springbootdepartmentstore.entity.Product;
import com.ptit.springbootdepartmentstore.entity.User;

@Component
public class CartMapper {

    public CartDTO toDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setUserId(cart.getId().getUser().getId());
        cartDTO.setProductId(cart.getId().getProduct().getId());
        cartDTO.setQuantity(cart.getQuantity());
        return cartDTO;
    }

    public Cart toEntity(CartDTO cartDTO, User user, Product product) {
        Cart cart = new Cart();
        CartId cartId = new CartId();
        cartId.setUser(user);
        cartId.setProduct(product);
        cart.setId(cartId);
        cart.setQuantity(cartDTO.getQuantity());
        return cart;
    }
    
    public List<CartDTO> toListDTO (List<Cart> carts) {
    	return carts.stream()
    			.map(this::toDTO)
    			.collect(Collectors.toList());
    } 

}
