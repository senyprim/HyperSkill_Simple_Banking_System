package banking;

import banking.model.Accounts;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        if (args==null || args.length<2) return;
        int index = Arrays.asList(args).indexOf("-filename");
        if (index!=-1 || args.length<index+1) return;
         */
        String dbname = "db.s3db";//args[index+1];

        String url = "jdbc:sqlite:"+dbname;


        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        try (Connection con = dataSource.getConnection()) {
            if (con.isValid(5)) {
                System.out.println("Connection is valid.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        CommandShell shell = new CommandShell(new Scanner(System.in),new Accounts());
        shell.run();
    }
}
