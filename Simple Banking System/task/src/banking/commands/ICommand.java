package banking.commands;

import java.sql.SQLException;

public interface ICommand {
    void execute() throws SQLException;
}
