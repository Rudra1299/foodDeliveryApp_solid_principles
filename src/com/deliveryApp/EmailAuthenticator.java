package com.deliveryApp;

import java.util.Map;

public class EmailAuthenticator implements Authenticator{
    private Map<String, String> userAuth; //Storage of username - passwords

    public EmailAuthenticator(Map<String, String> userAuth) {
        this.userAuth = userAuth;
    }

    @Override
    public boolean authentication(String username, String password) {
        if(!this.userAuth.containsKey(username))
            return false;
        else if(!password.equals(this.userAuth.get(username)))
            return false;
        else {
            return true;
        }
    }
}
