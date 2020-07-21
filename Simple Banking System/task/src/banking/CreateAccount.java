package banking;

public class CreateAccount implements ICommand{
    private CommandShell shell;
    public CreateAccount(CommandShell shell) {
        this.shell = shell;
    }
    @Override
    public void execute() {
        Account newAccount = shell.getAccounts().generateNewAccount(shell.rnd);
        shell.getAccounts().addAccount(newAccount);
        System.out.printf("Your card has been created\n" +
                "Your card number:\n" +
                "%s\n" +
                "Your card PIN:\n" +
                "%s\n",newAccount.getCardNumber(),newAccount.getPin());
    }
}
