package dal;

import be.Credential;
import com.microsoft.sqlserver.jdbc.SQLServerException;

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
}
