package dal;

import be.*;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
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
                    PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setInt(1, borger.getIDProperty().get());
                    preparedStatement.setInt(2, helbredstilstandsUnderkategori.getId().get());

                    preparedStatement.executeQuery();

                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateHelbredstilstand(Borger borger) {
        String sql = "UPDATE [H_Tilstandsvurdering (HS_Borger_ID, HS_UK_ID, Tilstand, Vurdering, Aarsag, Faglig_Notat, Forventet_Tilstand) VALUES (?,?,?,?,?,?,?) " +
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

                    preparedStatement.setInt(8, borger.getIDProperty().get());
                    preparedStatement.setInt(9, helbredstilstandsUnderkategori.getId().get());

                    preparedStatement.executeQuery();

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

            preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
                String tilstand = resultSet.getString(4);
                String vurdering = resultSet.getString(5);
                String aarsag = resultSet.getString(6);
                String fagligNotat = resultSet.getString(7);
                String forventetTilstand = resultSet.getString(8);

                //Underkategori
                String underKategoriTitel = resultSet.getString(10);

                //Overkategori
                String overKategoriTitel = resultSet.getString(13);
                int OKID = resultSet.getInt(12);

                int UKID = resultSet.getInt(3);
                if (OKID == 1) {
                    OkTitel1 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe1.add(helbredstilstandsUnderkategori);
                } else if (OKID == 2) {
                    OkTitel2 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe2.add(helbredstilstandsUnderkategori);
                } else if (OKID == 3) {
                    OkTitel3 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe3.add(helbredstilstandsUnderkategori);
                } else if (OKID == 4) {
                    OkTitel4 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe4.add(helbredstilstandsUnderkategori);
                } else if (OKID == 5) {
                    OkTitel5 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe5.add(helbredstilstandsUnderkategori);
                } else if (OKID == 6) {
                    OkTitel6 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe6.add(helbredstilstandsUnderkategori);
                } else if (OKID == 7) {
                    OkTitel7 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe7.add(helbredstilstandsUnderkategori);
                } else if (OKID == 8) {
                    OkTitel8 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe8.add(helbredstilstandsUnderkategori);
                } else if (OKID == 9) {
                    OkTitel9 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe9.add(helbredstilstandsUnderkategori);
                } else if (OKID == 10) {
                    OkTitel10 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe10.add(helbredstilstandsUnderkategori);
                } else if (OKID == 11) {
                    OkTitel11 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe11.add(helbredstilstandsUnderkategori);
                } else if (OKID == 12) {
                    OkTitel12 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel, tilstand, forventetTilstand, vurdering, aarsag, fagligNotat);
                    OKListe12.add(helbredstilstandsUnderkategori);
                }

            }
            if (!OKListe1.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel1, OKListe1);
            }
            if (!OKListe2.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel2, OKListe2);
            }
            if (!OKListe3.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel3, OKListe3);
            }
            if (!OKListe4.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel4, OKListe4);
            }
            if (!OKListe5.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel5, OKListe5);
            }
            if (!OKListe6.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel6, OKListe6);
            }
            if (!OKListe7.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel7, OKListe7);
            }
            if (!OKListe8.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel8, OKListe8);
            }
            if (!OKListe9.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel9, OKListe9);
            }
            if (!OKListe10.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel10, OKListe10);
            }
            if (!OKListe11.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel11, OKListe11);
            }
            if (!OKListe12.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel12, OKListe12);
            }

            return helbredstilstand;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return helbredstilstand;


    }

    public Helbredstilstand getEmptyHelbredstilstand() {
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
            String sql = "SELECT HS_Underkategori.ID, HS_Underkategori.Titel, HS_Overkategori.Titel, HS_Overkategori.ID FROM [HS_Underkategori]" +
                    "FULL JOIN [HS_Overkategori] ON HS_Underkategori.HS_OK_ID = HS_Overkategori.ID";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int UKID = resultSet.getInt(1);

                //Underkategori
                String underKategoriTitel = resultSet.getString(2);

                //Overkategori
                String overKategoriTitel = resultSet.getString(3);
                int OKID = resultSet.getInt(4);


                if (OKID == 1) {
                    OkTitel1 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel);
                    OKListe1.add(helbredstilstandsUnderkategori);
                }
                if (OKID == 2) {
                    OkTitel2 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel);
                    OKListe2.add(helbredstilstandsUnderkategori);
                }
                if (OKID == 3) {
                    OkTitel3 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel);
                    OKListe3.add(helbredstilstandsUnderkategori);
                }
                if (OKID == 4) {
                    OkTitel4 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel);
                    OKListe4.add(helbredstilstandsUnderkategori);
                }
                if (OKID == 5) {
                    OkTitel5 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel);
                    OKListe5.add(helbredstilstandsUnderkategori);
                }
                if (OKID == 6) {
                    OkTitel6 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel);
                    OKListe6.add(helbredstilstandsUnderkategori);
                }
                if (OKID == 7) {
                    OkTitel7 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel);
                    OKListe7.add(helbredstilstandsUnderkategori);
                }
                if (OKID == 8) {
                    OkTitel8 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel);
                    OKListe8.add(helbredstilstandsUnderkategori);
                }
                if (OKID == 9) {
                    OkTitel9 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel);
                    OKListe9.add(helbredstilstandsUnderkategori);
                }
                if (OKID == 10) {
                    OkTitel10 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel);
                    OKListe10.add(helbredstilstandsUnderkategori);
                }
                if (OKID == 11) {
                    OkTitel11 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel);
                    OKListe11.add(helbredstilstandsUnderkategori);
                }
                if (OKID == 12) {
                    OkTitel12 = overKategoriTitel;
                    HelbredstilstandsUnderkategori helbredstilstandsUnderkategori = new HelbredstilstandsUnderkategori(UKID, underKategoriTitel, overKategoriTitel);
                    OKListe12.add(helbredstilstandsUnderkategori);
                }

            }
            if (!OKListe1.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel1, OKListe1);
            }
            if (!OKListe2.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel2, OKListe2);
            }
            if (!OKListe3.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel3, OKListe3);
            }
            if (!OKListe4.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel4, OKListe4);
            }
            if (!OKListe5.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel5, OKListe5);
            }
            if (!OKListe6.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel6, OKListe6);
            }
            if (!OKListe7.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel7, OKListe7);
            }
            if (!OKListe8.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel8, OKListe8);
            }
            if (!OKListe9.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel9, OKListe9);
            }
            if (!OKListe10.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel10, OKListe10);
            }
            if (!OKListe11.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel11, OKListe11);
            }
            if (!OKListe12.isEmpty()) {
                helbredstilstand.addCategoryField(OkTitel12, OKListe12);
            }

            return helbredstilstand;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}

