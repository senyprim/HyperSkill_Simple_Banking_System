package banking;

import banking.model.Account;
import banking.model.Accounts;
import org.sqlite.SQLiteDataSource;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repo {
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS card (" +
            "id INTEGER  PRIMARY KEY," +
            "number TEXT NOT NULL," +
            "pin TEXT," +
            "balance INTEGER DEFAULT 0)";
    private static final String PATH = "";

    public static Connection getConnection(String dbname) throws SQLException {
        String url = "jdbc:sqlite:"+PATH+dbname;
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        Connection con = dataSource.getConnection();
            if (con.isValid(5)) {
                    Statement command = con.createStatement();
                    command.executeUpdate(CREATE_TABLE);
                return con;
            }
        return null;
    }
}
