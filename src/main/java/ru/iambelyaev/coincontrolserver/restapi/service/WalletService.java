package ru.iambelyaev.coincontrolserver.restapi.service;

import ru.iambelyaev.coincontrolserver.restapi.model.Wallet;

import java.util.List;

public interface WalletService {
    boolean create(Wallet Wallet);

    List<Wallet> readAll(int userId);

    boolean update(Wallet Wallet);

    boolean walletDelete(Integer userId, Integer walletId);

}
