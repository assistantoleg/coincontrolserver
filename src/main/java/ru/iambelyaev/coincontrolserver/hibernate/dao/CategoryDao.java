package ru.iambelyaev.coincontrolserver.hibernate.dao;

import ru.iambelyaev.coincontrolserver.hibernate.models.Category;
import ru.iambelyaev.coincontrolserver.hibernate.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CategoryDao {

    public Category findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Category category = session.get(Category.class, id);
        session.close();
        return category;
    }

    public void save(Category category) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(category);
        tx1.commit();
        session.close();
    }

    public void update(Category category) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(category);
        tx1.commit();
        session.close();
    }

    public void delete(Category category) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(category);
        tx1.commit();
        session.close();
    }
}
