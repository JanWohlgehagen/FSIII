package dal;

import be.Borger;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBCitizenDAO {
    private DBConnecting dbConnecting;

    public DBCitizenDAO(DBConnecting dbConnecting) throws IOException {
        this.dbConnecting = dbConnecting;
    }

    public void deleteCitizen(Borger borger)
    {
        try(Connection connection = dbConnecting.getConnection())
        {
            String sql = "DELETE FROM TABLE [Borger] WHERE ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, borger.IDProperty().get());
            preparedStatement.executeQuery();

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateCitizen(Borger borger)
    {
        try(Connection connection = dbConnecting.getConnection())
        {
            String sql ="UPDATE [Borger] SET FirstName = (?), LastName = (?) WHERE ID =(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, borger.firstNameProperty().get());
            preparedStatement.setString(2, borger.lastNameProperty().get());
            preparedStatement.setInt(3, borger.IDProperty().get());

            preparedStatement.executeQuery();

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Kunne ikke opdatere borgeren", ButtonType.OK);
        alert.setTitle("FSIII");
        alert.show();
    }

    public Borger createCitizen(Borger borger)
    {
        try(Connection connection = dbConnecting.getConnection())
        {
            String sql = "INSERT INTO [Borger] VALUES (FirstName, LastName) = (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, borger.firstNameProperty().get());
            preparedStatement.setString(2, borger.lastNameProperty().get());

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                borger.setID(resultSet.getInt(1));
                return borger;
            }
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Kunne ikke lave borgeren, tjek din forbindelse.", ButtonType.OK);
        alert.setTitle("FSIII");
        alert.show();
        return null;
    }
    public List<Borger> getAllCitizens()
    {
        List<Borger> listOfCitizens = new ArrayList<>();
        try(Connection connection =dbConnecting.getConnection())
        {
            String sql = "SELECT * FROM [Borger]";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int ID = resultSet.getInt(1);
                String firsteName =resultSet.getString(2);
                String lastName =resultSet.getString(3);
                Borger borger = new Borger(firsteName, lastName);
                borger.setID(ID);
                listOfCitizens.add(borger);

            }
            return listOfCitizens;
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Couldnt find any citizens", ButtonType.OK);
        alert.setTitle("FSIII");
        alert.show();
        return null;
    }
}
