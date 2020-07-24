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
        Account newAccount = shell.getAccounts().generateNewAccount(shell.rnd);
        boolean result = shell.getAccounts().addAccount(newAccount);
        if (result){
            try {
                Repo.saveAccount(shell.getConnection(),newAccount);

        System.out.printf("Your card has been created\n" +
                "Your card number:\n" +
                "%s\n" +
                "Your card PIN:\n" +
                "%s\n",newAccount.getCardNumber(),newAccount.getPin());
            } catch (SQLException throwables) {
                System.out.println(throwables.getMessage());
            }
        }
    }
}
