package com.age2001.inventory.service.service.impl;

import com.age2001.inventory.service.entity.Item;
import com.age2001.inventory.service.repository.ItemRepository;
import com.age2001.inventory.service.service.kafka.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item createItem(Item newItem) {
        return itemRepository.save(newItem);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
