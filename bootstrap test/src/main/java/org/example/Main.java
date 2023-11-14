package org.example;

import controller.JsonReader;
import model.Account;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        User user1 = new User("toto", "toto");
        System.out.println(user1.viewSettings());
        Admin admin1 = new Admin("Admin", "admin");
        admin1.changeSettings("bonjour ça va", "connard");
        System.out.println(user1.viewSettings());
        JsonReader jsonReader1 = new JsonReader("src/main/resources/Accounts.json");
        Account account1 =  new Account(jsonReader1.getName(), jsonReader1.getCash());
        System.out.println(account1.toString());

    }
}