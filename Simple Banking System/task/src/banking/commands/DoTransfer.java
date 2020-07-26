package banking.commands;

import banking.CommandShell;
import banking.model.Account;

import java.sql.SQLException;

public class DoTransfer implements ICommand{
    CommandShell shell;
    public DoTransfer(CommandShell shell) {
        this.shell = shell;
    }

    @Override
    public void execute()  {
        System.out.println("Transfer\n" +
                "Enter card number:");
        String card = shell.getScanner().nextLine();
        if (!Account.isNotValidNumber(card)){
            System.out.println("Probably you made mistake in the card number. Please try again!");
            return;
        }
        try {
        Account transferTo = shell.getAccounts().getAccount(card);
        if (transferTo==null){
            throw new IllegalArgumentException("Such a card does not exist.");
        }
        System.out.println("Enter how much money you want to transfer:");
        int money = Integer.parseInt(shell.getScanner().nextLine());

            shell.getAccounts().transferTo(transferTo,money);
            System.out.println("Success!");
        }catch (IllegalArgumentException | SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
}
