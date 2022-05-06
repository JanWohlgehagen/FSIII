package dal;

import be.Borger;
import be.Funktionstilstand;
import be.Helbredstilstand;
import be.HelbredstilstandsUnderkategori;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBHelbredstilstandDAO {
    private DBConnecting dbConnecting;

    public DBHelbredstilstandDAO(DBConnecting dbConnecting) {
        this.dbConnecting = dbConnecting;
    }

    public Helbredstilstand getHelbredstilstandOnCitizen(Borger borger) {
        Helbredstilstand helbredstilstand = new Helbredstilstand();
        List<HelbredstilstandsUnderkategori> OKListe1 = new ArrayList<>();
        List<HelbredstilstandsUnderkategori> OKListe2 = new ArrayList<>();
        List<HelbredstilstandsUnderkategori> OKListe3 = new ArrayList<>();
        List<HelbredstilstandsUnderkategori> OKListe4 = new ArrayList<>();
        List<HelbredstilstandsUnderkategori> OKListe5 = new ArrayList<>();
        List<HelbredstilstandsUnderkategori> OKListe6 = new ArrayList<>();
        List<HelbredstilstandsUnderkategori> OKListe7 = new ArrayList<>();
        List<HelbredstilstandsUnderkategori> OKListe8 = new ArrayList<>();
        List<HelbredstilstandsUnderkategori> OKListe9 = new ArrayList<>();
        List<HelbredstilstandsUnderkategori> OKListe10 = new ArrayList<>();
        List<HelbredstilstandsUnderkategori> OKListe11 = new ArrayList<>();
        List<HelbredstilstandsUnderkategori> OKListe12 = new ArrayList<>();

        String OkTitel1 = "";
        String OkTitel2 = "";
        String OkTitel3 = "";
        String OkTitel4 = "";
        String OkTitel5 = "";
        String OkTitel6 = "";
        String OkTitel7 = "";
        String OkTitel8 = "";
        String OkTitel9 = "";
        String OkTitel10 = "";
        String OkTitel11 = "";
        String OkTitel12 = "";

        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [H_Tilstandsvurdering]" +
                    "FULL JOIN [HS_Underkategori] ON H_Tilstandsvurdering.HS_UK_ID = HS_Underkategori.ID " +
                    "FULL JOIN [HS_Overkategori] ON HS_Underkategori.HS_OK_ID = HS_Overkategori.ID " +
                    "WHERE HS_Borger_ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, borger.getIDProperty().get());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //tilstandsvurdering
                String udfald = resultSet.getString(4);
                String tilstand = resultSet.getString(5);
                String vurdering = resultSet.getString(6);
                String aarsag = resultSet.getString(7);
                String fagligNotat = resultSet.getString(8);
                String forventetTilstand = resultSet.getString(9);

                //Underkategori
                String underKategoriTitel = resultSet.getString(11);

                //Overkategori
                String overKategoriTitel = resultSet.getString(14);

                int UKID = resultSet.getInt(3);
                if (UKID == 1) {
                    OkTitel1= overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe1.add(helbredstilstandsUnderkategori);
                }
                if (UKID == 2) {
                    OkTitel2= overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe2.add(helbredstilstandsUnderkategori);
                }
                if (UKID == 3) {
                    OkTitel3= overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe3.add(helbredstilstandsUnderkategori);
                }
                if (UKID == 4) {
                    OkTitel4= overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe4.add(helbredstilstandsUnderkategori);
                }
                if (UKID == 5) {
                    OkTitel5= overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe5.add(helbredstilstandsUnderkategori);
                }
                if (UKID == 6) {
                    OkTitel6= overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe6.add(helbredstilstandsUnderkategori);
                }
                if (UKID == 7) {
                    OkTitel7= overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe7.add(helbredstilstandsUnderkategori);
                }
                if (UKID == 8) {
                    OkTitel8= overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe8.add(helbredstilstandsUnderkategori);
                }
                if (UKID == 9) {
                    OkTitel9= overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe9.add(helbredstilstandsUnderkategori);
                }
                if (UKID == 10) {
                    OkTitel10= overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe10.add(helbredstilstandsUnderkategori);
                }
                if (UKID == 11) {
                    OkTitel11= overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe11.add(helbredstilstandsUnderkategori);
                }
                if (UKID == 12) {
                    OkTitel12= overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe12.add(helbredstilstandsUnderkategori);
                }

            }
            if(!OKListe1.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel1, OKListe1);
                }
            if(!OKListe2.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel2, OKListe2);
                }
            if(!OKListe3.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel3, OKListe3);
                }
            if(!OKListe4.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel4, OKListe4);
                }
            if(!OKListe5.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel5, OKListe5);
                }
            if(!OKListe6.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel6, OKListe6);
                }
            if(!OKListe7.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel7, OKListe7);
                }
            if(!OKListe8.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel8, OKListe8);
                }
            if(!OKListe9.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel9, OKListe9);
                }
            if(!OKListe10.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel10, OKListe10);
                }
            if(!OKListe11.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel11, OKListe11);
                }
            if(!OKListe12.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel12, OKListe12);
                }

            return helbredstilstand;

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return helbredstilstand;


    }
}

