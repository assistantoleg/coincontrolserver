package ru.iambelyaev.coincontrolserver.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.iambelyaev.coincontrolserver.restapi.model.Category;
import ru.iambelyaev.coincontrolserver.restapi.service.CategoryService;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryService CategoryService;

    @Autowired
    public CategoryController(CategoryService CategoryService) {
        this.CategoryService = CategoryService;
    }

    @PostMapping(value = "/category")
    public ResponseEntity<?> create(@RequestBody Category Category) {
        return CategoryService.create(Category)
                ? new ResponseEntity<>(HttpStatus.CREATED)
                : new ResponseEntity<String>("not found user_id", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/category/{userId}")
    public ResponseEntity<List<Category>> read(@PathVariable(name = "userId") int userId) {
        final List<Category> Category = CategoryService.readAll(userId);
        return Category != null && !Category.isEmpty()
                ? new ResponseEntity<>(Category, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/category")
    public ResponseEntity<?> update(@RequestBody Category Category) {
        final boolean updated = CategoryService.update(Category);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
//    @PutMapping(value = "/Category/{id}")
//    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Category Category) {
//        final boolean updated = CategoryService.update(Category, id);
//
//        return updated
//                ? new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//    }

    @DeleteMapping(value = "/category/{userId}/{categoryId}")
    public ResponseEntity<?> delete(@PathVariable Integer userId, @PathVariable Integer categoryId) {
        return CategoryService.categoryDelete(userId, categoryId)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

//    @DeleteMapping(value = "/category/{id}")
//    public ResponseEntity<?> delete(@PathVariable Integer id) {
//        System.out.println(id.toString());
//        final boolean deleted = CategoryService.categoryDelete(id);
//        return deleted
//                ? new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//    }
}
