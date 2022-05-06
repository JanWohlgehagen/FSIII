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

    public Funktionstilstand getFunktionstilstandOnCitizen(Borger borger)
    {
        Funktionstilstand funktionstilstand = new Funktionstilstand();
        List<FunktionstilstandsUnderkategori> OKListe1 = new ArrayList<>();
        List<FunktionstilstandsUnderkategori> OKListe2 = new ArrayList<>();
        List<FunktionstilstandsUnderkategori> OKListe3 = new ArrayList<>();
        List<FunktionstilstandsUnderkategori> OKListe4 = new ArrayList<>();
        List<FunktionstilstandsUnderkategori> OKListe5 = new ArrayList<>();
        try(Connection connection = dbConnecting.getConnection())
        {
            String sql = "SELECT *   FROM [F_Tilstandsvurdering]" +
                    "FULL JOIN [FS_Underkategori] ON F_Tilstandsvurdering.FS_UK_ID= FS_Underkategori.ID " +
                    "FULL JOIN [FS_Overkategori] on FS_Underkategori.FS_OK_ID = FS_Overkategori.ID " +
                    "WHERE FS_Borger_ID = (?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, borger.getIDProperty().get());

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String Udfoerelse = resultSet.getString(4);
                String Betydning = resultSet.getString(5);
                String BorgerMaal = resultSet.getString(6);
                String Niveau = resultSet.getString(7);
                String Vurdering = resultSet.getString(8);
                String Aarsag = resultSet.getString(9);
                String FagligNotat = resultSet.getString(10);

                int UKID = resultSet.getInt(3);
                if(UKID ==1)
                {

                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
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

/** public List<Case> getAllCasesOnCitizen(int citizenid){
 List<Case> allCases = new ArrayList<>();
 try (Connection connection = dbConnecting.getConnection()) {
 String sql = "SELECT * FROM [Case] WHERE Borger_ID = (?)";
 PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
 preparedStatement.setInt(1,citizenid);

 ResultSet resultSet = preparedStatement.executeQuery();

 if (resultSet.next()) {
 int id = resultSet.getInt("ID");
 String title = resultSet.getString("Title");
 String description = resultSet.getString("Description");
 boolean bevilling = resultSet.getBoolean("Bevilling");
 String bevillings_Tekst = resultSet.getString("Bevillings_Tekst");
 String plan = resultSet.getString("Plan");
 String opfoelgnings_Tag = resultSet.getString("Opfoelgnings_Tag");

 Case aCase = new Case(citizenid, title, description);
 aCase.setCaseID(id);
 aCase.isBevilgetProperty().set(bevilling);
 aCase.bevillingstekstProperty().set(bevillings_Tekst);
 aCase.planProperty().set(plan);
 aCase.opfoelgningstagProperty().set(opfoelgnings_Tag);
 allCases.add(aCase);
 }

 } catch (SQLException SQLe) {
 SQLe.printStackTrace();
 return null;
 }
 return allCases;
 } */
