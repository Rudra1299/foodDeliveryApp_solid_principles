package com.deliveryApp;

import java.util.List;
import java.util.Map;

public class GroceryDeliveryServiceImp implements GroceryDeliveryService{
    //user details and required information storage for processing the request
    private String username;
    private String password;
    private Map<Integer, String> idToCustomer;
    private Map<Integer, List<String>> idToOrders;
    private Map<String, Double> orderMenu;
    private Map<Integer, Double> orderAmount;
    private static int offerCode = 1800;
    private Authenticator authenticObject; //reference for authentication instance
    private orderManager orderCreator; //reference for foodOrderManager instance

    public GroceryDeliveryServiceImp(String username, String password, Map<Integer, String> idToCustomer, Map<Integer,
            List<String>> idToOrders, Map<String, Double> orderMenu, Map<Integer, Double> orderAmount,
                                  Authenticator authenticObject, orderManager orderCreator) {
        this.username = username;
        this.password = password;
        this.idToCustomer = idToCustomer;
        this.idToOrders = idToOrders;
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
        return idToOrders;
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
            System.out.println("Your order id is " + id + " please store for future reference.");
            return true;
        }
        else {
            System.out.println("An invalid user");
            return false;
        }
    }

    @Override
    public void offersInGroceryStores() {
        System.out.println("The offer code is:" +  offerCode);
        offerCode++;
    }
}
