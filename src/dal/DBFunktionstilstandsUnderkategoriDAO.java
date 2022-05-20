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
            String sql = "SELECT * FROM [FC_Subcategory]";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String underkategori = rs.getString("FS_SC_Title");
                funktionstilstandsUnderkategoriList.add(underkategori);
            }
            for(String uk : funktionstilstandsUnderkategoriList)
            {
                System.out.println(uk);
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
            String sql = "SELECT FC_Subcategory.FC_SC_ID, FC_Subcategory.FC_SC_Title, FC_Category.FC_C_Title FROM [FC_Subcategory]" +
                    "FULL JOIN [FC_Category] ON FC_Subcategory.FC_SC_ID = FC_Category.FC_C_ID";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int UKID = rs.getInt("FC_SC_ID");
                String tilstandsKlassifikation = rs.getString("FC_SC_Title");
                String overkategoriNavn = rs.getString("FC_C_Title");


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
            for (String key :funktionstilstandeHP.keySet())
            {
                for (FunktionstilstandsUnderkategori fuk : funktionstilstandeHP.get(key))
                {
                    System.out.println(fuk);
                }

            }
            return funktionstilstand;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}




