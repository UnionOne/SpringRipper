package com.github.union.service;

import com.github.union.exception.ShopNotFoundException;
import com.github.union.model.Shop;
import com.github.union.repository.ShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Resource
    private ShopRepository shopRepository;

    @Override
    @Transactional
    public Shop create(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    @Transactional(rollbackFor = ShopNotFoundException.class)
    public Shop delete(int id) throws ShopNotFoundException {
        Shop deletedShop = shopRepository.findOne(id);

        if (deletedShop == null) {
            throw new ShopNotFoundException();
        }

        shopRepository.delete(deletedShop);
        return deletedShop;
    }

    @Override
    @Transactional
    public List<Shop> findAll() {
        return shopRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = ShopNotFoundException.class)
    public Shop update(Shop shop) throws ShopNotFoundException {
        Shop updateShop = shopRepository.findOne(shop.getId());

        if (updateShop == null) {
            throw new ShopNotFoundException();
        }

        updateShop.setName(shop.getName());
        updateShop.setEmployeesNumber(shop.getEmployeesNumber());
        return updateShop;
    }

    @Override
    @Transactional
    public Shop findById(int id) {
        return shopRepository.findOne(id);
    }
}
