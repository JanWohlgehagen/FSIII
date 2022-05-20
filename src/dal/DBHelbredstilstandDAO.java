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

    public void createEmptyHelbredstilstandOnCitizen(Borger borger) {
        String sql = "INSERT INTO [H_Tilstandsvurdering] (HS_Borger_ID, HS_UK_ID) VALUES (?,?)";
        try (Connection connection = dbConnecting.getConnection()) {
            for (String key : borger.getHelbredstilstand().getHelbredsTilstandsKort().keySet()) {
                for (HelbredstilstandsUnderkategori helbredstilstandsUnderkategori : borger.getHelbredstilstand().getHelbredsTilstandsKort().get(key)) {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, borger.getIDProperty().get());
                    preparedStatement.setInt(2, helbredstilstandsUnderkategori.getId().get());

                    preparedStatement.execute();
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateHelbredstilstand(Borger borger) {
        String sql = "UPDATE [H_Tilstandsvurdering] SET HS_Borger_ID = (?), HS_UK_ID = (?), Tilstand = (?), Vurdering = (?), Aarsag = (?), Faglig_Notat = (?), Forventet_Tilstand = (?), Observation = (?), ObservationTime = (?) " +
                "WHERE HS_Borger_ID = (?) AND HS_UK_ID = (?)";
        try (Connection connection = dbConnecting.getConnection()) {
            for (String key : borger.getHelbredstilstand().getHelbredsTilstandsKort().keySet()) {
                for (HelbredstilstandsUnderkategori helbredstilstandsUnderkategori : borger.getHelbredstilstand().getHelbredsTilstandsKort().get(key)) {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, borger.getIDProperty().get());
                    preparedStatement.setInt(2, helbredstilstandsUnderkategori.getId().get());
                    preparedStatement.setString(3, helbredstilstandsUnderkategori.getTilstandProperty().get());
                    preparedStatement.setString(4, helbredstilstandsUnderkategori.getVurderingProperty().get());
                    preparedStatement.setString(5, helbredstilstandsUnderkategori.getAarsagProperty().get());
                    preparedStatement.setString(6, helbredstilstandsUnderkategori.getFagligNotatProperty().get());
                    preparedStatement.setString(7, helbredstilstandsUnderkategori.getForventetTilstandProperty().get());
                    preparedStatement.setString(8, helbredstilstandsUnderkategori.getObservation().getDescriptionProperty().get());
                    preparedStatement.setTimestamp(9, helbredstilstandsUnderkategori.getObservation().getTidspunkt());

                    preparedStatement.setInt(10, borger.getIDProperty().get());
                    preparedStatement.setInt(11, helbredstilstandsUnderkategori.getId().get());
                    preparedStatement.execute();

                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteHelbredstilstand(Borger borger) {
        String sql = "DELETE FROM [H_Tilstandsvurdering] WHERE HS_Borger_ID = (?)";
        try (Connection connection = dbConnecting.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, borger.getIDProperty().get());

            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public Helbredstilstand getHelbredstilstandOnCitizen(Borger borger) {
        Helbredstilstand helbredstilstand = new Helbredstilstand();

        HashMap<String, List<HelbredstilstandsUnderkategori>> helbredstilstandeHP = new HashMap<>();
        List<HelbredstilstandsUnderkategori> allHelbredstilstandeUK = new ArrayList<>();

        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [H_Tilstandsvurdering]" +
                    "FULL JOIN [HS_Underkategori] ON H_Tilstandsvurdering.HS_UK_ID = HS_Underkategori.HS_Underkategori_ID " +
                    "FULL JOIN [HS_Overkategori] ON HS_Underkategori.HS_OK_ID = HS_Overkategori.HS_Overkategori_ID " +
                    "WHERE HS_Borger_ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, borger.getIDProperty().get());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                //tilstandsvurdering
                int ID = resultSet.getInt("HS_UK_ID");
                String tilstand = resultSet.getString("Tilstand");
                String vurdering = resultSet.getString("Vurdering");
                String aarsag = resultSet.getString("Aarsag");
                String fagligNotat = resultSet.getString("Faglig_Notat");
                String forventetTilstand = resultSet.getString("Forventet_Tilstand");
                String observationDescription = resultSet.getString("Observation");
                Timestamp tidspunkt = resultSet.getTimestamp("ObservationTime");

                Observation observation = new Observation();
                observation.setDescription(observationDescription);
                observation.setTidspunkt(tidspunkt);

                //Underkategori
                String underKategoriTitel = resultSet.getString("HS_Underkategori_Titel");
                observation.setTitel(underKategoriTitel);

                //Overkategori
                String overKategoriTitel = resultSet.getString("HS_Overkategori_Titel");

                HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(ID, underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat, observation);
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
            String sql = "SELECT HS_Underkategori.HS_Underkategori_ID, HS_Underkategori.HS_Underkategori_Titel, HS_Overkategori.HS_Overkategori_Titel, HS_Overkategori.HS_Overkategori_ID FROM [HS_Underkategori]" +
                    "FULL JOIN [HS_Overkategori] ON HS_Underkategori.HS_OK_ID = HS_Overkategori.HS_Overkategori_ID";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int UKID = resultSet.getInt("HS_Underkategori_ID");

                //Underkategori
                String underKategoriTitel = resultSet.getString("HS_Underkategori_Titel");

                //Overkategori
                String overKategoriTitel = resultSet.getString("HS_Overkategori_Titel");

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
            String sql = "SELECT * FROM [HS_Overkategori]";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String overkategori = rs.getString("HS_Overkategori_Titel");
                helbredstilstandList.add(overkategori);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
        return helbredstilstandList;
    }

}

