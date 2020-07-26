package banking.commands;

import banking.CommandShell;
import banking.Repo;

import java.sql.SQLException;

public class AddIncomeCommand implements ICommand{
    CommandShell shell;
    public AddIncomeCommand(CommandShell shell) {
        this.shell=shell;
    }

    @Override
    public void execute()  {
        System.out.println("Enter income:");
        int income = Integer.parseInt(shell.getScanner().nextLine());
        try{
            shell.getAccounts().addIncome(income);
            System.out.println("Income was added!");
        } catch (Exception ex){
            System.out.println("Income command fail");
        }
    }
}
