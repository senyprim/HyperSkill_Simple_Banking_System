package banking;

import banking.model.Accounts;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        if (args==null || args.length<2) return;
        int index = Arrays.asList(args).indexOf("-fileName");
        if (index==-1 || args.length<index+1) return;

        String dbname = args[index+1];
        try(Connection connection = Repo.getConnection(dbname)){
            if (connection==null){
                throw new IllegalStateException("wrong connection");
            }
            CommandShell shell = new CommandShell(new Scanner(System.in),connection);
            shell.run();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
