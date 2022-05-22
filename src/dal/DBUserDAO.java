package dal;

import be.Credential;
import be.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUserDAO extends DBLoginDAO {

    private DBConnecting dbConnecting;

    public DBUserDAO(DBConnecting dbConnecting) {
        super(dbConnecting);
        this.dbConnecting = dbConnecting;
    }

    public User getUserById(int id) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [Person] JOIN Credentials on Credentials.Person_ID = Person.Person_ID WHERE Person.Person_ID = (?) AND Credentials.Person_ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String role = resultSet.getString("Role");
                String loginName = resultSet.getString("UserName");

                User user = new User(firstName, lastName);
                user.setCredential(new Credential(user.getIdProperty().get(), loginName));
                user.setUserType(role);
                user.setId(id);
                return user;
            }

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
            return null;
        }
        return null;
    }

    public List<User> getAllUser() {
        List<User> allUsers = new ArrayList<>();
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [Person] JOIN Credentials on Credentials.Person_ID = Person.Person_ID";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("Person_ID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String role = resultSet.getString("Role");
                String loginName = resultSet.getString("UserName");

                User user = new User(firstName, lastName);
                user.setCredential(new Credential(user.getIdProperty().get(), loginName));
                user.setUserType(role);
                user.setId(id);
                allUsers.add(user);
            }

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
            return null;
        }
        return allUsers;
    }

    public User newUser(User newUser) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "INSERT INTO Person (FirstName, LastName, Role) VALUES ((?), (?), (?))";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, newUser.getFirstNameProperty().get());
            preparedStatement.setString(2, newUser.getLastNameProperty().get());
            preparedStatement.setString(3, newUser.getUserTypeStringProperty().get());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                newUser.setId(id);
                return newUser;
            }

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
            return null;
        }
        return null;

    }

    public void deleteUser(User user) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "DELETE FROM [Person] WHERE Person_ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getIdProperty().get());
            preparedStatement.execute();

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    public void editUser(User user) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "UPDATE [Person] SET FirstName = (?), LastName = (?) WHERE Person_ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getFirstNameProperty().get());
            preparedStatement.setString(2, user.getLastNameProperty().get());
            preparedStatement.setInt(3, user.getIdProperty().get());
            preparedStatement.execute();

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }
}
