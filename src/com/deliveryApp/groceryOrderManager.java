package com.deliveryApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class groceryOrderManager implements orderManager{

    private static Integer counterId = 1000;
    private List<String>grocery;
    private Map<Integer, String> idToCustomer;
    private Map<Integer, List<String>> idToFoodOrders;
    private Map<String, Double> orderMenu;
    private Map<Integer, Double> orderAmount;
    public groceryOrderManager(Map<Integer, List<String>> idToFoodOrders,
                               Map<String, Double> orderMenu, Map<Integer, Double> orderAmount) {
        this.idToCustomer = idToCustomer;
        this.idToFoodOrders = idToFoodOrders;
        this.orderMenu = orderMenu;
        this.grocery = new ArrayList<>();
        this.orderAmount = orderAmount;
    }

    @Override
    public int  placeOrder() {
        int i = 1;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of orders:");
        int numberOfOrders = sc.nextInt();
        System.out.println("The menu contains: ");
        for(String groc : orderMenu.keySet()) {
            System.out.println(groc);
        }
        for(int j = 0; j < numberOfOrders; j++){
            String dish = sc.next();
            if(!orderMenu.containsKey(dish)) {
                System.out.println("This dish is not in the menu.");
                j--;
                continue;
            }
            this.grocery.add(dish);
        }
        System.out.println("Your order is placed");
        System.out.println("The order list is:");
        for(String dish : this.grocery) {
            System.out.println(i + ":" + dish);
            i++;
        }
        this.idToFoodOrders.put(counterId, this.grocery);
        double Amount = amountCalculator.calculateTotalAmount(grocery, orderMenu);
        this.orderAmount.put(counterId, Amount);
        System.out.println("The total amount is: " + Amount);
        counterId++;
        return counterId - 1;
    }
}
