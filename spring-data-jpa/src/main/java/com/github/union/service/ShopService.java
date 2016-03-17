package com.github.union.service;

import com.github.union.exception.ShopNotFoundException;
import com.github.union.model.Shop;

import java.util.List;

public interface ShopService {
    Shop create(Shop shop);
    Shop delete(int id) throws ShopNotFoundException;
    List<Shop> findAll();
    Shop update(Shop shop) throws ShopNotFoundException;
    Shop findById(int id);
}