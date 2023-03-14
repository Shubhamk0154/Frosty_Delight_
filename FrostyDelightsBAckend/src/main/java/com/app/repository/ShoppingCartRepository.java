package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}
