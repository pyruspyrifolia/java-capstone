package com.restaurant.api.controllers;

import com.restaurant.api.entities.Item;
import com.restaurant.api.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        try {
            List<Item> items = itemRepository.findAll();
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        try {
            Optional<Item> item = itemRepository.findById(id);
            if (item.isPresent()) {
                return ResponseEntity.ok(item.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/order/{orderid}")
    public ResponseEntity<List<Item>> getItemsByOrderId(@PathVariable Long orderid) {
        try {
            List<Item> items = itemRepository.findByOrderid(orderid);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/order/{orderid}")
    public ResponseEntity<String> addItemsToOrder(@PathVariable Long orderid, @RequestBody List<Item> items) {
        try {
            for (Item item : items) {
                item.setOrderid(orderid);
                itemRepository.save(item);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body("Items added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error adding items");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
        try {
            Optional<Item> existingItem = itemRepository.findById(id);
            if (existingItem.isPresent()) {
                item.setId(id);
                Item savedItem = itemRepository.save(item);
                return ResponseEntity.ok(savedItem);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        try {
            Optional<Item> item = itemRepository.findById(id);
            if (item.isPresent()) {
                itemRepository.deleteById(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/order/{orderid}")
    @Transactional
    public ResponseEntity<Void> deleteItemsByOrderId(@PathVariable Long orderid) {
        try {
            itemRepository.deleteByOrderid(orderid);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}