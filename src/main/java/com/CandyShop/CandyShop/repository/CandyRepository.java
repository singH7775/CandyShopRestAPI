package com.CandyShop.CandyShop.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.CandyShop.CandyShop.entity.Candy;


@Repository
public interface CandyRepository extends CrudRepository<Candy, Long>{

}