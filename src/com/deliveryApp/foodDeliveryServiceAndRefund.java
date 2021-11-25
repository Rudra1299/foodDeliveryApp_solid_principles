package com.deliveryApp;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class foodDeliveryServiceAndRefund extends foodDeliveryServiceImp{

    public foodDeliveryServiceAndRefund(String username, String password, Map<Integer, String> idToCustomer, Map<Integer,
            List<String>> idToFoodOrders, Map<String, Double> orderMenu, Map<Integer, Double> orderAmount,
                                        Authenticator authenticObject, orderManager orderCreator) {
        super(username, password, idToCustomer, idToFoodOrders, orderMenu, orderAmount, authenticObject, orderCreator);
    }

    //method to get refund initiated
    @Override
    public boolean service() {
        Scanner sc = new Scanner(System.in);
        System.out.print("The the order id: ");
        Integer id = sc.nextInt();

        if(!this.getOrderAmount().containsKey(id)) {
            System.out.println("The id is not existing as no purchase was made");
            return false;
        }

        this.getIdToCustomer().remove(id);
        Double Amount = this.getOrderAmount().get(id);
        this.getOrderAmount().remove(id);
        System.out.println("Your refund amount is: " + Amount);

        return true;
    }
}
