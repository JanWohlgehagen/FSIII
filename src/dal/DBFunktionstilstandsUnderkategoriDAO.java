package dal;

import be.Funktionstilstand;
import be.FunktionstilstandsUnderkategori;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBFunktionstilstandsUnderkategoriDAO {

    private DBConnecting dbConnecting;

    public DBFunktionstilstandsUnderkategoriDAO(DBConnecting dbConnecting) {
        this.dbConnecting = dbConnecting;
    }

    public List<String> getFunktionstilstandsUnderkategoriList() {
        List<String> funktionstilstandsUnderkategoriList = new ArrayList<>();
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [FS_Underkategori]";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String underkategori = rs.getString("Titel");
                funktionstilstandsUnderkategoriList.add(underkategori);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return funktionstilstandsUnderkategoriList;
    }

    public Funktionstilstand getEmptyFunktionstilstands() {
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
            String sql = "SELECT FS_Underkategori.ID, FS_Underkategori.Titel, FS_Overkategori.Titel FROM [FS_Underkategori]" +
                    "FULL JOIN [FS_Overkategori] ON FS_Underkategori.FS_OK_ID = FS_Overkategori.ID";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int UKID = rs.getInt(1);
                String tilstandsKlassifikation = rs.getString(2);
                String overkategoriNavn = rs.getString(3);



                if (UKID == 1) {
                    OkTitel1 = overkategoriNavn;
                    FunktionstilstandsUnderkategori funktionstilstandsUnderkategori = new FunktionstilstandsUnderkategori(UKID, tilstandsKlassifikation,overkategoriNavn);
                    OKListe1.add(funktionstilstandsUnderkategori);
                } else if (UKID == 2) {
                    OkTitel2 = overkategoriNavn;
                    FunktionstilstandsUnderkategori funktionstilstandsUnderkategori = new FunktionstilstandsUnderkategori(UKID, tilstandsKlassifikation,overkategoriNavn);
                    OKListe2.add(funktionstilstandsUnderkategori);
                } else if (UKID == 3) {
                    OkTitel3 = overkategoriNavn;
                    FunktionstilstandsUnderkategori funktionstilstandsUnderkategori = new FunktionstilstandsUnderkategori(UKID, tilstandsKlassifikation,overkategoriNavn);
                    OKListe3.add(funktionstilstandsUnderkategori);
                } else if (UKID == 4) {
                    OkTitel4 = overkategoriNavn;
                    FunktionstilstandsUnderkategori funktionstilstandsUnderkategori = new FunktionstilstandsUnderkategori(UKID, tilstandsKlassifikation,overkategoriNavn);
                    OKListe4.add(funktionstilstandsUnderkategori);
                } else if (UKID == 5) {
                    OkTitel5 = overkategoriNavn;
                    FunktionstilstandsUnderkategori funktionstilstandsUnderkategori = new FunktionstilstandsUnderkategori(UKID, tilstandsKlassifikation,overkategoriNavn);
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
            return null;
        }
    }
}



