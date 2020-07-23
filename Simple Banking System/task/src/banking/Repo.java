package banking;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Repo {
    private static final String CREATE_TABLE = " create table cards (" +
            "id integer," +
            "number text," +
            "pin text," +
            "balance integer default 0";
    public static void add
    public static void fillDb(Connection con) throws SQLException {
        Statement command = con.createStatement();
        command.executeUpdate(CREATE_TABLE);
    }

}
