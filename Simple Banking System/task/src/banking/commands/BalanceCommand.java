package banking.commands;

import banking.CommandShell;

public class BalanceCommand implements ICommand {
    CommandShell shell;
    public BalanceCommand(CommandShell shell) {
        this.shell=shell;
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println("Balance: "+shell.getAccounts().getBalance());
    }
}
