package com.CandyShop.CandyShop.repository;

import org.springframework.data.repository.CrudRepository;

import com.CandyShop.CandyShop.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
