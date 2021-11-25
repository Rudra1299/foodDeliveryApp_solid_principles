package com.deliveryApp;

import java.util.List;
import java.util.Map;

public class amountCalculator {

    public static double calculateTotalAmount(List<String>orders, Map<String, Double> orderMenu){
        double Amount = 0;
        for(String order : orders) {
            Amount += orderMenu.get(order);
        }

        return Amount;
    }
}
