package banking.commands;

import banking.CommandShell;

public class LoginAccount implements ICommand {
    CommandShell shell;
    public LoginAccount(CommandShell shell) {
        this.shell=shell;
    }

    @Override
    public void execute() {
        System.out.println("Enter your card number:");
        String cardNumber = shell.getScanner().nextLine();
        System.out.println("Enter your PIN:");
        String pin = shell.getScanner().nextLine();
        System.out.println();
        try {
            boolean answer = shell.getAccounts().login(cardNumber,pin);
            if (answer) {
                System.out.println("You have successfully logged in!\n");
                new AccountCommands(this.shell).execute();
            } else {
                System.out.println("Wrong card number or PIN!");
            }
        } catch (Exception e){
            System.out.println("Login fail");
        }

    }
}
