package banking;

import banking.commands.CreateAccount;
import banking.commands.LoginAccount;
import banking.model.Accounts;

import java.util.Random;
import java.util.Scanner;

public class CommandShell {
    private Scanner scanner;
    private Accounts accounts;
    private boolean isExit = false;
    public final Random rnd;

    public CommandShell(Scanner scanner,Accounts accounts){
        this.scanner=scanner;
        this.accounts=accounts;
        this.rnd = new Random();
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Accounts getAccounts() {
        return accounts;
    }

    public void run(){
        while (!isExit){
            System.out.println(
                    "1. Create an account\n" +
                    "2. Log into account\n" +
                    "0. Exit");
            String input = scanner.nextLine();
            System.out.println();
            switch (input) {
                case "1": new CreateAccount(this).execute();break;
                case "2": new LoginAccount(this).execute();break;
                case "0": System.out.println("Bye!");return;
            }
            System.out.println();
        }

    }

}
