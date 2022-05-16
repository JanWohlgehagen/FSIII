package dal;

import be.WClass;
import be.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBClassDAO {

    private DBConnecting dbConnecting;

    public DBClassDAO(DBConnecting dbConnecting) {
        this.dbConnecting = dbConnecting;
    }

    public WClass createClass(WClass wClass){
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "INSERT INTO [Class](Name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, wClass.getNameProperty().get());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("Class_ID");
                wClass.setId(id);
            }

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
            return null;
        }
        return wClass;
    }

    public void deleteClass(WClass wClass) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "DELETE FROM [Class] WHERE Class_ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, wClass.getIdProperty().get());

            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllStudentInClass(WClass wClass){
        List<User> allStudent = new ArrayList<>();
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [Person] JOIN ClassStudents ON ClassStudents.Student_ID = Person.Person_ID WHERE ClassStudents.[Class_ID] = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, wClass.getIdProperty().get());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("Person_ID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String role = resultSet.getString("Role");
                User user = new User(firstName,lastName);
                user.setUserType(role);
                user.setId(id);
                allStudent.add(user);
            }

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
            return null;
        }
        return allStudent;
    }

    public void addStudentToClass(User user, WClass wClass){
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "INSERT INTO [ClassStudents] (Student_ID, Class_ID) VALUES ((?),(?))";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, user.getIdProperty().get());
            preparedStatement.setInt(2, wClass.getIdProperty().get());

            preparedStatement.execute();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }

    }
}
