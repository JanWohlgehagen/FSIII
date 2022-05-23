package dal;

import be.*;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBHelbredstilstandDAO {
    private DBConnecting dbConnecting;

    public DBHelbredstilstandDAO(DBConnecting dbConnecting) {
        this.dbConnecting = dbConnecting;
    }

    public void updateHelbredstilstand(Borger borger) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sqlDelete = "DELETE FROM HC_Assessment WHERE HC_SC_ID = (?) AND HC_A_ID = (?) AND Citizen_ID = (?);";
            String sqlInsert = "INSERT INTO HC_Assessment (HC_SC_ID, HC_A_ID, Citizen_ID, [Description]) VALUES ((?),(?),(?),(?));";

            PreparedStatement preparedStatementInsert = connection.prepareStatement(sqlInsert);
            PreparedStatement preparedStatementDelete = connection.prepareStatement(sqlDelete);

            preparedStatementDelete.setInt(3, borger.getIDProperty().get());
            preparedStatementInsert.setInt(3, borger.getIDProperty().get());

            String tempString = "";

            for (String key : borger.getHelbredstilstand().getHelbredsTilstandsKort().keySet()) {
                for (HelbredstilstandsUnderkategori huk : borger.getHelbredstilstand().getHelbredsTilstandsKort().get(key)) {
                    preparedStatementDelete.setInt(1, huk.getId().get());
                    preparedStatementInsert.setInt(1, huk.getId().get());
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
                                        preparedStatementInsert.setInt(3,borger.getIDProperty().get());
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
                                        preparedStatementInsert.setInt(3,borger.getIDProperty().get());
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
                                        preparedStatementInsert.setInt(3,borger.getIDProperty().get());
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
                                        preparedStatementInsert.setInt(3,borger.getIDProperty().get());
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
                                        preparedStatementInsert.setInt(3,borger.getIDProperty().get());
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
                                        preparedStatementInsert.setInt(3,borger.getIDProperty().get());
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


    public Helbredstilstand getHelbredstilstandOnCitizen(Borger borger) {
        Helbredstilstand helbredstilstand = new Helbredstilstand();

        HashMap<String, List<HelbredstilstandsUnderkategori>> helbredstilstandeHP = new HashMap<>();
        List<HelbredstilstandsUnderkategori> allHelbredstilstandeUK = new ArrayList<>();

        try (Connection connection = dbConnecting.getConnection()) {
            String sqlCategories = "SELECT HC_Subcategory.HC_SC_ID, HC_Subcategory.HC_SC_Title, HC_Category.HC_C_Title FROM HC_Subcategory " +
                    "JOIN [HC_Category] ON HC_Subcategory.HC_C_ID = HC_Category.HC_C_ID;";
            String sqlAssessments = "SELECT * FROM HC_Assessment WHERE Citizen_ID = (?) AND HC_SC_ID = (?);";

            PreparedStatement preparedStatementCategories = connection.prepareStatement(sqlCategories);
            PreparedStatement preparedStatementAssessments = connection.prepareStatement(sqlAssessments);
            preparedStatementAssessments.setInt(1, borger.getIDProperty().get());

            ResultSet resultSetCategories = preparedStatementCategories.executeQuery();
            while (resultSetCategories.next()) {

                int ID = resultSetCategories.getInt("HC_SC_ID");

                String underKategoriTitel = resultSetCategories.getString("HC_SC_Title");

                String overKategoriTitel = resultSetCategories.getString("HC_C_Title");

                HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(ID, underKategoriTitel, overKategoriTitel);
                preparedStatementAssessments.setInt(2, ID);
                ResultSet resultSet = preparedStatementAssessments.executeQuery();
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
                            observation.setTitel(underKategoriTitel);
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
            helbredstilstand.setHelbredstilstandskort(helbredstilstandeHP);
            return helbredstilstand;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return helbredstilstand;


    }

    public Helbredstilstand getEmptyHelbredstilstand() {
        Helbredstilstand helbredstilstand = new Helbredstilstand();
        HashMap<String, List<HelbredstilstandsUnderkategori>> helbredstilstandeHP = new HashMap<>();
        List<HelbredstilstandsUnderkategori> allHelbredstilstandeUK = new ArrayList<>();

        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT HC_Subcategory.HC_SC_ID, HC_Subcategory.HC_SC_Title, HC_Category.HC_C_Title, HC_Category.HC_C_ID FROM [HC_Subcategory]" +
                    "FULL JOIN [HC_Category] ON HC_Subcategory.HC_C_ID = HC_Category.HC_C_ID";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int UKID = resultSet.getInt("HC_SC_ID");

                //Underkategori
                String underKategoriTitel = resultSet.getString("HC_SC_Title");

                //Overkategori
                String overKategoriTitel = resultSet.getString("HC_C_Title");

                HelbredstilstandsUnderkategori h = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel);
                allHelbredstilstandeUK.add(h);

            }


            for (HelbredstilstandsUnderkategori h : allHelbredstilstandeUK) {
                if (!helbredstilstandeHP.containsKey(h.getOverkategoriProperty().get())) {
                    helbredstilstandeHP.put(h.getOverkategoriProperty().get(), new ArrayList<HelbredstilstandsUnderkategori>());
                }
                helbredstilstandeHP.get(h.getOverkategoriProperty().get()).add(h);
            }
            helbredstilstand.setHelbredstilstandskort(helbredstilstandeHP);
            return helbredstilstand;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public List<String> getHelbredstilstandList() {
        List<String> helbredstilstandList = new ArrayList<>();
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [HC_Category]";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String overkategori = rs.getString("HC_C_Title");
                helbredstilstandList.add(overkategori);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return helbredstilstandList;
    }

}

