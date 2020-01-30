package com.sabulous.todoapp.services;

import com.sabulous.todoapp.model.Item;

import org.springframework.stereotype.Service;

@Service
public interface ItemService extends CRUDService<Item> {

}