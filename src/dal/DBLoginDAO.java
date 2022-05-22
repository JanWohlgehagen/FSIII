package dal;

import be.Credential;

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
            preparedStatement.setString(1, userName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("Person_ID");
                String userPassword = resultSet.getString("Password");
                Credential credential = new Credential(id, userName);
                credential.setPassword(userPassword);
                return credential;
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

    public void updatePassword(Credential credential) {
        try (Connection connection = dbConnecting.getConnection()) {
            System.out.println("sædkmgædslmgs");
            String sql = "UPDATE [Credentials] SET Password = (?) WHERE Person_ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, credential.getPassword());
            preparedStatement.setInt(2, credential.getUserId());
            preparedStatement.execute();

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    public void updateLoginName(Credential credential) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "UPDATE [Credentials] SET UserName = (?) WHERE Person_ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, credential.getUserName());
            preparedStatement.setInt(2, credential.getUserId());
            preparedStatement.execute();

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }
}
