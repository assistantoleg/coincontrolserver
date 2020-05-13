package ru.iambelyaev.coincontrolserver.hibernate.services;

import ru.iambelyaev.coincontrolserver.hibernate.dao.CategoryDao;
import ru.iambelyaev.coincontrolserver.hibernate.models.Category;

public class CategoryService {

    private CategoryDao categoryDao = new CategoryDao();

    public CategoryService() {
    }

    public Category findCategory(int id) {
        return categoryDao.findById(id);
    }

    public void saveCategory(Category category) {
        categoryDao.save(category);
    }

    public void deleteCategory(Category category) {
        categoryDao.delete(category);
    }

    public void updateCategory(Category category) {
        categoryDao.update(category);
    }
}
