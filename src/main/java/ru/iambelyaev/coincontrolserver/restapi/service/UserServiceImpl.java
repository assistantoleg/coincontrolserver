package ru.iambelyaev.coincontrolserver.restapi.service;

import org.springframework.stereotype.Service;
import ru.iambelyaev.coincontrolserver.restapi.model.User;
import ru.iambelyaev.coincontrolserver.hibernate.services.UserService;
import ru.iambelyaev.coincontrolserver.hibernate.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import java.net.*;
import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserServiceImpl implements ru.iambelyaev.coincontrolserver.restapi.service.UserService {
    @Override
    public boolean create(User User) {
        System.out.println(User.getUserName());
        if(User.getUserName().isEmpty() || User.getUserPassword().isEmpty())
            return false;
        ru.iambelyaev.coincontrolserver.hibernate.services.UserService userService =
                new ru.iambelyaev.coincontrolserver.hibernate.services.UserService();
        ru.iambelyaev.coincontrolserver.hibernate.models.User dbUser =
                new ru.iambelyaev.coincontrolserver.hibernate.models.User(User.getUserName(),User.getUserPassword());
        userService.saveUser(dbUser);
        return true;
    }

    @Override
    public List<User> readAll() {
        ru.iambelyaev.coincontrolserver.hibernate.services.UserService userService =
                new ru.iambelyaev.coincontrolserver.hibernate.services.UserService();
        List<ru.iambelyaev.coincontrolserver.hibernate.models.User> dbUser = userService.findAllUsers();
        ArrayList<User> list = new ArrayList<>();
        for(ru.iambelyaev.coincontrolserver.hibernate.models.User i : dbUser){
            list.add(new User(i.getId(), i.getName(), i.getPassword()));
        }
        return list;
    }

    @Override
    public boolean update(ru.iambelyaev.coincontrolserver.restapi.model.User User) {
        ru.iambelyaev.coincontrolserver.hibernate.services.UserService userService =
                new ru.iambelyaev.coincontrolserver.hibernate.services.UserService();
        ru.iambelyaev.coincontrolserver.hibernate.models.User dbUser =
                userService.findUser(User.getUserId());
        if( dbUser != null){
            dbUser.setName(User.getUserName());
            dbUser.setPassword(User.getUserPassword());
            userService.updateUser(dbUser);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        ru.iambelyaev.coincontrolserver.hibernate.services.UserService userService =
                new ru.iambelyaev.coincontrolserver.hibernate.services.UserService();
        ru.iambelyaev.coincontrolserver.hibernate.models.User dbUser =
                userService.findUser(id);
        if(dbUser != null){
            userService.deleteUser(dbUser);
        }
        return false;
    }
}