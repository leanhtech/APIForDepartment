package com.ptit.springbootdepartmentstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.CartDTO;
import com.ptit.springbootdepartmentstore.entity.Cart;
import com.ptit.springbootdepartmentstore.entity.CartId;
import com.ptit.springbootdepartmentstore.entity.Product;
import com.ptit.springbootdepartmentstore.entity.User;
import com.ptit.springbootdepartmentstore.mapper.CartMapper;
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

    @Autowired
    private CartMapper cartMapper;

    public List<CartDTO> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        return cartMapper.toListDTO(carts);
    }
    
    public List<CartDTO> getAllByIdUser(Integer id) {
    	List<Cart> carts = cartRepository.findByIdUserId(id);
    	return cartMapper.toListDTO(carts);
    }

    public CartDTO getCartByUserIdAndProductId(Integer userId, Integer productId) {
        User user = userRepository.findById(userId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if (user != null && product != null) {
            CartId cartId = new CartId();
            cartId.setUser(user);
            cartId.setProduct(product);
            Cart cart = cartRepository.findById(cartId).orElse(null);

            if (cart != null) {
                CartDTO cartDTO = cartMapper.toDTO(cart);
                return cartDTO;
            }
        }

        return null;
    }


    public CartDTO addToCart(Integer userId, Integer productId, Integer quantity) {
        User user = userRepository.findById(userId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if (user != null && product != null) {
            CartDTO existingCartDTO = getCartByUserIdAndProductId(userId, productId);

            if (existingCartDTO != null) {
                Integer newQuantity = existingCartDTO.getQuantity() + quantity;
                existingCartDTO.setQuantity(newQuantity);
                Cart cart = cartMapper.toEntity(existingCartDTO, user, product);
                Cart savedCart = cartRepository.save(cart);
                CartDTO savedCartDTO = cartMapper.toDTO(savedCart);
                return savedCartDTO;
            } else {
                CartDTO cartDTO = new CartDTO();
                cartDTO.setUserId(userId);
                cartDTO.setProductId(productId);
                cartDTO.setQuantity(quantity);
                Cart cart = cartMapper.toEntity(cartDTO, user, product);
                Cart savedCart = cartRepository.save(cart);
                CartDTO savedCartDTO = cartMapper.toDTO(savedCart);
                return savedCartDTO;
            }
        }

        return null;
    }

    public CartDTO updateCart(Integer userId, Integer productId, Integer quantity) {
        User user = userRepository.findById(userId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if (user != null && product != null) {
            CartDTO existingCartDTO = getCartByUserIdAndProductId(userId, productId);

            if (existingCartDTO != null) {
                existingCartDTO.setQuantity(quantity);
                Cart cart = cartMapper.toEntity(existingCartDTO, user, product);
                Cart savedCart = cartRepository.save(cart);
                return cartMapper.toDTO(savedCart);
            }
        }
        return null;
    }
    
    public void deleteCart(Integer userId, Integer productId) {
        User user = userRepository.findById(userId).orElse(null);
        Product product = productRepository.findById(productId).orElse(null);

        if (user != null && product != null) {
            CartId cartId = new CartId();
            cartId.setUser(user);
            cartId.setProduct(product);
            cartRepository.deleteById(cartId);
        }
    }
                
}