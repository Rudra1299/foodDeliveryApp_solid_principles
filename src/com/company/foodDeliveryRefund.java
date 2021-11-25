package com.company;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
public class foodDeliveryRefund extends foodDelivery{
    public foodDeliveryRefund(String username, String password, Map<String, String> userAuth,
                              Map<Integer, String> idToCustomers, Map<Integer, List<String>> idToFoodOrders,
                              Map<String, Double> orderMenu,Map<Integer, Double>  orderAmount) {
        super(username, password, userAuth, idToCustomers, idToFoodOrders, orderMenu, orderAmount);
    }
    //method for the refund service
    @Override
    public boolean service() {
        Scanner sc = new Scanner(System.in);
        System.out.print("The the customer id: ");
        Integer id = sc.nextInt();
        this.getIdToCustomer().remove(id);
        this.getIdToCustomer().remove(id);
        Double Amount = this.getOrderAmount().get(id);
        this.getOrderAmount().remove(id);
        System.out.println("Your refund amount is: " + Amount);
        return true;
    }
}
