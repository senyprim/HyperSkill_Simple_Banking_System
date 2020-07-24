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
    private static final String CREATE_TABLE = "CREATE TABLE card (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "number TEXT," +
            "pin TEXT," +
            "balance INTEGER DEFAULT 0)";
    private static final String ADD_ACCOUNT = "insert into card (number,pin,balance) values (?,?,?)";
    private static final String GET_ACCOUNT_ID = "select id from card where number = ? and pin = ? and balance = ?";
    private static final String PATH = "";

    public static void saveAccount(Connection connection, Account account) throws SQLException {
        if (getAccountId(connection,account)!=-1) return;

        PreparedStatement st = connection.prepareStatement(ADD_ACCOUNT);

        st.setString(1,account.getCardNumber());
        st.setString(2,account.getPin());
        st.setInt(3,account.getMoney());
        int result = st.executeUpdate();
    }

    public static int getAccountId(Connection connection,Account account) throws SQLException {
        PreparedStatement st = connection.prepareStatement(GET_ACCOUNT_ID);

        st.setString(1,account.getCardNumber());
        st.setString(2,account.getPin());
        st.setInt(3,account.getMoney());
        if (!st.execute()) {
            return -1;
        }
        ResultSet resultSet = st.getResultSet();
        if (!resultSet.next()) {
            return -1;
        }
        return resultSet.getInt("id");
    }

    public static Connection getConnection(String dbname) throws SQLException {
        String url = "jdbc:sqlite:"+PATH+dbname;
        boolean isExist = Files.exists(Path.of(PATH+dbname));

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(url);

        Connection con = dataSource.getConnection();
            if (con.isValid(5)) {
                if (!isExist){
                    Statement command = con.createStatement();
                    command.executeUpdate(CREATE_TABLE);
                }
                return con;
            }
        return null;
    }

    public static Accounts getAccounts(Connection connection) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet select = statement.executeQuery("select * from card");
        while (select.next()) {
            Account newAccount = new Account(
                    select.getString("number")
                    , select.getString("pin")
                    , select.getInt("balance"));
            accounts.add(newAccount);
        }
        return new Accounts(accounts);
    }




}
