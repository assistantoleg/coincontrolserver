package ru.iambelyaev.coincontrolserver.hibernate.services;

import ru.iambelyaev.coincontrolserver.hibernate.models.Wallet;

public interface WalletService {

    public Wallet findWallet(int id);

    public void saveWallet(Wallet wallet);

    public void deleteWallet(Wallet wallet);

    public void updateWallet(Wallet wallet);
}
