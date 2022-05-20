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
            String sqlDelete = "DELETE FROM GI_Assessment WHERE [FK_GI_ID] = (?) AND [Citizen_ID] = (?);";
            String sqlInsert = "INSERT INTO GI_Assessment (Citizen_ID, FK_GI_ID, Description)VALUES ((?), (?), (?));";
            PreparedStatement preparedStatementDelete = connection.prepareStatement(sqlDelete);
            PreparedStatement preparedStatementInsert = connection.prepareStatement(sqlInsert);

            //Set the ID for the citizen in both preparedstatements, this is not dynamic and is therefore exempt from the loop
            preparedStatementDelete.setInt(2, borger.getIDProperty().get());
            preparedStatementInsert.setInt(1, borger.getIDProperty().get());

            for (int i = 1; i < 12; i++) {

                preparedStatementDelete.setInt(1, i);
                preparedStatementInsert.setInt(2, i);

                //If the property is not null, the row in DB is deleted, and if string is not blank or empty a new row is created as wel
                switch (i) {
                    case (1) -> {
                        if (borger.getMestringProperty().get() != null) {
                            String tempString = borger.getMestringProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (2) -> {
                        if (borger.getMotivationProperty().get() != null) {
                            String tempString = borger.getMotivationProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (3) -> {
                        if (borger.getRessourcerProperty().get() != null) {
                            String tempString = borger.getRessourcerProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (4) -> {
                        if (borger.getRollerProperty().get() != null) {
                            String tempString = borger.getRollerProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (5) -> {
                        if (borger.getVanerProperty().get() != null) {
                            String tempString = borger.getVanerProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (6) -> {
                        if (borger.getUddannelseProperty().get() != null) {
                            String tempString = borger.getUddannelseProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (7) -> {
                        if (borger.getLivshistorieProperty().get() != null) {
                            String tempString = borger.getLivshistorieProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (8) -> {
                        if (borger.getNetvaerkProperty().get() != null) {
                            String tempString = borger.getNetvaerkProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (9) -> {
                        if (borger.getHjaelpemidlerProperty().get() != null) {
                            String tempString = borger.getHjaelpemidlerProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (10) -> {
                        if (borger.getHelbredsoplysningerProperty().get() != null) {
                            String tempString = borger.getHelbredsoplysningerProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (11) -> {
                        if (borger.getBoligensIndretningProperty().get() != null) {
                            String tempString = borger.getBoligensIndretningProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Kunne ikke opdatere generelle oplysninger.", ButtonType.OK);
            alert.setTitle("FSIII");
            alert.show();
        }
    }


    public Borger getGenerelleOplysninger(Borger borger) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT [Description], [FK_GI_ID] FROM GI_Assessment WHERE [Citizen_ID] = (?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, borger.getIDProperty().get());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                switch (resultSet.getInt("FK_GI_ID")) {
                    case (1) -> borger.setMestring(resultSet.getString("Description"));
                    case (2) -> borger.setMotivation(resultSet.getString("Description"));
                    case (3) -> borger.setRessourcer(resultSet.getString("Description"));
                    case (4) -> borger.setRoller(resultSet.getString("Description"));
                    case (5) -> borger.setVaner(resultSet.getString("Description"));
                    case (6) -> borger.setUddannelse(resultSet.getString("Description"));
                    case (7) -> borger.setLivshistorie(resultSet.getString("Description"));
                    case (8) -> borger.setNetvaerk(resultSet.getString("Description"));
                    case (9) -> borger.setHjaelpemidler(resultSet.getString("Description"));
                    case (10) -> borger.setHelbredsoplysninger(resultSet.getString("Description"));
                    case (11) -> borger.setBoligensIndretning(resultSet.getString("Description"));
                }
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

