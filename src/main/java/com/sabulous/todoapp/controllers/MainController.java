package com.sabulous.todoapp.controllers;

import java.util.ArrayList;
import java.util.List;

import com.sabulous.todoapp.model.Item;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public Item getItem() {
        
        Item dep = new Item();
        dep.setId(102);
        dep.setContent("dependent item content");

        Item i = new Item();
        i.setContent("item content is this");
        i.setStatus(2);
        i.setDependentItems(new ArrayList<Item>(List.of(dep)));
        
        return i;
    } 
}