package banking.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Accounts {
    private List<Account> accounts;
    private Account currentAccount;


    public boolean isLogin(){
        return currentAccount!=null;
    }
    public Accounts(List<Account> accounts){
        this.accounts = accounts;
    }
    public Accounts(){
        this(new ArrayList<>());
    }
    public boolean addAccount(Account account){
        if (!isContains(account)){
            this.accounts.add(account);
            return true;
        }
        return false;
    }

    public boolean isContains(Account account){
        return accounts.stream().anyMatch(item ->
                Objects.equals(account.getCardNumber(),item.getCardNumber())
                        && Objects.equals(account.getPin(),item.getPin()));
    }

    public boolean login(String cardNumber, String pin){
        for (Account account : accounts){
            if (Objects.equals(account.getCardNumber(),cardNumber)
                && Objects.equals(account.getPin(),pin)){
                currentAccount = account;
                return true;
            }
        }
        return false;
    }

    public boolean logout(){
        currentAccount = null;
        return true;
    }

    public  Account generateNewAccount(Random rnd){
        return new Account(generateCardNumber(rnd),generatePin(rnd));
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

    public int getBalance (){
        return currentAccount.getMoney();
    }
}
