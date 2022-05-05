package dal;

import be.Case;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBCaseDAO {

    private DBConnecting dbConnecting;

    public DBCaseDAO(DBConnecting dbConnecting) {
        this.dbConnecting = dbConnecting;
    }

    public List<Case> getAllCasesOnCitizen(int citizenid){
        List<Case> allCases = new ArrayList<>();
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [Case] WHERE Borger_ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,citizenid);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
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
                aCase.getBevillingstekstProperty().set(bevillings_Tekst);
                aCase.getPlanProperty().set(plan);
                aCase.getOpfoelgningstagProperty().set(opfoelgnings_Tag);
                allCases.add(aCase);
            }

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
            return null;
        }
        return allCases;
    }

    public Case getCaseOnCitizen(int citizenID, int caseID){
        try (Connection connection = dbConnecting.getConnection()) {
            //TODO
            throw new UnsupportedOperationException();

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
            return null;
        }
    }

    public void updateCaseOnCitizen(int citizenID, Case selectCase){

        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "UPDATE [Case] SET Title = (?), Description = (?), Bevilling = (?), Bevillings_Tekst = (?), [Plan] = (?), Opfoelgnings_Tag = (?)" +
                         " WHERE Borger_ID = (?) AND ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, selectCase.getCaseTitleProperty().get());
            preparedStatement.setString(2, selectCase.getCaseDescriptionProperty().get());
            preparedStatement.setBoolean(3, selectCase.isBevilgetProperty().get());
            preparedStatement.setString(4, selectCase.getBevillingstekstProperty().get());
            preparedStatement.setString(5, selectCase.getPlanProperty().get());
            preparedStatement.setString(6, selectCase.getOpfoelgningstagProperty().get());
            preparedStatement.setInt(7, citizenID);
            preparedStatement.setInt(8, selectCase.getCaseIDProperty().get());

            preparedStatement.executeUpdate();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    public void deleteCaseOnCitizen(int citizenID, int caseID){
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "DELETE [Case] WHERE Borger_ID = (?) AND ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, citizenID);
            preparedStatement.setInt(2, caseID);

            preparedStatement.executeUpdate();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    public Case createCaseOnCitizen(Case newCase){
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "INSERT INTO [Case] (Borger_ID, Title, Description, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag) " +
                         "VALUES ( (?), (?), (?), (?), (?), (?), (?) )";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, newCase.getCitizenIDProperty().get());
            preparedStatement.setString(2, newCase.getCaseTitleProperty().get());
            preparedStatement.setString(3, newCase.getCaseDescriptionProperty().get());
            preparedStatement.setBoolean(4, newCase.isBevilgetProperty().get());
            preparedStatement.setString(5, newCase.getBevillingstekstProperty().get());
            preparedStatement.setString(6, newCase.getPlanProperty().get());
            preparedStatement.setString(7, newCase.getOpfoelgningstagProperty().get());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                newCase.setCaseID(id);
            }

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
            return null;
        }
        return newCase;
    }

}
