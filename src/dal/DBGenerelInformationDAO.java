package dal;

import be.Borger;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.sql.*;

public class DBGenerelInformationDAO {
    private DBConnecting dbConnecting;

    public DBGenerelInformationDAO(DBConnecting dbConnecting) throws IOException {
        this.dbConnecting = dbConnecting;
    }

    public void updateGenerelleOplysninger(Borger borger) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "UPDATE Generelle_Oplysninger SET Mestring = (?), Motivation = (?), Ressourcer = (?), Roller = (?), Vaner = (?), Uddannelse = (?), Livshistorie = (?), Netvaerk = (?), Helbredsoplysninger = (?), hjaelpemidler = (?), Boligens_Indretning = (?) WHERE Borger_ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, borger.getMestringProperty().get());
            preparedStatement.setString(2, borger.getMotivationProperty().get());
            preparedStatement.setString(3, borger.getRessourcerProperty().get());
            preparedStatement.setString(4, borger.getRollerProperty().get());
            preparedStatement.setString(5, borger.getVanerProperty().get());
            preparedStatement.setString(6, borger.getUddannelseProperty().get());
            preparedStatement.setString(7, borger.getLivshistorieProperty().get());
            preparedStatement.setString(8, borger.getNetvaerkProperty().get());
            preparedStatement.setString(9, borger.getHelbredsoplysningerProperty().get());
            preparedStatement.setString(10, borger.getHjaelpemidlerProperty().get());
            preparedStatement.setString(11, borger.getBoligensIndretningProperty().get());
            preparedStatement.setInt(12, borger.getIDProperty().get());

            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Kunne ikke opdatere generelle oplysninger.", ButtonType.OK);
            alert.setTitle("FSIII");
            alert.show();
        }
    }

    public void createGenerelleOplysninger(Borger borger) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "INSERT INTO Generelle_Oplysninger (Borger_ID) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, borger.getIDProperty().get());
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Kunne ikke oprette generelle oplysninger.", ButtonType.OK);
            alert.setTitle("FSIII");
            alert.show();
        }
    }

    public Borger getGenerelleOplysninger(Borger borger) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM Generelle_Oplysninger WHERE Borger_ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, borger.getIDProperty().get());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
               borger.setMestring(resultSet.getString(2));
               borger.setMotivation(resultSet.getString(3));
               borger.setRessourcer(resultSet.getString(4));
               borger.setRoller(resultSet.getString(5));
               borger.setVaner(resultSet.getString(6));
               borger.setUddannelse(resultSet.getString(7));
               borger.setLivshistorie(resultSet.getString(8));
               borger.setNetvaerk(resultSet.getString(9));
               borger.setHelbredsoplysninger(resultSet.getString(10));
               borger.setHjaelpemidler(resultSet.getString(11));
               borger.setBoligensIndretning(resultSet.getString(12));
            }
            return borger;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Kunne ikke lave borgeren, tjek din forbindelse.", ButtonType.OK);
            alert.setTitle("FSIII");
            alert.show();
            return null;
        }
    }
}

