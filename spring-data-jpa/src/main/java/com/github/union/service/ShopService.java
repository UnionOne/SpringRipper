package com.github.union.service;

import com.github.union.model.Shop;

import java.util.List;

public interface ShopService {
    Shop create(Shop shop);
    Shop delete(int id) throws Exception;
    List<Shop> findAll();
    Shop update(Shop shop) throws Exception;
    Shop findById(int id);
}