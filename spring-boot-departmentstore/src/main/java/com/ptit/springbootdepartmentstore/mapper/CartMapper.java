package com.ptit.springbootdepartmentstore.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.CartDTO;
import com.ptit.springbootdepartmentstore.entity.Cart;
import com.ptit.springbootdepartmentstore.entity.CartId;
import com.ptit.springbootdepartmentstore.entity.Product;
import com.ptit.springbootdepartmentstore.entity.User;
import com.ptit.springbootdepartmentstore.repository.CartRepository;

@Component
public class CartMapper {

    public CartDTO toDto(Cart cart) {
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

}
