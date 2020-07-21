package banking;

public class BalanceCommand implements ICommand{
    CommandShell shell;
    public BalanceCommand(CommandShell shell) {
        this.shell=shell;
    }

    @Override
    public void execute() {
        System.out.println();
        System.out.println(shell.getAccounts().getBalance());
    }
}
