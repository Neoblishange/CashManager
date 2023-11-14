package model;

public class Account {
    private String name;
    private String cash;

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", cash='" + cash + '\'' +
                '}';
    }

    public Account(String name, String cash) {
        this.name = name;
        this.cash = cash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }
}
