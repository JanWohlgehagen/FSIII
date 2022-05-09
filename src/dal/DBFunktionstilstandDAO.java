package dal;

import be.Borger;
import be.Funktionstilstand;
import be.FunktionstilstandsUnderkategori;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBFunktionstilstandDAO {

    private DBConnecting dbConnecting;

    public DBFunktionstilstandDAO(DBConnecting dbConnecting) {
        this.dbConnecting = dbConnecting;
    }

    public void createEmptyFunktionstilstand(Borger borger) {
        String sql = "INSERT INTO [F_Tilstandsvurdering (FS_Borger_ID, FS_UK_ID, Udfoerelse, Betydning, Borger_Maal, Niveau, Vurdering, Aarsag," +
                " Faglig_Notat, Forvente_Tilstand, opfoelgning) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = dbConnecting.getConnection()) {
            for (String key : borger.getFunktionstilstand().getFunktionsTilstandsKort().keySet()) {
                for (FunktionstilstandsUnderkategori funktionstilstandsUnderkategori : borger.getFunktionstilstand().getFunktionsTilstandsKort().get(key)) {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, borger.getIDProperty().get());
                    preparedStatement.setInt(2, funktionstilstandsUnderkategori.getId().get());

                    preparedStatement.executeQuery();

                }
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateFunktionstilstand(Borger borger) {
        String sql = "UPDATE [F_Tilstandsvurdering (FS_Borger_ID, FS_UK_ID, Udfoerelse, Betydning, Borger_Maal, Niveau, Vurdering, Aarsag," +
                " Faglig_Notat, Forvente_Tilstand, opfoelgning) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)" +
                "WHERE FS_Borger_ID = (?) AND FS_UK_ID = (?)";
        try (Connection connection = dbConnecting.getConnection()) {
            for (String key : borger.getFunktionstilstand().getFunktionsTilstandsKort().keySet()) {
                for (FunktionstilstandsUnderkategori funktionstilstandsUnderkategori : borger.getFunktionstilstand().getFunktionsTilstandsKort().get(key)) {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, borger.getIDProperty().get());
                    preparedStatement.setInt(2, funktionstilstandsUnderkategori.getId().get());
                    preparedStatement.setString(3, funktionstilstandsUnderkategori.getUdførelseProperty().get());
                    preparedStatement.setString(4, funktionstilstandsUnderkategori.getBetydningProperty().get());
                    preparedStatement.setInt(5, funktionstilstandsUnderkategori.getNiveauProperty().get());
                    preparedStatement.setString(6, funktionstilstandsUnderkategori.getVurderingProperty().get());
                    preparedStatement.setString(7, funktionstilstandsUnderkategori.getAarsagProperty().get());
                    preparedStatement.setString(8, funktionstilstandsUnderkategori.getFagligNotatProperty().get());
                    preparedStatement.setInt(9, funktionstilstandsUnderkategori.getForventetTilstandProperty().get());
                    preparedStatement.setString(10, funktionstilstandsUnderkategori.getOpfølgningProperty().get());

                    preparedStatement.setInt(11, borger.getIDProperty().get());
                    preparedStatement.setInt(12, funktionstilstandsUnderkategori.getId().get());

                    preparedStatement.executeQuery();

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
        List<FunktionstilstandsUnderkategori> OKListe1 = new ArrayList<>();
        List<FunktionstilstandsUnderkategori> OKListe2 = new ArrayList<>();
        List<FunktionstilstandsUnderkategori> OKListe3 = new ArrayList<>();
        List<FunktionstilstandsUnderkategori> OKListe4 = new ArrayList<>();
        List<FunktionstilstandsUnderkategori> OKListe5 = new ArrayList<>();
        String OkTitel1 = "";
        String OkTitel2 = "";
        String OkTitel3 = "";
        String OkTitel4 = "";
        String OkTitel5 = "";
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT *   FROM [F_Tilstandsvurdering]" +
                    "FULL JOIN [FS_Underkategori] ON F_Tilstandsvurdering.FS_UK_ID= FS_Underkategori.ID " +
                    "FULL JOIN [FS_Overkategori] on FS_Underkategori.FS_OK_ID = FS_Overkategori.ID " +
                    "WHERE FS_Borger_ID = (?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, borger.getIDProperty().get());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //Tilstandsvurdering
                int id = resultSet.getInt(2);
                String udfoerelse = resultSet.getString(4);
                String betydning = resultSet.getString(5);
                String borgerMaal = resultSet.getString(6);
                int niveau = resultSet.getInt(7);
                String vurdering = resultSet.getString(8);
                String aarsag = resultSet.getString(9);
                String fagligNotat = resultSet.getString(10);
                int forventetTilstand = resultSet.getInt(11);
                String opfoelgning = resultSet.getString(12);

                // Underkategori
                String underkategoriTitel = resultSet.getString(13);

                //Overkategori
                String overKategoriTitel = resultSet.getString(16);

                int UKID = resultSet.getInt(3);
                if (UKID == 1) {
                    OkTitel1 = overKategoriTitel;
                    FunktionstilstandsUnderkategori funktionstilstandsUnderkategori = new FunktionstilstandsUnderkategori(id, udfoerelse, betydning, borgerMaal, underkategoriTitel, vurdering, aarsag, fagligNotat, opfoelgning, overKategoriTitel, niveau, forventetTilstand);
                    OKListe1.add(funktionstilstandsUnderkategori);
                } else if (UKID == 2) {
                    OkTitel2 = overKategoriTitel;
                    FunktionstilstandsUnderkategori funktionstilstandsUnderkategori = new FunktionstilstandsUnderkategori(id, udfoerelse, betydning, borgerMaal, underkategoriTitel, vurdering, aarsag, fagligNotat, opfoelgning, overKategoriTitel, niveau, forventetTilstand);
                    OKListe2.add(funktionstilstandsUnderkategori);
                } else if (UKID == 3) {
                    OkTitel3 = overKategoriTitel;
                    FunktionstilstandsUnderkategori funktionstilstandsUnderkategori = new FunktionstilstandsUnderkategori(id, udfoerelse, betydning, borgerMaal, underkategoriTitel, vurdering, aarsag, fagligNotat, opfoelgning, overKategoriTitel, niveau, forventetTilstand);
                    OKListe3.add(funktionstilstandsUnderkategori);
                } else if (UKID == 4) {
                    OkTitel4 = overKategoriTitel;
                    FunktionstilstandsUnderkategori funktionstilstandsUnderkategori = new FunktionstilstandsUnderkategori(id, udfoerelse, betydning, borgerMaal, underkategoriTitel, vurdering, aarsag, fagligNotat, opfoelgning, overKategoriTitel, niveau, forventetTilstand);
                    OKListe4.add(funktionstilstandsUnderkategori);
                } else if (UKID == 5) {
                    OkTitel5 = overKategoriTitel;
                    FunktionstilstandsUnderkategori funktionstilstandsUnderkategori = new FunktionstilstandsUnderkategori(id, udfoerelse, betydning, borgerMaal, underkategoriTitel, vurdering, aarsag, fagligNotat, opfoelgning, overKategoriTitel, niveau, forventetTilstand);
                    OKListe5.add(funktionstilstandsUnderkategori);
                }

            }
            if (!OKListe1.isEmpty()) {
                funktionstilstand.addCategoryField(OkTitel1, OKListe1);
            }
            if (!OKListe2.isEmpty()) {
                funktionstilstand.addCategoryField(OkTitel2, OKListe2);
            }
            if (!OKListe3.isEmpty()) {
                funktionstilstand.addCategoryField(OkTitel3, OKListe3);
            }
            if (!OKListe4.isEmpty()) {
                funktionstilstand.addCategoryField(OkTitel4, OKListe4);
            }
            if (!OKListe5.isEmpty()) {
                funktionstilstand.addCategoryField(OkTitel5, OKListe5);
            }


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
                String overkategori = rs.getString("Titel");
                funktionstilstandList.add(overkategori);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return funktionstilstandList;
    }
}

