package banking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(Account.getCheckSum("4000004093485741"));
        System.out.println(Account.getCheckDigit("4000004093485741"));
        CommandShell shell = new CommandShell(new Scanner(System.in),new Accounts());
        shell.run();
    }
}
