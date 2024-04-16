package com.age2001.inventory.service.service.kafka;

import com.age2001.inventory.service.entity.Item;

import java.util.List;

public interface ItemService {
    Item createItem(Item newItem);

    List<Item> getAllItems();

}
