package banking.commands;

import banking.Repo;
import banking.model.Account;
import banking.CommandShell;

import java.sql.SQLException;

public class CreateAccount implements ICommand {
    private CommandShell shell;
    public CreateAccount(CommandShell shell) {
        this.shell = shell;
    }
    @Override
    public void execute()  {
        Account newAccount = Account.createAccount(shell.rnd);
        try {
            boolean result = shell.getAccounts().addOrUpdateAccount(newAccount);
            if (result){
            System.out.printf("Your card has been created\n" +
                              "Your card number:\n" +
                              "%s\n" +
                              "Your card PIN:\n" +
                              "%s\n",newAccount.getCardNumber(),newAccount.getPin());
            }
        } catch (SQLException throwables) {
        System.out.println(throwables.getMessage());
    }
  }
}
