package com.company;
import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner sc = new Scanner(System.in);
        Map<String, String> userAuth = new HashMap<>();
        userAuth.put("Stepheinie@gmail.com", "1234");
        userAuth.put("eddie@gmail.com", "1567");

        System.out.print("Enter username: ");
        String username = sc.next();
        System.out.print("Enter password: ");
        String password = sc.next();

        Map<Integer, String> idToCustomers = new HashMap<Integer, String>();
        Map<Integer, List<String>> idToFoodOrders = new HashMap<>();
        Map<String, Double> orderMenu = new HashMap<>();
        Map<String, Double> groceryMenu = new HashMap<>();
        Map<Integer, Double> orderAmount = new HashMap<>();

        orderMenu.put("pizza", 500.0);
        orderMenu.put("burger", 150.0);
        orderMenu.put("noodles", 120.0);

        groceryMenu.put("flour", 180.00);
        groceryMenu.put("rice", 120.00);
        groceryMenu.put("wheat", 168.00);

        //menu selection
            System.out.print("Enter the type - 1. grocery 2. food: " );
            int type = sc.nextInt();
            if(type == 2) {
                FoodDelivery request = new FoodDelivery(username, password, userAuth, idToCustomers, idToFoodOrders,
                        orderMenu, orderAmount);
                request.service();
                System.out.println("Do you want to avail discount now (Y/N):");
                char dis = sc.next().charAt(0);
                if(dis == 'Y') {
                    request.discountAvail();
                }

                System.out.print("Do you want refund? (Y/N):");
                char ch = sc.next().charAt(0);
                if(ch == 'Y') {
                    request.refundService();
                }

                System.out.println("Thankyou for visiting");
            }
            else {
                GroceryDelivery request = new GroceryDelivery(username, password, userAuth, idToCustomers, idToFoodOrders,
                        groceryMenu, orderAmount);
                request.service();
                System.out.print("Do you want to avail offers now (Y/N):");
                char dis = sc.next().charAt(0);
                if(dis == 'Y') {
                    request.offersInGroceryStore();
                    request.discountAvail();
                }
            }

    }
}

    /*String username, String password, Map<String, String> userAuth,
        Map<Integer, String> idToCustomers, Map<Integer, List<String>> idToFoodOrders,
        Map<String, Double> orderMenu,Map<Integer, Double>  orderAmount*/