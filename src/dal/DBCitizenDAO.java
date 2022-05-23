package dal;

import be.Citizen;
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

    public void deleteCitizen(Citizen citizen) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "DELETE FROM [Borger] WHERE Borger_ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, citizen.getIDProperty().get());
            preparedStatement.execute();

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateCitizen(Citizen citizen) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "UPDATE [Borger] SET FirstName = (?), LastName = (?) WHERE Borger_ID =(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, citizen.getFirstNameProperty().get());
            preparedStatement.setString(2, citizen.getLastNameProperty().get());
            preparedStatement.setInt(3, citizen.getIDProperty().get());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Kunne ikke opdatere borgeren", ButtonType.OK);
            alert.setTitle("FSIII");
            alert.show();
            throwables.printStackTrace();
        }
    }

    public void addStudentToCitizen(Citizen citizen) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "UPDATE [Borger] SET Student_ID = (?) WHERE Borger_ID =(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, citizen.getStudent().getIdProperty().get());
            preparedStatement.setInt(2, citizen.getIDProperty().get());
            preparedStatement.execute();

        } catch (SQLException throwables) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Kunne ikke opdatere borgeren", ButtonType.OK);
            alert.setTitle("FSIII");
            alert.show();
            throwables.printStackTrace();
        }

    }

    public Citizen createCitizen(Citizen citizen) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "INSERT INTO [Borger] (FirstName, LastName, Age, Template) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, citizen.getFirstNameProperty().get());
            preparedStatement.setString(2, citizen.getLastNameProperty().get());
            preparedStatement.setInt(3, citizen.getAgeProperty().get());
            preparedStatement.setBoolean(4, citizen.isTemplateProperty().get());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                citizen.setID(resultSet.getInt(1));
                return citizen;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Kunne ikke lave borgeren, tjek din forbindelse.", ButtonType.OK);
        alert.setTitle("FSIII");
        alert.show();
        return null;
    }

    public List<Citizen> getAllCitizens() {
        List<Citizen> listOfCitizens = new ArrayList<>();
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [Borger] WHERE Template = 0";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int ID = resultSet.getInt("Borger_ID");
                String firsteName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                int age = resultSet.getInt("Age");
                boolean isTemplate = resultSet.getBoolean("Template");
                int studentID = resultSet.getInt("Student_ID");
                Citizen citizen = new Citizen(firsteName, lastName, isTemplate, age);
                citizen.setID(ID);
                citizen.setStudentID(studentID);
                listOfCitizens.add(citizen);

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

    public List<Citizen> getAllTemplates() {
        List<Citizen> listOfTemplates = new ArrayList<>();
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [Borger] WHERE Template = 1";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int ID = resultSet.getInt("Borger_ID");
                String firsteName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                int age = resultSet.getInt("Age");
                boolean isTemplate = resultSet.getBoolean("Template");
                Citizen citizen = new Citizen(firsteName, lastName, isTemplate, age);
                citizen.setID(ID);
                listOfTemplates.add(citizen);
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
