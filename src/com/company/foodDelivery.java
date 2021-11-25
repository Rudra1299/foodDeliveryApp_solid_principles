package com.company;

import java.util.*;

public class foodDelivery implements Delivery{
    private static Integer counter;
    {
        counter = 0;
    }
    private Map<String, String> userAuth; //user authentucation details
    private String username;
    private String password;
    private List<String> food = new ArrayList<>(); //food order lists
    private Map<Integer, String> idToCustomer; //id to customer mapping
    private Map<Integer, List<String>> idToFoodOrders; //store food orders according to customer id
    private Map<String, Double> orderMenu;
    private Map<Integer, Double> orderAmount;

    public Map<String, String> getUserAuth() {
        return userAuth;
    }

    public Map<Integer, Double> getOrderAmount() {
        return orderAmount;
    }

    public foodDelivery(String username, String password, Map<String, String> userAuth,
                        Map<Integer, String> idToCustomers, Map<Integer, List<String>> idToFoodOrders,
                        Map<String, Double> orderMenu, Map<Integer, Double>  orderAmount){
        this.username = username;
        this.password = password;
        this.userAuth = userAuth;
        this.idToCustomer = idToCustomers;
        this.idToFoodOrders = idToFoodOrders;
        this.orderMenu = orderMenu;
        this.orderAmount = orderAmount;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getFood() {
        return food;
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


    //verify customer details
    public boolean Authentication(String username, String password) {
        if(!this.userAuth.containsKey(username))
            return false;
        else if(!password.equals(this.userAuth.get(username)))
            return false;
        else {
            return true;
        }
    }
    //this method is responsible for placing food order
    public void placeOrder() {
        int i = 1;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of orders:");
        int numberOfOrders = sc.nextInt();
        System.out.println("The menu contains: ");
        for(String dish : orderMenu.keySet()) {
            System.out.println(dish);
        }
        for(int j = 0; j < numberOfOrders; j++){
            String dish = sc.next();
            if(!orderMenu.containsKey(dish)) {
                System.out.println("This dish is not in the menu.");
                j--;
                continue;
            }
            this.food.add(dish);
        }
        System.out.println("Your order is placed");
        System.out.println("The order list is:");
        for(String dish : food) {
            System.out.println(i + ":" + dish);
            i++;
        }
        this.idToCustomer.put(counter, this.username);
        this.idToFoodOrders.put(counter, this.food);
        double Amount = calculateTotalAmount();
        System.out.println("The total amount is: " + Amount);
        System.out.println("Our order id is: " + counter + " please store it for future reference");
    }

    public boolean service() {
        if(Authentication(this.username, this.password)) {
            System.out.println("A valid user, proceed  with your order");
            placeOrder();
            return true;
        }
        else {
            System.out.println("An invalid user");
            return false;
        }
    }

    public double calculateTotalAmount() {
        double Amount = 0;
        for(String dish : this.food) {
            Amount += this.orderMenu.get(dish);
        }

        return Amount;
    }

    public void discountAvail() {
        double discount = 0.1;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the order id: ");
        Integer id = sc.nextInt();
        if(!orderAmount.containsKey(id)) {
            System.out.println("Cannot avail discount as no food order placed");
            return;
        }
        orderAmount.computeIfPresent(id, (k,v) -> v - v * discount);

        System.out.println("The discount is availed, The amount is: " +  this.orderAmount.get(id));

    }

    public void refundService() {
        Scanner sc = new Scanner(System.in);
        System.out.print("The the order id: ");
        Integer id = sc.nextInt();
        this.getIdToCustomer().remove(id);
        this.getIdToCustomer().remove(id);
        Double Amount = this.orderAmount.get(id);
        this.orderAmount.remove(id);
        System.out.println("Your refund amount is: " + Amount);
    }

}
