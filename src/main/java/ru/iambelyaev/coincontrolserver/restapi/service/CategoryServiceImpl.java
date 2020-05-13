package ru.iambelyaev.coincontrolserver.restapi.service;

import org.springframework.stereotype.Service;
import ru.iambelyaev.coincontrolserver.restapi.model.Category;
import ru.iambelyaev.coincontrolserver.hibernate.services.CategoryService;
import ru.iambelyaev.coincontrolserver.hibernate.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import java.net.*;
import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CategoryServiceImpl implements ru.iambelyaev.coincontrolserver.restapi.service.CategoryService {
    @Override
    public boolean create(Category Category) {
        ru.iambelyaev.coincontrolserver.hibernate.services.CategoryService categoryService =
                new ru.iambelyaev.coincontrolserver.hibernate.services.CategoryService();
        ru.iambelyaev.coincontrolserver.hibernate.models.Category dbCategory =
                new ru.iambelyaev.coincontrolserver.hibernate.models.Category(Category.getCategoryName());

        ru.iambelyaev.coincontrolserver.hibernate.services.UserService userService =
                new ru.iambelyaev.coincontrolserver.hibernate.services.UserService();
        ru.iambelyaev.coincontrolserver.hibernate.models.User dbUser = userService.findUser(Category.getUserId());
        if(dbUser != null) {
            dbCategory.setUser(dbUser);
            categoryService.saveCategory(dbCategory);
            return true;
        }
        return false;
    }

    @Override
    public List<Category> readAll(int userId) {
        ru.iambelyaev.coincontrolserver.hibernate.services.UserService userService =
                new ru.iambelyaev.coincontrolserver.hibernate.services.UserService();
        ru.iambelyaev.coincontrolserver.hibernate.models.User dbUser =
                userService.findUser(userId);

//    public Category(int userId, int categoryId, String categoryName, int subCategoryId, String subCategoryName)
        List<ru.iambelyaev.coincontrolserver.hibernate.models.Category> dbCategories = dbUser.getCategories();
        List<Category> list = new ArrayList<>();
        for(ru.iambelyaev.coincontrolserver.hibernate.models.Category i : dbCategories){
            list.add(new Category(dbUser.getId(), i.getId(), i.getName(), 0, "0"));
        }
        return list;
    }

//    @Override
//    public Category read(int id) {
//        return Category_REPOSITORY_MAP.get(id);
//    }

    @Override
    public boolean update(Category Category) {
        ru.iambelyaev.coincontrolserver.hibernate.services.CategoryService categoryService =
                new ru.iambelyaev.coincontrolserver.hibernate.services.CategoryService();
        ru.iambelyaev.coincontrolserver.hibernate.models.Category dbCategory =
                categoryService.findCategory(Category.getCategoryId());
        if( dbCategory.getUser().getId() == Category.getUserId()){
            dbCategory.setName(Category.getCategoryName());
            categoryService.updateCategory(dbCategory);
            return true;
        }
        return false;
    }

    @Override
    public boolean categoryDelete(Integer userId, Integer categoryId) {
        ru.iambelyaev.coincontrolserver.hibernate.services.CategoryService categoryService =
                new ru.iambelyaev.coincontrolserver.hibernate.services.CategoryService();
        ru.iambelyaev.coincontrolserver.hibernate.models.Category dbCategory =
                categoryService.findCategory(categoryId);
        if( dbCategory.getUser().getId() == userId){
            categoryService.deleteCategory(dbCategory);
            return true;
        }
        return false;
    }

    @Override
    public boolean subcategoryDelete(int categoryId, int subcategoryId) {
        return true;
    }
}
