package com.age2001.inventory.service.controller;

import com.age2001.inventory.service.entity.Item;
import com.age2001.inventory.service.service.kafka.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/items")
@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> data = itemService.getAllItems();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item data = itemService.createItem(item);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }
}
