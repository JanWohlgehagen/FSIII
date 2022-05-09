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
            String sql = "DELETE FROM [Borger] WHERE ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, borger.getIDProperty().get());
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
            String sql ="UPDATE [Borger] SET FirstName = (?), LastName = (?), Helhedsvurdering = (?) WHERE ID =(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, borger.getFirstNameProperty().get());
            preparedStatement.setString(2, borger.getLastNameProperty().get());
            preparedStatement.setString(3, borger.getHelhedsvurderingProperty().get());
            preparedStatement.setInt(4, borger.getIDProperty().get());

            preparedStatement.execute();
            return;
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
            String sql = "INSERT INTO [Borger] (FirstName, LastName, Age, Template) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, borger.getFirstNameProperty().get());
            preparedStatement.setString(2, borger.getLastNameProperty().get());
            preparedStatement.setInt(3, borger.getAgeProperty().get());
            preparedStatement.setBoolean(4, borger.isTemplateProperty().get());

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                borger.setID(resultSet.getInt(1));
                return borger;
            }
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
            String sql = "SELECT * FROM [Borger] WHERE Template = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int ID = resultSet.getInt(1);
                String firsteName =resultSet.getString(2);
                String lastName =resultSet.getString(3);
                int age = resultSet.getInt(4);
                boolean isTemplate = resultSet.getBoolean(5);
                String helhedsvurdering = resultSet.getString(6);
                Borger borger = new Borger(firsteName, lastName, isTemplate, age);
                borger.setID(ID);
                borger.setHelhedsvurdering(helhedsvurdering);
                listOfCitizens.add(borger);

            }
            return listOfCitizens;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Couldnt find any citizens", ButtonType.OK);
        alert.setTitle("FSIII");
        alert.show();
        return null;
    }

    public List<Borger> getAllTemplates()
    {
        List<Borger> listOfTemplates = new ArrayList<>();
        try(Connection connection =dbConnecting.getConnection())
        {
            String sql = "SELECT * FROM [Borger] WHERE Template = 1";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int ID = resultSet.getInt(1);
                String firsteName =resultSet.getString(2);
                String lastName =resultSet.getString(3);
                int age = resultSet.getInt(4);
                boolean isTemplate = resultSet.getBoolean(5);
                Borger borger = new Borger(firsteName, lastName, isTemplate, age);
                borger.setID(ID);
                listOfTemplates.add(borger);

            }
            return listOfTemplates;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Couldnt find any citizens", ButtonType.OK);
        alert.setTitle("FSIII");
        alert.show();
        return null;
    }
}
