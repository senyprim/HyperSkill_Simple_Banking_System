package banking;

public class AccountCommands implements ICommand{
    private CommandShell shell ;
    private boolean exit=false;
    public AccountCommands(CommandShell shell) {
        this.shell = shell;
    }

    @Override
    public void execute() {
        while (!exit && shell.getAccounts().isLogin()){
            System.out.println("1. Balance\n" +
                    "2. Log out\n" +
                    "0. Exit");
            String input = shell.getScanner().nextLine();
            switch (input){
                case "1":new BalanceCommand(this.shell).execute();break;
                case "2":new LogOutCommand(this.shell).execute();break;
                case "0":shell.setExit(true);return;
            }
            System.out.println();
        }
    }
}
