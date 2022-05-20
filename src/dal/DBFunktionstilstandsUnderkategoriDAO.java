package dal;

import be.Funktionstilstand;
import be.FunktionstilstandsUnderkategori;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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
                String underkategori = rs.getString("FS_Underkategori_Title");
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
        List<FunktionstilstandsUnderkategori> allFunktionstilstande = new ArrayList<>();
        HashMap<String, List<FunktionstilstandsUnderkategori>> funktionstilstandeHP = new HashMap();

        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT FS_Underkategori.FS_Underkategori_ID, FS_Underkategori.FS_Underkategori_Title, FS_Overkategori.FS_Overkategori_Titel FROM [FS_Underkategori]" +
                    "FULL JOIN [FS_Overkategori] ON FS_Underkategori.FS_OK_ID = FS_Overkategori.FS_Overkategori_ID";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int UKID = rs.getInt("FS_Underkategori_ID");
                String tilstandsKlassifikation = rs.getString("FS_Underkategori_Title");
                String overkategoriNavn = rs.getString("FS_Overkategori_Titel");


                FunktionstilstandsUnderkategori funktionstilstandsUnderkategori = new FunktionstilstandsUnderkategori(UKID, tilstandsKlassifikation, overkategoriNavn);
                allFunktionstilstande.add(funktionstilstandsUnderkategori);
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
            return null;
        }
    }
}




