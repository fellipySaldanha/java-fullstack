package com.java.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.domain.Category;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Category> listAll(){
        Category cat1 = new Category(1, "informatica");
        Category cat2 = new Category(2, "escritorio");
        List<Category> listCategory = new ArrayList<>();
        listCategory.add(cat1);
        listCategory.add(cat2);
        return listCategory;
    }
    
}