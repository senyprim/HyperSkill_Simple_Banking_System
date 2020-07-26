package banking.model;

import java.util.Objects;
import java.util.Random;

public class Account {
    private final String cardNumber;
    private String pin;
    private int money;
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
    public void setPin(String pin){
        this.pin=pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return money == account.money &&
                cardNumber.equals(account.cardNumber) &&
                Objects.equals(pin, account.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, pin, money);
    }

    public int getMoney() {
        return money;
    }
    public void setMoney(int money){
        this.money=money;
    }
    public void AddIncome(int income){
        this.money+=income;
    }
    public static boolean isNotValidNumber(String number){
        if (number.length()!=16) return true;
        return getCheckSum(number)%10!=0;
    }

    public static Account createAccount(Random rnd){
        return new Account(generateCardNumber(rnd),generatePin(rnd));
    }

    private static int getCheckSum(String number){
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
    private static int getCheckDigit(String cardNumber){
        int result = cardNumber.charAt(cardNumber.length()-1)-'0';
        int checkSum=getCheckSum(cardNumber);
        return (10-(checkSum-result)%10)%10;
    }

    private static String generateCardNumber(Random rnd){
        String number = "400000"+generateNumber(10,rnd);
        return number.substring(0,15)+Account.getCheckDigit(number);
    }

    private static String generatePin(Random rnd){
        return generateNumber(4,rnd);
    }

    private static String generateNumber(int length,Random rnd){
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < length; i++){
            stb.append(rnd.nextInt(10));
        }
        return stb.toString();
    }
}
