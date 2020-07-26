package banking.commands;

import banking.CommandShell;

public class AccountCommands implements ICommand {
    private CommandShell shell ;
    private boolean exit=false;
    public AccountCommands(CommandShell shell) {
        this.shell = shell;
    }

    @Override
    public void execute() {
        while (!exit && shell.getAccounts().isLogin()){
            System.out.println(
                    "1. Balance\n" +
                    "2. Add income\n" +
                    "3. Do transfer\n" +
                    "4. Close account\n" +
                    "5. Log out\n" +
                    "0. Exit");
            String input = shell.getScanner().nextLine();
            System.out.println();
            switch (input){
                case "1":new BalanceCommand(this.shell).execute();break;
                case "2":new AddIncomeCommand(this.shell).execute();break;
                case "3":new DoTransfer(this.shell).execute();break;
                case "4":new CloseAccount(this.shell).execute();break;
                case "5":new LogOutCommand(this.shell).execute();break;
                case "0":shell.setExit(true);return;
            }
            System.out.println();
        }
    }
}
