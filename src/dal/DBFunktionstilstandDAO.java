package dal;

import be.Borger;
import be.Funktionstilstand;
import be.FunktionstilstandsUnderkategori;
import be.Observation;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBFunktionstilstandDAO {

    private DBConnecting dbConnecting;

    public DBFunktionstilstandDAO(DBConnecting dbConnecting) {
        this.dbConnecting = dbConnecting;
    }

    public void createEmptyFunktionstilstand(Borger borger) {
        String sql = "INSERT INTO [F_Tilstandsvurdering] (FS_Borger_ID, FS_UK_ID) VALUES (?,?)";
        try (Connection connection = dbConnecting.getConnection()) {
            for (String key : borger.getFunktionstilstand().getFunktionsTilstandsKort().keySet()) {
                for (FunktionstilstandsUnderkategori funktionstilstandsUnderkategori : borger.getFunktionstilstand().getFunktionsTilstandsKort().get(key)) {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, borger.getIDProperty().get());
                    preparedStatement.setInt(2, funktionstilstandsUnderkategori.getId().get());

                    preparedStatement.execute();
                }
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateFunktionstilstand(Borger borger) {
        String sql = "UPDATE [F_Tilstandsvurdering] SET FS_Borger_ID = (?), FS_UK_ID = (?), Udfoerelse = (?), Betydning = (?), Borger_Maal = (?), Niveau = (?)," +
                " Vurdering = (?), Aarsag = (?), Faglig_Notat = (?), Forventet_Tilstand = (?), Observation = (?), ObservationTime = (?), Opfoelgning = (?)" +
                "WHERE FS_Borger_ID = (?) AND FS_UK_ID = (?)";
        try (Connection connection = dbConnecting.getConnection()) {
            for (String key : borger.getFunktionstilstand().getFunktionsTilstandsKort().keySet()) {
                for (FunktionstilstandsUnderkategori funktionstilstandsUnderkategori : borger.getFunktionstilstand().getFunktionsTilstandsKort().get(key)) {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, borger.getIDProperty().get());
                    preparedStatement.setInt(2, funktionstilstandsUnderkategori.getId().get());
                    preparedStatement.setString(3, funktionstilstandsUnderkategori.getUdførelseProperty().get());
                    preparedStatement.setString(4, funktionstilstandsUnderkategori.getBetydningProperty().get());
                    preparedStatement.setString(5, funktionstilstandsUnderkategori.getOenskerOgMaalProperty().get());
                    preparedStatement.setInt(6, funktionstilstandsUnderkategori.getNiveauProperty().get());
                    preparedStatement.setString(7, funktionstilstandsUnderkategori.getVurderingProperty().get());
                    preparedStatement.setString(8, funktionstilstandsUnderkategori.getAarsagProperty().get());
                    preparedStatement.setString(9, funktionstilstandsUnderkategori.getFagligNotatProperty().get());
                    preparedStatement.setInt(10, funktionstilstandsUnderkategori.getForventetTilstandProperty().get());
                    preparedStatement.setString(11,funktionstilstandsUnderkategori.getObservation().getDescriptionProperty().get());
                    preparedStatement.setTimestamp(12, funktionstilstandsUnderkategori.getObservation().getTidspunkt());
                    preparedStatement.setString(13, funktionstilstandsUnderkategori.getOpfølgningProperty().get());

                    preparedStatement.setInt(14, borger.getIDProperty().get());
                    preparedStatement.setInt(15, funktionstilstandsUnderkategori.getId().get());

                    preparedStatement.execute();

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
                String observationDescription = resultSet.getString("Observation");
                Timestamp tidspunkt = resultSet.getTimestamp("ObservationTime");
                String opfoelgning = resultSet.getString("Opfoelgning");

                Observation observation = new Observation();
                observation.setDescription(observationDescription);
                observation.setTidspunkt(tidspunkt);

                // Underkategori
                String underkategoriTitel = resultSet.getString("FS_Underkategori_Title");

                //Overkategori
                String overKategoriTitel = resultSet.getString("FS_Overkategori_Titel");
                FunktionstilstandsUnderkategori f = new FunktionstilstandsUnderkategori(id, udfoerelse, betydning, borgerMaal, underkategoriTitel, vurdering, aarsag, fagligNotat, opfoelgning, overKategoriTitel, niveau, forventetTilstand, observation);
                allFunktionstilstande.add(f);
            }

            for (FunktionstilstandsUnderkategori f : allFunktionstilstande) {
                if (!funktionstilstandeHP.containsKey(f.getOverKategoriProperty().get())) {
                    funktionstilstandeHP.put(f.getOverKategoriProperty().get(), new ArrayList<>());
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

