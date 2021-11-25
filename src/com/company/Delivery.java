package com.company;

public interface Delivery {
    boolean Authentication(String username, String password);
    void placeOrder();
    boolean service();
    double calculateTotalAmount();
    void discountAvail();
    void refundService();
}
