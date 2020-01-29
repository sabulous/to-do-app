package com.sabulous.todoapp.repositories;

import com.sabulous.todoapp.model.Item;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {

}