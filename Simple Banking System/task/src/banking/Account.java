package banking;

import java.util.Random;

public class Account {
    private final String cardNumber;
    private String pin;
    private double money=0;
    public Account(String cardNumber, String pin){
        this.cardNumber = cardNumber;
        this.pin = pin;
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public double getMoney() {
        return money;
    }
}
