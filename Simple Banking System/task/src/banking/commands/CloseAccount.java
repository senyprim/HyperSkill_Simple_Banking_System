package banking.commands;

import banking.CommandShell;

import java.sql.SQLException;

public class CloseAccount implements ICommand{
    CommandShell shell ;
    public CloseAccount(CommandShell shell) {
        this.shell = shell;
    }

    @Override
    public void execute() {
        try {
            shell.getAccounts().removeCurrentAccount();
            System.out.println("The account has been closed!");
        }
        catch (SQLException exception){
            System.out.println("The account has Not been closed!");
        }
    }
}
