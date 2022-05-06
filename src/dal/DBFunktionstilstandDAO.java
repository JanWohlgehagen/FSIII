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
                    FunktionstilstandsUnderkategori funktionstilstandsUnderkategori = new FunktionstilstandsUnderkategori(udfoerelse, betydning, borgerMaal, underkategoriTitel, vurdering, aarsag, fagligNotat, opfoelgning, overKategoriTitel, niveau, forventetTilstand);
                    OKListe1.add(funktionstilstandsUnderkategori);
                } else if (UKID == 2) {
                    OkTitel2 = overKategoriTitel;
                    FunktionstilstandsUnderkategori funktionstilstandsUnderkategori = new FunktionstilstandsUnderkategori(udfoerelse, betydning, borgerMaal, underkategoriTitel, vurdering, aarsag, fagligNotat, opfoelgning, overKategoriTitel, niveau, forventetTilstand);
                    OKListe2.add(funktionstilstandsUnderkategori);
                } else if (UKID == 3) {
                    OkTitel3 = overKategoriTitel;
                    FunktionstilstandsUnderkategori funktionstilstandsUnderkategori = new FunktionstilstandsUnderkategori(udfoerelse, betydning, borgerMaal, underkategoriTitel, vurdering, aarsag, fagligNotat, opfoelgning, overKategoriTitel, niveau, forventetTilstand);
                    OKListe3.add(funktionstilstandsUnderkategori);
                } else if (UKID == 4) {
                    OkTitel4 = overKategoriTitel;
                    FunktionstilstandsUnderkategori funktionstilstandsUnderkategori = new FunktionstilstandsUnderkategori(udfoerelse, betydning, borgerMaal, underkategoriTitel, vurdering, aarsag, fagligNotat, opfoelgning, overKategoriTitel, niveau, forventetTilstand);
                    OKListe4.add(funktionstilstandsUnderkategori);
                } else if (UKID == 5) {
                    OkTitel5 = overKategoriTitel;
                    FunktionstilstandsUnderkategori funktionstilstandsUnderkategori = new FunktionstilstandsUnderkategori(udfoerelse, betydning, borgerMaal, underkategoriTitel, vurdering, aarsag, fagligNotat, opfoelgning, overKategoriTitel, niveau, forventetTilstand);
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



/**
 * public List<Case> getAllCasesOnCitizen(int citizenid){
 * List<Case> allCases = new ArrayList<>();
 * try (Connection connection = dbConnecting.getConnection()) {
 * String sql = "SELECT * FROM [Case] WHERE Borger_ID = (?)";
 * PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
 * preparedStatement.setInt(1,citizenid);
 * <p>
 * ResultSet resultSet = preparedStatement.executeQuery();
 * <p>
 * if (resultSet.next()) {
 * int id = resultSet.getInt("ID");
 * String title = resultSet.getString("Title");
 * String description = resultSet.getString("Description");
 * boolean bevilling = resultSet.getBoolean("Bevilling");
 * String bevillings_Tekst = resultSet.getString("Bevillings_Tekst");
 * String plan = resultSet.getString("Plan");
 * String opfoelgnings_Tag = resultSet.getString("Opfoelgnings_Tag");
 * <p>
 * Case aCase = new Case(citizenid, title, description);
 * aCase.setCaseID(id);
 * aCase.isBevilgetProperty().set(bevilling);
 * aCase.bevillingstekstProperty().set(bevillings_Tekst);
 * aCase.planProperty().set(plan);
 * aCase.opfoelgningstagProperty().set(opfoelgnings_Tag);
 * allCases.add(aCase);
 * }
 * <p>
 * } catch (SQLException SQLe) {
 * SQLe.printStackTrace();
 * return null;
 * }
 * return allCases;
 * }
 */
