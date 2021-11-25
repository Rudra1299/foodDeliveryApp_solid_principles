package com.deliveryApp;

import java.util.*;

public class Application {

    public static void main(String[] args) {
        // required data for the code usually extracted from database
        Map<Integer, String> idToCustomer = new HashMap<>();
        Map<Integer, List<String>> idToOrders = new HashMap<>();
        Map<String, Double> orderMenu = new HashMap<>();
        Map<String, Double> groceryMenu = new HashMap<>();
        Map<Integer, Double>  orderAmount = new HashMap<>();
        Map<String, String> userAuth = new HashMap<>();
        Set<String> discountAvailers = new HashSet<>();
        Set<String> offerAvailers = new HashSet<>();;
        String username;
        String password;

        userAuth.put("Stephienie@gmail.com", "1234");
        userAuth.put("eddie@gmail.com", "1567");

        orderMenu.put("pizza", 500.00);
        orderMenu.put("burger", 120.00);
        orderMenu.put("noodles", 150.00);

        groceryMenu.put("flour", 180.00);
        groceryMenu.put("rice", 120.00);
        groceryMenu.put("wheat", 168.00);

        Scanner sc = new Scanner(System.in);
        Authenticator authenticObject = new emailAuthenticator(userAuth);
        DeliveryService request;
        orderManager orderCreator;

        //checking authentication to enter the valid user
        while(true) {
            while (true) {
                System.out.print("Enter the user email: ");
                username = sc.next();
                System.out.print("Enter the password: ");
                password = sc.next();
                System.out.print("Enter the type - 1. grocery 2. food: ");
                int choose = sc.nextInt();
                if (choose == 2) {
                    orderCreator = new foodOrderManager(idToOrders, orderMenu, orderAmount);
                    request = (foodDeliveryService) new foodDeliveryServiceImp(username, password, idToCustomer, idToOrders,
                            orderMenu, orderAmount,
                            authenticObject, orderCreator);
                } else {
                    orderCreator = new groceryOrderManager(idToOrders, groceryMenu, orderAmount);
                    request = (GroceryDeliveryService) new GroceryDeliveryServiceImp(username, password, idToCustomer,
                            idToOrders,
                            groceryMenu, orderAmount,
                            authenticObject, orderCreator);
                }
                boolean check = request.service();
                if (check)
                    break;

            }

            //invoking food refund service and discount service
            if (request instanceof foodDeliveryService) {

                if(!discountAvailers.contains(username)) {
                    System.out.print("you are eligible for discount want to avail now (Y/N) ?:");
                    char dis = sc.next().charAt(0);
                    if (dis == 'Y') {
                        ((foodDeliveryService) request).discountAvail();
                        discountAvailers.add(username);
                    } else {
                        System.out.println("Discount is stored for future");
                    }
                }

                System.out.print("Are you willing to get refund? (Y/N) : ");
                char response = sc.next().charAt(0);
                if (response == 'Y') {
                    request = new foodDeliveryServiceAndRefund(username, password, idToCustomer, idToOrders,
                            orderMenu, orderAmount,
                            authenticObject, orderCreator);
                    request.service();
                } else {
                    System.out.print("Thankyou for visiting");
                }
            }
            else {
                if(!offerAvailers.contains(username)){
                    System.out.print("Do you want to avail offers now (Y/N)?: ");
                    char offer = sc.next().charAt(0);
                    if (offer == 'Y') {
                        ((GroceryDeliveryService) request).offersInGroceryStores();
                        offerAvailers.add(username);
                    }
                    System.out.println("Thankyou for visiting");
                }
                else {
                    System.out.println("Thankyou for visiting");
                }

            }

            System.out.print("another order (Y/N)?:");
            char check2 = sc.next().charAt(0);
            if (check2 == 'N')
                break;
        }

    }
}
