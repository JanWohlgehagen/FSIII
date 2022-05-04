package dal;

import be.user.User;

import java.sql.*;

public class DBPersonDAO {

    private DBConnecting dbConnecting;

    public DBPersonDAO(DBConnecting dbConnecting) {
        this.dbConnecting = dbConnecting;
    }

    public User getPersonById(int id){
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [Person] WHERE ID = (?)";
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
}
