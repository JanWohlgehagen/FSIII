package dal;

import be.Citizen;
import be.FunctionAssessment;
import be.FunktionstilstandsUnderkategori;
import be.Observation;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBFunctionAssessmentDAO {

    private DBConnecting dbConnecting;

    public DBFunctionAssessmentDAO(DBConnecting dbConnecting) {
        this.dbConnecting = dbConnecting;
    }

    public void updateFunktionstilstand(Borger borger) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sqlDelete = "DELETE FROM FC_Assessment WHERE FC_S_ID = (?) AND FC_A_ID = (?) AND Citizen_ID = (?);";
            String sqlInsert = "INSERT INTO FC_Assessment (FC_S_ID, FC_A_ID, Citizen_ID, [Description]) VALUES ((?),(?),(?),(?));";

            PreparedStatement preparedStatementInsert = connection.prepareStatement(sqlInsert);
            PreparedStatement preparedStatementDelete = connection.prepareStatement(sqlDelete);

            preparedStatementDelete.setInt(3, borger.getIDProperty().get());
            preparedStatementInsert.setInt(3, borger.getIDProperty().get());

            String tempString = "";

            for (String key : borger.getFunktionstilstand().getFunktionsTilstandsKort().keySet()) {
                for (FunktionstilstandsUnderkategori funktionstilstandsUnderkategori : borger.getFunktionstilstand().getFunktionsTilstandsKort().get(key)) {
                    preparedStatementDelete.setInt(1, funktionstilstandsUnderkategori.getId().get());
                    preparedStatementInsert.setInt(1, funktionstilstandsUnderkategori.getId().get());
                    if(funktionstilstandsUnderkategori.getNiveauProperty().get() != -1) {
                        for (int i = 1; i < 11; i++) {
                            switch (i) {
                                case (1) -> {
                                    if (funktionstilstandsUnderkategori.getUdførelseProperty().get() != null) {
                                        tempString = funktionstilstandsUnderkategori.getUdførelseProperty().get();
                                        preparedStatementDelete.setInt(2, i);
                                        preparedStatementDelete.execute();
                                        if (!tempString.isEmpty() || !tempString.isBlank()) {
                                            preparedStatementInsert.setInt(2, i);
                                            preparedStatementInsert.setString(4, tempString);
                                            preparedStatementInsert.execute();
                                        }
                                    }
                                }
                                case (2) -> {
                                    if (funktionstilstandsUnderkategori.getBetydningProperty().get() != null) {
                                        tempString = funktionstilstandsUnderkategori.getBetydningProperty().get();
                                        preparedStatementDelete.setInt(2, i);
                                        preparedStatementDelete.execute();
                                        if (!tempString.isEmpty() || !tempString.isBlank()) {
                                            preparedStatementInsert.setInt(2, i);
                                            preparedStatementInsert.setString(4, tempString);
                                            preparedStatementInsert.execute();
                                        }
                                    }
                                }
                                case (3) -> {
                                    if (funktionstilstandsUnderkategori.getOenskerOgMaalProperty().get() != null) {
                                        tempString = funktionstilstandsUnderkategori.getOenskerOgMaalProperty().get();
                                        preparedStatementDelete.setInt(2, i);
                                        preparedStatementDelete.execute();
                                        if (!tempString.isEmpty() || !tempString.isBlank()) {
                                            preparedStatementInsert.setInt(2, i);
                                            preparedStatementInsert.setString(4, tempString);
                                            preparedStatementInsert.execute();
                                        }
                                    }
                                }
                                case (4) -> {
                                    if (funktionstilstandsUnderkategori.getNiveauProperty().get() != -1) {
                                        tempString = String.valueOf(funktionstilstandsUnderkategori.getNiveauProperty().get());
                                        preparedStatementDelete.setInt(2, i);
                                        preparedStatementDelete.execute();
                                        preparedStatementInsert.setInt(2, i);
                                        preparedStatementInsert.setString(4, tempString);
                                        preparedStatementInsert.execute();
                                    }
                                }
                                case (5) -> {
                                    if (funktionstilstandsUnderkategori.getVurderingProperty().get() != null) {
                                        tempString = funktionstilstandsUnderkategori.getVurderingProperty().get();
                                        preparedStatementDelete.setInt(2, i);
                                        preparedStatementDelete.execute();
                                        if (!tempString.isEmpty() || !tempString.isBlank()) {
                                            preparedStatementInsert.setInt(2, i);
                                            preparedStatementInsert.setString(4, tempString);
                                            preparedStatementInsert.execute();
                                        }
                                    }
                                }
                                case (6) -> {
                                    if (funktionstilstandsUnderkategori.getAarsagProperty().get() != null) {
                                        tempString = funktionstilstandsUnderkategori.getAarsagProperty().get();
                                        preparedStatementDelete.setInt(2, i);
                                        preparedStatementDelete.execute();
                                        if (!tempString.isEmpty() || !tempString.isBlank()) {
                                            preparedStatementInsert.setInt(2, i);
                                            preparedStatementInsert.setString(4, tempString);
                                            preparedStatementInsert.execute();
                                        }
                                    }
                                }
                                case (7) -> {
                                    if (funktionstilstandsUnderkategori.getFagligNotatProperty().get() != null) {
                                        tempString = funktionstilstandsUnderkategori.getFagligNotatProperty().get();
                                        preparedStatementDelete.setInt(2, i);
                                        preparedStatementDelete.execute();
                                        if (!tempString.isEmpty() || !tempString.isBlank()) {
                                            preparedStatementInsert.setInt(2, i);
                                            preparedStatementInsert.setString(4, tempString);
                                            preparedStatementInsert.execute();
                                        }
                                    }
                                }
                                case (8) -> {
                                    if (funktionstilstandsUnderkategori.getForventetTilstandProperty().get() != -1) {
                                        tempString = String.valueOf(funktionstilstandsUnderkategori.getForventetTilstandProperty().get());
                                        preparedStatementDelete.setInt(2, i);
                                        preparedStatementDelete.execute();
                                        preparedStatementInsert.setInt(2, i);
                                        preparedStatementInsert.setString(4, tempString);
                                        preparedStatementInsert.execute();
                                    }
                                }
                                case (9) -> {
                                    if (funktionstilstandsUnderkategori.getObservation().getDescriptionProperty().get() != null) {
                                        tempString = funktionstilstandsUnderkategori.getObservation().getDescriptionProperty().get();
                                        preparedStatementDelete.setInt(2, i);
                                        preparedStatementDelete.execute();
                                        if (!tempString.isEmpty() || !tempString.isBlank()) {
                                            preparedStatementInsert.setInt(2, i);
                                            preparedStatementInsert.setString(4, tempString);
                                            preparedStatementInsert.execute();
                                        }
                                    }
                                }
                                case (10) -> {
                                    if (funktionstilstandsUnderkategori.getOpfølgningProperty().get() != null) {
                                        tempString = funktionstilstandsUnderkategori.getOpfølgningProperty().get();
                                        preparedStatementDelete.setInt(2, i);
                                        preparedStatementDelete.execute();
                                        if (!tempString.isEmpty() || !tempString.isBlank()) {
                                            preparedStatementInsert.setInt(2, i);
                                            preparedStatementInsert.setString(4, tempString);
                                            preparedStatementInsert.execute();
                                        }
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


    public FunctionAssessment getFunktionstilstandOnCitizen(Citizen citizen) {
        FunctionAssessment functionAssessment = new FunctionAssessment();
        List<FunktionstilstandsUnderkategori> allFunktionstilstande = new ArrayList<>();
        HashMap<String, List<FunktionstilstandsUnderkategori>> funktionstilstandeHP = new HashMap();

        try (Connection connection = dbConnecting.getConnection()) {
            String sqlCategories = "SELECT FC_Subcategory.FC_SC_ID, FC_Subcategory.FC_SC_Title, FC_Category.FC_C_Title FROM FC_Subcategory " +
                    "JOIN [FC_Category] ON FC_Subcategory.FC_C_ID = FC_Category.FC_C_ID;";
            String sqlAssessments = "SELECT * FROM FC_Assessment WHERE Citizen_ID = (?) AND FC_S_ID = (?);";

            PreparedStatement preparedStatementCategories = connection.prepareStatement(sqlCategories);
            PreparedStatement preparedStatementAssessments = connection.prepareStatement(sqlAssessments);
            preparedStatementAssessments.setInt(1, borger.getIDProperty().get());

            ResultSet resultSetCategory = preparedStatementCategories.executeQuery();
            while (resultSetCategory.next())
            {
                int UKID = resultSetCategory.getInt("FC_SC_ID");
                String tilstandsKlassifikation = resultSetCategory.getString("FC_SC_Title");
                String overkategoriNavn = resultSetCategory.getString("FC_C_Title");

                FunktionstilstandsUnderkategori funktionstilstandsUnderkategori = new FunktionstilstandsUnderkategori(UKID, tilstandsKlassifikation, overkategoriNavn);

                funktionstilstandsUnderkategori.setNiveau(-1);
                funktionstilstandsUnderkategori.setForventetTilstand(-1);
                preparedStatementAssessments.setInt(2, UKID);
                ResultSet resultSet = preparedStatementAssessments.executeQuery();

                while (resultSet.next()){
                    switch (resultSet.getInt("FC_A_ID")){
                        case (1) ->{
                            funktionstilstandsUnderkategori.setUdførelse(resultSet.getString("Description"));
                        }
                        case (2) ->{
                            funktionstilstandsUnderkategori.setBetydning(resultSet.getString("Description"));
                        }
                        case (3) ->{
                            funktionstilstandsUnderkategori.setOenskerOgMaal(resultSet.getString("Description"));
                        }
                        case (4) ->{
                            funktionstilstandsUnderkategori.setNiveau(Integer.parseInt(resultSet.getString("Description")));
                        }
                        case (5) ->{
                            funktionstilstandsUnderkategori.setVurdering(resultSet.getString("Description"));
                        }
                        case (6) ->{
                            funktionstilstandsUnderkategori.setAarsag(resultSet.getString("Description"));
                        }
                        case (7) ->{
                            funktionstilstandsUnderkategori.setFagligNotat(resultSet.getString("Description"));
                        }
                        case (8) ->{
                            funktionstilstandsUnderkategori.setForventetTilstand(Integer.parseInt(resultSet.getString("Description")));
                        }
                        case (9) ->{
                            Observation observation = new Observation();
                            observation.setDescription(resultSet.getString("Description"));
                            funktionstilstandsUnderkategori.setObservation(observation);
                            funktionstilstandsUnderkategori.getObservation().setTitel(tilstandsKlassifikation);
                        }
                        case (10) ->{
                            funktionstilstandsUnderkategori.setOpfølgning(resultSet.getString("Description"));
                        }
                    }
                }

                allFunktionstilstande.add(funktionstilstandsUnderkategori);
            }

            for (FunktionstilstandsUnderkategori f : allFunktionstilstande) {
                if (!funktionstilstandeHP.containsKey(f.getOverKategoriProperty().get())) {
                    funktionstilstandeHP.put(f.getOverKategoriProperty().get(), new ArrayList<>());
                }
                funktionstilstandeHP.get(f.getOverKategoriProperty().get()).add(f);
            }

            functionAssessment.setFunktionsTilstande(funktionstilstandeHP);
            return functionAssessment;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return functionAssessment;
    }
    public FunctionAssessment getEmptyFunktionstilstands() {
        FunctionAssessment functionAssessment = new FunctionAssessment();
        List<FunktionstilstandsUnderkategori> allFunktionstilstande = new ArrayList<>();
        HashMap<String, List<FunktionstilstandsUnderkategori>> funktionstilstandeHP = new HashMap();

        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [FC_Category]";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String overkategori = rs.getString("FC_C_Title");
                funktionstilstandList.add(overkategori);
            }

            for (FunktionstilstandsUnderkategori f : allFunktionstilstande) {
                if (!funktionstilstandeHP.containsKey(f.getOverKategoriProperty().get())) {
                    funktionstilstandeHP.put(f.getOverKategoriProperty().get(), new ArrayList<FunktionstilstandsUnderkategori>());
                }
                funktionstilstandeHP.get(f.getOverKategoriProperty().get()).add(f);
            }
            functionAssessment.setFunktionsTilstande(funktionstilstandeHP);
            return functionAssessment;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}

