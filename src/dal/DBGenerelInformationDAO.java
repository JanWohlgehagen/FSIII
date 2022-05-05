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

            preparedStatement.setString(1, borger.mestringProperty().get());
            preparedStatement.setString(2, borger.motivationProperty().get());
            preparedStatement.setString(3, borger.ressourcerProperty().get());
            preparedStatement.setString(4, borger.rollerProperty().get());
            preparedStatement.setString(5, borger.vanerProperty().get());
            preparedStatement.setString(6, borger.uddannelseProperty().get());
            preparedStatement.setString(7, borger.livshistorieProperty().get());
            preparedStatement.setString(8, borger.netvaerkProperty().get());
            preparedStatement.setString(9, borger.helbredsoplysningerProperty().get());
            preparedStatement.setString(10, borger.hjaelpemidlerProperty().get());
            preparedStatement.setString(11, borger.boligensIndretningProperty().get());
            preparedStatement.setInt(12, borger.IDProperty().get());

            preparedStatement.executeQuery();
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Kunne ikke lave borgeren, tjek din forbindelse.", ButtonType.OK);
        alert.setTitle("FSIII");
        alert.show();
    }

    public void createGenerelleOplysninger(Borger borger) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "INSERT INTO INSERT INTO Generelle_Oplysninger (Borger_ID) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, borger.IDProperty().get());
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Kunne ikke lave borgeren, tjek din forbindelse.", ButtonType.OK);
        alert.setTitle("FSIII");
        alert.show();
    }

    public Borger getGenerelleOplysninger(Borger borger) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM Generelle_Oplysninger WHERE Borger_ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, borger.IDProperty().get());

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
}

