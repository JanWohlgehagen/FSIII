package dal;

import be.Credential;
import be.user.User;

import java.sql.*;

public class DBLoginDAO {

    private DBConnecting dbConnecting;

    public DBLoginDAO(DBConnecting dbConnecting) {
        this.dbConnecting = dbConnecting;
    }

    public Credential checkCredential(String userName) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [Credentials] WHERE UserName = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,userName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("Person_ID");
                String userPassword = resultSet.getString("Password");
                return new Credential(id,userName,userPassword);
            }

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
            return null;
        }
        return null;
    }



    public void createNewLoginUser(Credential credential) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "INSERT INTO Credentials (Person_ID, UserName, Password) VALUES ((?), (?), (?))";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, credential.getUserId());
            preparedStatement.setString(2, credential.getUserName());
            preparedStatement.setString(3, credential.getPassword());

            preparedStatement.execute();

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    /**
    public void deleteLoginUser(Credential credential){
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "DELETE FROM Credentials WHERE Person_ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, credential.getUserId());

            preparedStatement.execute();

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }
     **/

    public void editLoginUser(Credential credential) {
        try(Connection connection = dbConnecting.getConnection()) {
            String sql = "UPDATE [Credentials] SET UserName = (?), Password = (?) WHERE Person_ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, credential.getUserName());
            preparedStatement.setString(2, credential.getPassword());
            preparedStatement.setInt(3, credential.getUserId());
            preparedStatement.execute();

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }
}
