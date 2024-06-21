package auth;

import database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    static DatabaseConnection databaseConnection;  //polaczenie bazy danych
    public void register(Account account) {  //rejestruje nowego uzytkownika w bazie danych
        try {
            PreparedStatement statement = databaseConnection.getConnection()
                    .prepareStatement("INSERT INTO auth_account(name, password) VALUES (?, ?)"); //wstawia nowy rekord do tabeli
            statement.setString(1, account.name());
            statement.setString(2, account.password());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean authenticate(String name, String password) {  //sprawdza czy hasla sa te sama
        Account account = getUser(name);
        if (account != null) {
            return password.equals(account.password());
        }
        return false;
    }
    public Account getUser(String name) {  //zwraca uzytkownika
        try {
            PreparedStatement statement = databaseConnection.getConnection()
                    .prepareStatement("SELECT password FROM auth.account WHERE name = ?");
            statement.setString(1, name);
            statement.execute();

            ResultSet result = statement.getResultSet();
            List<Account> accounts = new ArrayList<>();

            while (result.next()) { //to trzeba wiedziec
                String password = result.getString("password");
                Account account = new Account(name, password);
                accounts.add(account);
            }
            return accounts.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}