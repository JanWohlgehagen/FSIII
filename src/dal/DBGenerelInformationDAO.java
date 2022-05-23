package dal;

import be.Citizen;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.sql.*;

public class DBGenerelInformationDAO {
    private DBConnecting dbConnecting;

    public DBGenerelInformationDAO(DBConnecting dbConnecting) throws IOException {
        this.dbConnecting = dbConnecting;
    }

    public void updateGenerelleOplysninger(Citizen citizen) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sqlDelete = "DELETE FROM GI_Assessment WHERE [FK_GI_ID] = (?) AND [Citizen_ID] = (?);";
            String sqlInsert = "INSERT INTO GI_Assessment (Citizen_ID, FK_GI_ID, Description)VALUES ((?), (?), (?));";
            PreparedStatement preparedStatementDelete = connection.prepareStatement(sqlDelete);
            PreparedStatement preparedStatementInsert = connection.prepareStatement(sqlInsert);

            //Set the ID for the citizen in both preparedstatements, this is not dynamic and is therefore exempt from the loop
            preparedStatementDelete.setInt(2, citizen.getIDProperty().get());
            preparedStatementInsert.setInt(1, citizen.getIDProperty().get());

            for (int i = 1; i < 12; i++) {

                preparedStatementDelete.setInt(1, i);
                preparedStatementInsert.setInt(2, i);

                //If the property is not null, the row in DB is deleted, and if string is not blank or empty a new row is created as wel
                switch (i) {
                    case (1) -> {
                        if (citizen.getGeneralinformation().getMasteryProperty().get() != null) {
                            String tempString = citizen.getGeneralinformation().getMasteryProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (2) -> {
                        if (citizen.getGeneralinformation().getMotivationProperty().get() != null) {
                            String tempString = citizen.getGeneralinformation().getMotivationProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (3) -> {
                        if (citizen.getGeneralinformation().getResourcesProperty().get() != null) {
                            String tempString = citizen.getGeneralinformation().getResourcesProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (4) -> {
                        if (citizen.getGeneralinformation().getRolesProperty().get() != null) {
                            String tempString = citizen.getGeneralinformation().getRolesProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (5) -> {
                        if (citizen.getGeneralinformation().getHabitsProperty().get() != null) {
                            String tempString = citizen.getGeneralinformation().getHabitsProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (6) -> {
                        if (citizen.getGeneralinformation().getEducationProperty().get() != null) {
                            String tempString = citizen.getGeneralinformation().getEducationProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (7) -> {
                        if (citizen.getGeneralinformation().getLifeStoryProperty().get() != null) {
                            String tempString = citizen.getGeneralinformation().getLifeStoryProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (8) -> {
                        if (citizen.getGeneralinformation().getNetworkProperty().get() != null) {
                            String tempString = citizen.getGeneralinformation().getNetworkProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (9) -> {
                        if (citizen.getGeneralinformation().getAssistiveDevicesProperty().get() != null) {
                            String tempString = citizen.getGeneralinformation().getAssistiveDevicesProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (10) -> {
                        if (citizen.getGeneralinformation().getHealthInformationProperty().get() != null) {
                            String tempString = citizen.getGeneralinformation().getHealthInformationProperty().get();
                            preparedStatementInsert.setString(3, tempString);
                            preparedStatementDelete.execute();
                            if (!tempString.isBlank() || !tempString.isEmpty())
                                preparedStatementInsert.execute();
                        }
                    }
                    case (11) -> {
                        if (citizen.getGeneralinformation().getHomeDecorProperty().get() != null) {
                            String tempString = citizen.getGeneralinformation().getHomeDecorProperty().get();
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


    public Citizen getGenerelleOplysninger(Citizen citizen) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT [Description], [FK_GI_ID] FROM GI_Assessment WHERE [Citizen_ID] = (?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, citizen.getIDProperty().get());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                switch (resultSet.getInt("FK_GI_ID")) {
                    case (1) -> citizen.getGeneralinformation().setMastery(resultSet.getString("Description"));
                    case (2) -> citizen.getGeneralinformation().setMotivation(resultSet.getString("Description"));
                    case (3) -> citizen.getGeneralinformation().setResources(resultSet.getString("Description"));
                    case (4) -> citizen.getGeneralinformation().setRoles(resultSet.getString("Description"));
                    case (5) -> citizen.getGeneralinformation().setHabits(resultSet.getString("Description"));
                    case (6) -> citizen.getGeneralinformation().setEducation(resultSet.getString("Description"));
                    case (7) -> citizen.getGeneralinformation().setLifeStory(resultSet.getString("Description"));
                    case (8) -> citizen.getGeneralinformation().setNetwork(resultSet.getString("Description"));
                    case (9) -> citizen.getGeneralinformation().setAssistiveDevices(resultSet.getString("Description"));
                    case (10) -> citizen.getGeneralinformation().setHealthInformation(resultSet.getString("Description"));
                    case (11) -> citizen.getGeneralinformation().setHomeDecor(resultSet.getString("Description"));
                }
            }
            return citizen;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Kunne ikke lave borgeren, tjek din forbindelse.", ButtonType.OK);
            alert.setTitle("FSIII");
            alert.show();
            return null;
        }
    }
}

