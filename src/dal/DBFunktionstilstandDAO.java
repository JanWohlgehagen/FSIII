package dal;

import be.Funktionstilstand;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBFunktionstilstandDAO {

    private DBConnecting dbConnecting;

    public DBFunktionstilstandDAO(DBConnecting dbConnecting) {
        this.dbConnecting = dbConnecting;
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