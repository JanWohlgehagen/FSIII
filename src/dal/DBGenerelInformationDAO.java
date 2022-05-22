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
                        if (borger.getGeneralinformation().getMestringProperty().get() != null) {
                            String tempString = borger.getGeneralinformation().getMestringProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (2) -> {
                        if (borger.getGeneralinformation().getMotivationProperty().get() != null) {
                            String tempString = borger.getGeneralinformation().getMotivationProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (3) -> {
                        if (borger.getGeneralinformation().getRessourcerProperty().get() != null) {
                            String tempString = borger.getGeneralinformation().getRessourcerProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (4) -> {
                        if (borger.getGeneralinformation().getRollerProperty().get() != null) {
                            String tempString = borger.getGeneralinformation().getRollerProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (5) -> {
                        if (borger.getGeneralinformation().getVanerProperty().get() != null) {
                            String tempString = borger.getGeneralinformation().getVanerProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (6) -> {
                        if (borger.getGeneralinformation().getUddannelseProperty().get() != null) {
                            String tempString = borger.getGeneralinformation().getUddannelseProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (7) -> {
                        if (borger.getGeneralinformation().getLivshistorieProperty().get() != null) {
                            String tempString = borger.getGeneralinformation().getLivshistorieProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (8) -> {
                        if (borger.getGeneralinformation().getNetvaerkProperty().get() != null) {
                            String tempString = borger.getGeneralinformation().getNetvaerkProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (9) -> {
                        if (borger.getGeneralinformation().getHjaelpemidlerProperty().get() != null) {
                            String tempString = borger.getGeneralinformation().getHjaelpemidlerProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (10) -> {
                        if (borger.getGeneralinformation().getHelbredsoplysningerProperty().get() != null) {
                            String tempString = borger.getGeneralinformation().getHelbredsoplysningerProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (11) -> {
                        if (borger.getGeneralinformation().getBoligensIndretningProperty().get() != null) {
                            String tempString = borger.getGeneralinformation().getBoligensIndretningProperty().get();
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
                    case (1) -> borger.getGeneralinformation().setMestring(resultSet.getString("Description"));
                    case (2) -> borger.getGeneralinformation().setMotivation(resultSet.getString("Description"));
                    case (3) -> borger.getGeneralinformation().setRessourcer(resultSet.getString("Description"));
                    case (4) -> borger.getGeneralinformation().setRoller(resultSet.getString("Description"));
                    case (5) -> borger.getGeneralinformation().setVaner(resultSet.getString("Description"));
                    case (6) -> borger.getGeneralinformation().setUddannelse(resultSet.getString("Description"));
                    case (7) -> borger.getGeneralinformation().setLivshistorie(resultSet.getString("Description"));
                    case (8) -> borger.getGeneralinformation().setNetvaerk(resultSet.getString("Description"));
                    case (9) -> borger.getGeneralinformation().setHjaelpemidler(resultSet.getString("Description"));
                    case (10) -> borger.getGeneralinformation().setHelbredsoplysninger(resultSet.getString("Description"));
                    case (11) -> borger.getGeneralinformation().setBoligensIndretning(resultSet.getString("Description"));
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

