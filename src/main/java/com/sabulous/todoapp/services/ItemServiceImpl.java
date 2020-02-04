package com.sabulous.todoapp.services;

import java.util.ArrayList;
import java.util.List;

import com.sabulous.todoapp.model.Item;
import com.sabulous.todoapp.repositories.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> listAll() {
        List<Item> items = new ArrayList<>();
        itemRepository.findAll().forEach(items::add);
        return items;
    }

    @Override
    public Item getById(Integer id) {
        return itemRepository.findById(id).get();
    }

    @Override
    public Item saveOrUpdate(Item Item) {
        return itemRepository.save(Item);
    }

    @Override
    public void delete(Integer id) {
        itemRepository.deleteById(id);
    }

}