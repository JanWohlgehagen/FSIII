package dal;

import be.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUserDAO {

    private DBConnecting dbConnecting;

    public DBUserDAO(DBConnecting dbConnecting) {
        this.dbConnecting = dbConnecting;
    }

    public User getUserById(int id){
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [Person] WHERE Person_ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String role = resultSet.getString("Role");
                User user = new User(firstName,lastName);
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

    public List<User> getAllUser(){
        List<User> allUsers = new ArrayList<>();
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [Person]";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("Person_ID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String role = resultSet.getString("Role");

                User user = new User(firstName,lastName);
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
}
