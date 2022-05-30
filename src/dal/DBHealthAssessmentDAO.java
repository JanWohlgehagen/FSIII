package dal;

import be.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBHealthAssessmentDAO {
    private DBConnecting dbConnecting;

    public DBHealthAssessmentDAO(DBConnecting dbConnecting) {
        this.dbConnecting = dbConnecting;
    }

    public void updateHealthAssessment(Citizen citizen) {
        try (Connection connection = dbConnecting.getConnection()) {
            //Delete statement, this is always executed in case the input is empty
            String sqlDelete = "DELETE FROM HC_Assessment WHERE HC_SC_ID = (?) AND HC_A_ID = (?) AND Citizen_ID = (?);";
            //Insert statement, this is executed if the input is not empty
            String sqlInsert = "INSERT INTO HC_Assessment (HC_SC_ID, HC_A_ID, Citizen_ID, [Description]) VALUES ((?),(?),(?),(?));";

            PreparedStatement preparedStatementInsert = connection.prepareStatement(sqlInsert);
            PreparedStatement preparedStatementDelete = connection.prepareStatement(sqlDelete);

            preparedStatementDelete.setInt(3, citizen.getIDProperty().get());
            preparedStatementInsert.setInt(3, citizen.getIDProperty().get());

            String tempString = "";
            //Outer loop going through the keyset of a citizens condition
            for (String key : citizen.getHelbredstilstand().getHelbredsTilstandsKort().keySet()) {
                //first nested loop going through the subcategories of the assessments
                for (HelbredstilstandsUnderkategori huk : citizen.getHelbredstilstand().getHelbredsTilstandsKort().get(key)) {
                    preparedStatementDelete.setInt(1, huk.getId().get());
                    preparedStatementInsert.setInt(1, huk.getId().get());
                    //i goes to 7 because there are 7 different fields to fill out in a functional ability assessment
                    for (int i = 1; i<7; i++)
                    {
                        switch (i){
                            case(1)->{
                                if (huk.getTilstandProperty().get()!= null)
                                {
                                    tempString = huk.getTilstandProperty().get();
                                    preparedStatementDelete.setInt(2,i);
                                    preparedStatementDelete.execute();
                                    if(!tempString.isEmpty() || !tempString.isBlank())
                                    {
                                        preparedStatementInsert.setInt(2,i);
                                        preparedStatementInsert.setInt(3,citizen.getIDProperty().get());
                                        preparedStatementInsert.setString(4,tempString);
                                        preparedStatementInsert.execute();
                                    }
                                }


                            }case(2)->{
                                if (huk.getForventetTilstandProperty().get()!= null)
                                {
                                    tempString = huk.getForventetTilstandProperty().get();
                                    preparedStatementDelete.setInt(2,i);
                                    preparedStatementDelete.execute();
                                    if(!tempString.isEmpty() || !tempString.isBlank())
                                    {
                                        preparedStatementInsert.setInt(2,i);
                                        preparedStatementInsert.setInt(3,citizen.getIDProperty().get());
                                        preparedStatementInsert.setString(4,tempString);
                                        preparedStatementInsert.execute();
                                    }
                                }

                            }case(3)->{
                                if (huk.getVurderingProperty().get()!= null)
                                {
                                    tempString = huk.getVurderingProperty().get();
                                    preparedStatementDelete.setInt(2,i);
                                    preparedStatementDelete.execute();
                                    if(!tempString.isEmpty() || !tempString.isBlank())
                                    {
                                        preparedStatementInsert.setInt(2,i);
                                        preparedStatementInsert.setInt(3,citizen.getIDProperty().get());
                                        preparedStatementInsert.setString(4,tempString);
                                        preparedStatementInsert.execute();
                                    }
                                }

                            }case(4)->{
                                if (huk.getAarsagProperty().get()!= null)
                                {
                                    tempString = huk.getAarsagProperty().get();
                                    preparedStatementDelete.setInt(2,i);
                                    preparedStatementDelete.execute();
                                    if(!tempString.isEmpty() || !tempString.isBlank())
                                    {
                                        preparedStatementInsert.setInt(2,i);
                                        preparedStatementInsert.setInt(3,citizen.getIDProperty().get());
                                        preparedStatementInsert.setString(4,tempString);
                                        preparedStatementInsert.execute();
                                    }
                                }

                            }case(5)->{
                                if (huk.getFagligNotatProperty().get()!= null)
                                {
                                    tempString = huk.getFagligNotatProperty().get();
                                    preparedStatementDelete.setInt(2,i);
                                    preparedStatementDelete.execute();
                                    if(!tempString.isEmpty() || !tempString.isBlank())
                                    {
                                        preparedStatementInsert.setInt(2,i);
                                        preparedStatementInsert.setInt(3,citizen.getIDProperty().get());
                                        preparedStatementInsert.setString(4,tempString);
                                        preparedStatementInsert.execute();
                                    }
                                }

                            }case(6)->{
                                if (huk.getObservation().getDescriptionProperty().get()!= null)
                                {
                                    tempString = huk.getObservation().getDescriptionProperty().get();
                                    preparedStatementDelete.setInt(2,i);
                                    preparedStatementDelete.execute();
                                    if(!tempString.isEmpty() || !tempString.isBlank())
                                    {
                                        preparedStatementInsert.setInt(2,i);
                                        preparedStatementInsert.setInt(3,citizen.getIDProperty().get());
                                        preparedStatementInsert.setString(4,tempString);
                                        preparedStatementInsert.execute();
                                    }
                                }

                            }
                        }
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public HealthAssessment getHelbredstilstandOnCitizen(Citizen citizen) {
        HealthAssessment healthAssessment = new HealthAssessment();

        HashMap<String, List<HelbredstilstandsUnderkategori>> helbredstilstandeHP = new HashMap<>();
        List<HelbredstilstandsUnderkategori> allHelbredstilstandeUK = new ArrayList<>();

        try (Connection connection = dbConnecting.getConnection()) {
            //Statement that fetches all subcategories of a function ability condition
            String sqlCategories = "SELECT HC_Subcategory.HC_SC_ID, HC_Subcategory.HC_SC_Title, HC_Category.HC_C_Title FROM HC_Subcategory " +
                    "JOIN [HC_Category] ON HC_Subcategory.HC_C_ID = HC_Category.HC_C_ID;";

            String sqlAssessments = "SELECT * FROM HC_Assessment WHERE Citizen_ID = (?) AND HC_SC_ID = (?);";

            PreparedStatement preparedStatementCategories = connection.prepareStatement(sqlCategories);
            PreparedStatement preparedStatementAssessments = connection.prepareStatement(sqlAssessments);
            preparedStatementAssessments.setInt(1, citizen.getIDProperty().get());

            ResultSet resultSetCategories = preparedStatementCategories.executeQuery();
            //outer loop going through all the subcategories and assigns an ID as well as a super category to it.
            while (resultSetCategories.next()) {

                int ID = resultSetCategories.getInt("HC_SC_ID");
                String underKategoriTitel = resultSetCategories.getString("HC_SC_Title");

                String overKategoriTitel = resultSetCategories.getString("HC_C_Title");

                HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(ID, underKategoriTitel, overKategoriTitel);
                preparedStatementAssessments.setInt(2, ID);
                //fetches all assessments on a given subcategory
                ResultSet resultSet = preparedStatementAssessments.executeQuery();
                //Assigns fields in a subcategory based on the assessment's ID in the database.
                while (resultSet.next()) {
                    switch (resultSet.getInt("HC_A_ID")) {
                        case(1) ->{
                            helbredstilstandsUnderkategori.setTilstand(resultSet.getString("Description"));
                        }
                        case(2) ->{
                            helbredstilstandsUnderkategori.setForventetTilstand(resultSet.getString("Description"));
                                                }
                        case(3) ->{
                            helbredstilstandsUnderkategori.setVurdering(resultSet.getString("Description"));
                                                }
                        case(4) ->{
                            helbredstilstandsUnderkategori.setAarsag(resultSet.getString("Description"));
                                                }
                        case(5) ->{
                            helbredstilstandsUnderkategori.setFagligNotat(resultSet.getString("Description"));
                                                }
                        case(6) ->{
                            Observation observation = new Observation();
                            observation.setDescription(resultSet.getString("Description"));
                            observation.setTitle(underKategoriTitel);
                            helbredstilstandsUnderkategori.setObservation(observation);
                                                }
                    }
                }
                allHelbredstilstandeUK.add(helbredstilstandsUnderkategori);

            }
            for (HelbredstilstandsUnderkategori h : allHelbredstilstandeUK) {
                if (!helbredstilstandeHP.containsKey(h.getOverkategoriProperty().get())) {
                    helbredstilstandeHP.put(h.getOverkategoriProperty().get(), new ArrayList<HelbredstilstandsUnderkategori>());
                }
                helbredstilstandeHP.get(h.getOverkategoriProperty().get()).add(h);
            }
            healthAssessment.setHelbredstilstandskort(helbredstilstandeHP);

            return healthAssessment;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return healthAssessment;


    }
}

