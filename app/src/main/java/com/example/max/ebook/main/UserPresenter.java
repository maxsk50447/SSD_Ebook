package com.example.max.ebook.main;

import android.content.Intent;

import com.example.max.ebook.user.User;

/**
 * Created by Max on 3/6/2560.
 */

public class UserPresenter {
    private UserView view;
    User user;
    public UserPresenter(UserView view, Intent intent) {
        this.view = view;
        user = new User();
        createUser(intent);
    }
    public void refill(double amount) {
        user.refill(amount);
        view.updateMoney(user.getMoney());
    }
    public void createUser(Intent intent){
        int cartSize = Integer.parseInt(intent.getStringExtra("cartSize"));
        int collectionSize = Integer.parseInt(intent.getStringExtra("collectionSize"));
        double wallet = Double.parseDouble(intent.getStringExtra("wallet"));
        double sumPrice = Double.parseDouble(intent.getStringExtra("sumPrice"));

        for(int i=0;i<collectionSize;i++){
            user.addCollection(intent.getStringExtra("collection"+i));
        }

        for(int i=0;i<cartSize;i++){
            user.order.addOrder(intent.getStringExtra("cartBook"+i));
        }

        setMoney(wallet);
        setTotalPrice(sumPrice);
        view.updateMoney(user.money);
    }


    public void showCart() {
        view.updateAll(user.order.getOrderList());
    }

    public void showCollection(){
        view.updateAll(user.getCollectionList());
    }

    public void setMoney(double money){
        user.money = money;
    }

    public void setTotalPrice(double totalPrice){
        user.order.totalPrice = totalPrice;
    }

    public double getMoney() {
        return user.money;
    }

    public void purchase(){
        user.purchase();
        view.updateAll(user.order.getOrderList());
        view.updateMoney(user.money);
    }
}
