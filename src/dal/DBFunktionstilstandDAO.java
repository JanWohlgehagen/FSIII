package dal;

import be.Borger;
import be.Funktionstilstand;
import be.FunktionstilstandsUnderkategori;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBFunktionstilstandDAO {

    private DBConnecting dbConnecting;

    public DBFunktionstilstandDAO(DBConnecting dbConnecting) {
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
                    if(funktionstilstandsUnderkategori.getNiveauProperty().get() != 9) {
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
                                        tempString = funktionstilstandsUnderkategori.getUdførelseProperty().get();
                                        preparedStatementDelete.setInt(2, i);
                                        preparedStatementDelete.execute();
                                        preparedStatementInsert.setInt(2, i);
                                        preparedStatementInsert.setString(4, tempString);
                                        preparedStatementInsert.execute();
                                    }
                                }
                                case (9) -> {
                                    if (funktionstilstandsUnderkategori.().get() != null) {
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
                    } else {
                        for (int i = 1; i < 11; i++) {
                            preparedStatementDelete.setInt(2, i);
                            preparedStatementDelete.execute();
                        }
                    }


                    preparedStatementDelete.execute();
                    preparedStatementInsert.execute();

                }
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteFunktionstilstandOnCitizen(Borger borger) {
        String sql = "DELETE FROM [F_Tilstandsvurdering] WHERE FS_Borger_ID = (?)";
        try (Connection connection = dbConnecting.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, borger.getIDProperty().get());

            preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public Funktionstilstand getFunktionstilstandOnCitizen(Borger borger) {
        Funktionstilstand funktionstilstand = new Funktionstilstand();
        List<FunktionstilstandsUnderkategori> allFunktionstilstande = new ArrayList<>();
        HashMap<String, List<FunktionstilstandsUnderkategori>> funktionstilstandeHP = new HashMap();
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [F_Tilstandsvurdering]" +
                    "FULL JOIN [FS_Underkategori] ON F_Tilstandsvurdering.FS_UK_ID= FS_Underkategori.FS_Underkategori_ID " +
                    "FULL JOIN [FS_Overkategori] on FS_Underkategori.FS_OK_ID = FS_Overkategori.FS_Overkategori_ID " +
                    "WHERE FS_Borger_ID = (?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, borger.getIDProperty().get());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //Tilstandsvurdering
                int id = resultSet.getInt("FS_UK_ID");
                String udfoerelse = resultSet.getString("Udfoerelse");
                String betydning = resultSet.getString("Betydning");
                String borgerMaal = resultSet.getString("Borger_Maal");
                int niveau = resultSet.getInt("Niveau");
                String vurdering = resultSet.getString("Vurdering");
                String aarsag = resultSet.getString("Aarsag");
                String fagligNotat = resultSet.getString("Faglig_Notat");
                int forventetTilstand = resultSet.getInt("Forventet_Tilstand");
                String opfoelgning = resultSet.getString("Opfoelgning");

                // Underkategori
                String underkategoriTitel = resultSet.getString("FS_Underkategori_Title");

                //Overkategori
                String overKategoriTitel = resultSet.getString("FS_Overkategori_Titel");
                FunktionstilstandsUnderkategori f = new FunktionstilstandsUnderkategori(id, udfoerelse, betydning, borgerMaal, underkategoriTitel, vurdering, aarsag, fagligNotat, opfoelgning, overKategoriTitel, niveau, forventetTilstand);
                allFunktionstilstande.add(f);
            }

            for (FunktionstilstandsUnderkategori f : allFunktionstilstande) {
                if (!funktionstilstandeHP.containsKey(f.getOverKategoriProperty().get())) {
                    funktionstilstandeHP.put(f.getOverKategoriProperty().get(), new ArrayList<FunktionstilstandsUnderkategori>());
                }
                funktionstilstandeHP.get(f.getOverKategoriProperty().get()).add(f);
            }

            funktionstilstand.setFunktionsTilstande(funktionstilstandeHP);
            return funktionstilstand;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return funktionstilstand;
    }


    public List<String> getFunktionstilstandList() {
        List<String> funktionstilstandList = new ArrayList<>();
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [FS_Overkategori]";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String overkategori = rs.getString("FS_Overkategori_Titel");
                funktionstilstandList.add(overkategori);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return funktionstilstandList;
    }
}

