package ru.iambelyaev.coincontrolserver.hibernate.services;

import org.springframework.stereotype.Service;
import ru.iambelyaev.coincontrolserver.hibernate.dao.WalletDao;
import ru.iambelyaev.coincontrolserver.hibernate.dao.UserDao;
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
public class WalletServiceImpl implements WalletService {

    private WalletDao walletDao;
    private UserDao userDao;

    @Override
    public boolean create(Wallet wallet) {
        User dbUser = userDao.findById(wallet.getUser().getId());
        if(dbUser != null) {
            wallet.setUser(dbUser);
            walletDao.save(wallet);
            return true;
        }
        return false;
    }

//    @Override
//    public List<Wallet> readAll(int userId) {
//        ru.iambelyaev.coincontrolserver.hibernate.services.UserService userService =
//                new ru.iambelyaev.coincontrolserver.hibernate.services.UserService();
//        ru.iambelyaev.coincontrolserver.hibernate.models.User dbUser =
//                userService.findUser(userId);
//
//        List<Wallet> list = new ArrayList<>();
//        if(dbUser != null) {
//            List<ru.iambelyaev.coincontrolserver.hibernate.models.Wallet> dbWallets = userService.findWalletAll(userId);
//            for (ru.iambelyaev.coincontrolserver.hibernate.models.Wallet i : dbWallets) {
//                list.add(new Wallet(i.getId(), dbUser.getId(), i.getName(), i.getMoney()));
//            }
//        }
//        return list;
//    }
//
//    @Override
//    public boolean update(Wallet Wallet) {
//        ru.iambelyaev.coincontrolserver.hibernate.services.WalletService walletService =
//                new ru.iambelyaev.coincontrolserver.hibernate.services.WalletService();
//        ru.iambelyaev.coincontrolserver.hibernate.models.Wallet dbWallet =
//                walletService.findWallet(Wallet.getId());
//        if( dbWallet.getUser().getId() == Wallet.getUser()){
//            dbWallet.setName(Wallet.getName());
//            walletService.updateWallet(dbWallet);
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean walletDelete(Integer userId, Integer walletId) {
//        ru.iambelyaev.coincontrolserver.hibernate.services.WalletService walletService =
//                new ru.iambelyaev.coincontrolserver.hibernate.services.WalletService();
//        ru.iambelyaev.coincontrolserver.hibernate.models.Wallet dbWallet =
//                walletService.findWallet(walletId);
//        if( dbWallet.getUser().getId() == userId){
//            walletService.deleteWallet(dbWallet);
//            return true;
//        }
//        return false;
//    }
}
