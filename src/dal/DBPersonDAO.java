package dal;

import be.Credential;
import be.Person;

import java.sql.*;

public class DBPersonDAO {

    private DBConnecting dbConnecting;

    public DBPersonDAO(DBConnecting dbConnecting) {
        this.dbConnecting = dbConnecting;
    }

    public Person getPersonById(int id){
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [Person] WHERE ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String role = resultSet.getString("Role");
                Person person = new Person(firstName,lastName);
                person.setUserType(role);
                person.setId(id);
                return person;
            }

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
            return null;
        }
        return null;
    }
}
