package banking.commands;

import banking.CommandShell;

public class LogOutCommand implements ICommand {
    CommandShell shell ;
    public LogOutCommand(CommandShell shell) {
        this.shell = shell;
    }

    @Override
    public void execute() {
        shell.getAccounts().logout();
        System.out.println();
        System.out.println("You have successfully logged out!");
    }
}
