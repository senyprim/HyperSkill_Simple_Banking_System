package banking.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Accounts {
    private String  loginCardNumber="";
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    private static final String GET_ACCOUNT = "select * from card where number = ?";
    private static final String ADD_ACCOUNT = "insert into card (number,pin,balance) values (?,?,?)";
    private static final String UPDATE_ACCOUNT = "update card set pin=?, balance=? where number = ?";
    private static final String REMOVE_ACCOUNT = "delete from card where number=?";


    public Accounts(Connection connection){
        this.connection = connection;
    }

    public Account getAccount(String cardNumber) throws SQLException {
        if (Account.isNotValidNumber(cardNumber)){
            return null;
        }
        PreparedStatement st = connection.prepareStatement(GET_ACCOUNT);

        st.setString(1,cardNumber);
        ResultSet resultSet = st.executeQuery();
        if (!resultSet.next()) {
            return null;
        }
        return new Account(
                resultSet.getString("number")
                ,resultSet.getString("pin")
                ,resultSet.getInt("balance"));
    }

    public boolean isNotExist(String cardNumber) throws SQLException {
        Account account = getAccount(cardNumber);
        return account==null;
    }

    private boolean addAccount(Account account) throws SQLException {
        PreparedStatement st = connection.prepareStatement(ADD_ACCOUNT);
        st.setString(1,account.getCardNumber());
        st.setString(2,account.getPin());
        st.setInt(3,account.getMoney());
        int result = st.executeUpdate();
        return result > 0;
    }
    private boolean updateAccount(Account account) throws SQLException {
        PreparedStatement st = connection.prepareStatement(UPDATE_ACCOUNT);
        st.setString(3,account.getCardNumber());
        st.setString(1,account.getPin());
        st.setInt(2,account.getMoney());
        int result = st.executeUpdate();
        return result > 0;
    }
    public boolean addOrUpdateAccount(Account account) throws SQLException {
        String cardNumber=account.getCardNumber();
        if (isNotExist( cardNumber)) {
            return addAccount(account);
        } else {
            return updateAccount(account);
        }
    }
    public boolean isLogin(){
        return !loginCardNumber.isEmpty();
    }
    public boolean login(String cardNumber, String pin) throws SQLException {
        Account account = getAccount(cardNumber);
        if (account == null) return false;
        if (Objects.equals(account.getPin(),pin)){
            loginCardNumber=account.getCardNumber();
            return true;
        }
        return false;
    }
    public boolean logout(){
        loginCardNumber="";
        return true;
    }
    public boolean removeAccount(String cardNumber) throws SQLException {
        PreparedStatement st = connection.prepareStatement(REMOVE_ACCOUNT);
        st.setString(1, cardNumber);
        int result = st.executeUpdate();
        return result != 0;
    }
    public void removeCurrentAccount() throws SQLException {
        if (loginCardNumber.isEmpty()){
            throw new IllegalArgumentException("Not login accounts");
        }
        removeAccount(loginCardNumber);
        loginCardNumber="";
    }
    public void transferTo(Account toAccount, int money) throws SQLException {
        if (loginCardNumber.isEmpty()){
            throw new IllegalArgumentException("Not login account");
        }
        Account fromAccount = getAccount(loginCardNumber);
        if (isNotExist(toAccount.getCardNumber())){
            throw new IllegalArgumentException("You can't transfer money to the same account!");
        }
        if (Objects.equals(fromAccount.getCardNumber(),toAccount.getCardNumber())){
            throw new IllegalArgumentException("Such a card does not exist.");
        }
        if (money>fromAccount.getMoney()){
            throw new IllegalArgumentException("Not enough money!");
        }
        try{
            connection.setAutoCommit(false);
            fromAccount.setMoney(fromAccount.getMoney()-money);
            addOrUpdateAccount(fromAccount);

            toAccount.setMoney(toAccount.getMoney()+money);
            addOrUpdateAccount(toAccount);
            connection.commit();
        }
        catch (SQLException ex){
            connection.rollback();
        }
        finally {
            connection.setAutoCommit(true);
        }
    }
    public void addIncome(int money) throws SQLException {
        Account current = getAccount(loginCardNumber);
        current.AddIncome(money);
        addOrUpdateAccount(current);
    }

    public  Account generateNewAccount(Random rnd){
        return Account.createAccount(rnd);
    }

    public int getBalance () throws SQLException {
        Account current = getAccount(loginCardNumber);
        return  current.getMoney();
    }
}
