package com.deliveryApp;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class foodDeliveryServiceImp implements foodDeliveryService{

    //user details and required information storage for processing the request
    private String username;
    private String password;
    private Map<Integer, String> idToCustomer;
    private Map<Integer, List<String>> idToFoodOrders;
    private Map<String, Double> orderMenu;
    private Map<Integer, Double> orderAmount;

    private Authenticator authenticObject; //reference for authentication instance
    private orderManager orderCreator; //reference for foodOrderManager instance

    public foodDeliveryServiceImp(String username, String password, Map<Integer, String> idToCustomer, Map<Integer,
            List<String>> idToFoodOrders, Map<String, Double> orderMenu, Map<Integer, Double> orderAmount,
            Authenticator authenticObject, orderManager orderCreator) {
        this.username = username;
        this.password = password;
        this.idToCustomer = idToCustomer;
        this.idToFoodOrders = idToFoodOrders;
        this.orderMenu = orderMenu;
        this.orderAmount = orderAmount;
        this.authenticObject = authenticObject;
        this.orderCreator = orderCreator;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Map<Integer, String> getIdToCustomer() {
        return idToCustomer;
    }

    public Map<Integer, List<String>> getIdToFoodOrders() {
        return idToFoodOrders;
    }

    public Map<String, Double> getOrderMenu() {
        return orderMenu;
    }

    public Map<Integer, Double> getOrderAmount() {
        return orderAmount;
    }

    //method for authentication and order placement
    @Override
    public boolean service() {
        if(authenticObject.authentication(this.username, this.password)) {
            System.out.println("A valid user, proceed  with your order");
            Integer id = orderCreator.placeOrder();
            this.idToCustomer.put(id, this.username);
            System.out.println("Your order id is " + id + " please store it for future reference.");
            return true;
        }
        else {
            System.out.println("An invalid user");
            return false;
        }
    }


    //method to avail discount on the orders purchase on total amount
    @Override
    public void discountAvail() {
        double discount = 0.1;
        System.out.print("Enter the ordr id: ");
        Scanner sc = new Scanner(System.in);
        Integer id = sc.nextInt();
        if(!this.orderAmount.containsKey(id)) {
            System.out.println("Cannot avail discount as no food order placed");
            return;
        }
        orderAmount.computeIfPresent(id, (k,v) -> v - v * discount);

        System.out.println("The discount is availed, The amount is: " +  this.orderAmount.get(id));

    }


}
