package com.qkrmekem.autocommittest.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component(value = "userName")
public class UserName {
//    @Value("#{myData['git.username']}")
    @Value("${git.username}")
    private String username;

    public String getUsername() {
        System.out.println("username = " + username);
        return username;
    }
}
