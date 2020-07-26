package banking.commands;

import banking.CommandShell;

import java.sql.SQLException;

public class BalanceCommand implements ICommand {
    CommandShell shell;
    public BalanceCommand(CommandShell shell) {
        this.shell=shell;
    }

    @Override
    public void execute() {
        try{
            System.out.println("Balance: "+shell.getAccounts().getBalance());
        } catch (SQLException exception){
            System.out.println("SQL Error"+exception.getMessage());
        }
    }
}
