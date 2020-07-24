package banking.model;

import java.util.Random;

public class Account {
    private final String cardNumber;
    private String pin;
    private int money=0;
    public Account(String cardNumber, String pin,int balance){
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.money = balance;
    }
    public Account(String cardNumber, String pin){
        this(cardNumber,pin,0);
    }


    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public int getMoney() {
        return money;
    }

    public boolean isValidNumber(String number){
        if (number.length()!=16) return false;
        return getCheckSum(number)%10==0;
    }
    public static int getCheckSum(String number){
        String str = number.substring(0,number.length()-1);
        int sum = number.charAt(number.length()-1)-'0';
        for(int index = 0; index < str.length(); index++){
            int digit = str.charAt(index) - '0';
            if (index%2==0) digit*=2;
            if (digit>9) digit-=9;
            sum+=digit;
        }
        return sum;
    }
    public static int getCheckDigit(String cardNumber){
        int result = cardNumber.charAt(cardNumber.length()-1)-'0';
        int checkSum=getCheckSum(cardNumber);
        return (10-(checkSum-result)%10)%10;
    }

}
