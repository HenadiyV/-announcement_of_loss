package adver.example.adver.controllers;


import adver.example.adver.dto.CategoryDTO;
import adver.example.adver.models.*;
import adver.example.adver.repos.AdverRepository;
import adver.example.adver.repos.CategoryRepository;
import adver.example.adver.repos.CityRepository;
import adver.example.adver.repos.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import adver.example.adver.repos.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test/category")
public class CategoryController {
    final CategoryRepository categoryRepo;

    @Autowired
    public CategoryController(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @GetMapping
    public List<Category> getListCategorys(){

        CategoryDTO ST=new CategoryDTO((List<Category>)categoryRepo.findAll());
        return ST.getListCategorys();
    }

    @GetMapping("{id}")
    public Category getOne(@PathVariable("id") Category category) {
        return category;
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) { ;
        return categoryRepo.save(category);

    }

    @PutMapping("{id}")
    public Category updateCategory(@RequestBody Category newCategory, @PathVariable int id) {

        return categoryRepo.findById(id)
                .map(category -> {
                    category.setName(newCategory.getName());
                    return categoryRepo.save(category);
                }).get();

    }

    @DeleteMapping("{id}")
    public void deleteCategory(@PathVariable("id") Category category) {
        categoryRepo.delete(category);
    }
}