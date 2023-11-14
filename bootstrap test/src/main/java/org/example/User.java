package org.example;

import java.io.*;

public class User {
    private String name;
    private String password;
    private boolean isLogged;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public void login(String name, String password) {

        this.isLogged = true;
    }

    public String viewSettings() throws IOException {
        int ch;
        StringBuilder fileContent = new StringBuilder();
        File file = new File("src/main/resources/Settings.txt");
        FileReader fr=null;

        try
        {
            fr = new FileReader(file);
            while ((ch=fr.read())!=-1){
                fileContent.append((char) ch);
            }
            fr.close();
            return fileContent.toString();
        }
        catch (FileNotFoundException fe)
        {
            System.out.println("File not found");
            return fe.getMessage();
        }
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}
