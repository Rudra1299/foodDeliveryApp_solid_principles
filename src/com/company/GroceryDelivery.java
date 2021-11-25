package com.company;

import java.util.List;
import java.util.Map;

public class GroceryDelivery extends foodDelivery{

    public GroceryDelivery(String username, String password, Map<String, String> userAuth,
                           Map<Integer, String> idToCustomers, Map<Integer, List<String>> idToFoodOrders,
                           Map<String, Double> orderMenu,Map<Integer, Double>  orderAmount) {
        super(username, password, userAuth, idToCustomers, idToFoodOrders, orderMenu, orderAmount);
    }

    @Override
    public void discountAvail() {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    public void offersInGroceryStore() {
        System.out.print("avail offers in nearby grocery store");
    }
}
