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
                String overkategoriTitle = resultSet.getString("OverkategoriTitle");
                String underkategoriTitle = resultSet.getString("UnderkategoriTitle");
                String henvisning = resultSet.getString("Henvisning");
                String aasagsfritekst = resultSet.getString("Aasagsfritekst");
                String aasagsdiagnose = resultSet.getString("Aasagsdiagnose");
                String aasagstilstand = resultSet.getString("Aasagstilstand");
                String borgerensonsker = resultSet.getString("Borgerensonsker");
                String sagsansvarlig = resultSet.getString("SagsAnsvarlig");
                String description = resultSet.getString("Description");
                boolean bevilling = resultSet.getBoolean("Bevilling");
                String bevillings_Tekst = resultSet.getString("Bevillings_Tekst");
                String plan = resultSet.getString("Plan");
                String opfoelgnings_Tag = resultSet.getString("Opfoelgnings_Tag");

                Case aCase = new Case(citizenid, overkategoriTitle, underkategoriTitle);
                aCase.setCaseID(id);
                aCase.setCaseDescription(description);
                aCase.setHenvisning(henvisning);
                aCase.setAasagsfritekst(aasagsfritekst);
                aCase.setAasagsdiagnose(aasagsdiagnose);
                aCase.setAasagstilstand(aasagstilstand);
                aCase.setBorgerensonsker(borgerensonsker);
                aCase.setSagsansvarlig(sagsansvarlig);
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
            String sql = "UPDATE [Case] SET " +
                    "OverkategoriTitle = (?), " +
                    "UnderkategoriTitle = (?), " +
                    "Henvisning = (?), " +
                    "Aasagsdiagnose = (?), " +
                    "Aasagsfritekst = (?), " +
                    "Aasagstilstand = (?), " +
                    "Borgerensonsker = (?), " +
                    "Description = (?), " +
                    "Bevilling = (?), " +
                    "Bevillings_Tekst = (?), " +
                    "[Plan] = (?), " +
                    "Opfoelgnings_Tag = (?), " +
                    "SagsAnsvarlig = (?)" +
                         " WHERE Borger_ID = (?) AND Case_ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, selectCase.getOverkategoriTitleProperty().get());
            preparedStatement.setString(2, selectCase.getUnderkategoriTitleProperty().get());
            preparedStatement.setString(3, selectCase.getHenvisningProperty().get());
            preparedStatement.setString(4, selectCase.getAasagsdiagnoseProperty().get());
            preparedStatement.setString(5, selectCase.getAasagsfritekstProperty().get());
            preparedStatement.setString(6, selectCase.getAasagstilstandProperty().get());
            preparedStatement.setString(7, selectCase.getBorgerensonskerProperty().get());
            preparedStatement.setString(8, selectCase.getCaseDescriptionProperty().get());
            preparedStatement.setBoolean(9, selectCase.isBevilgetProperty().get());
            preparedStatement.setString(10, selectCase.getBevillingstekstProperty().get());
            preparedStatement.setString(11, selectCase.getPlanProperty().get());
            preparedStatement.setString(12, selectCase.getOpfoelgningstagProperty().get());
            preparedStatement.setString(13, selectCase.getSagsansvarligProperty().get());
            preparedStatement.setInt(14, citizenID);
            preparedStatement.setInt(15, selectCase.getCaseIDProperty().get());

            preparedStatement.executeUpdate();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    public void deleteCaseOnCitizen(int citizenID, int caseID){
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "DELETE [Case] WHERE Borger_ID = (?) AND Case_ID = (?)";
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
            String sql = "INSERT INTO [Case] (Borger_ID, OverkategoriTitle, UnderkategoriTitle, Henvisning,  Aasagsdiagnose,  Aasagsfritekst, Aasagstilstand, Borgerensonsker, Description, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag, SagsAnsvarlig)" +
                    " VALUES ( (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?) )";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, newCase.getCitizenIDProperty().get());
            preparedStatement.setString(2, newCase.getOverkategoriTitleProperty().get());
            preparedStatement.setString(3, newCase.getUnderkategoriTitleProperty().get());
            preparedStatement.setString(4, newCase.getHenvisningProperty().get());
            preparedStatement.setString(5, newCase.getAasagsdiagnoseProperty().get());
            preparedStatement.setString(6, newCase.getAasagsfritekstProperty().get());
            preparedStatement.setString(7, newCase.getAasagstilstandProperty().get());
            preparedStatement.setString(8, newCase.getBorgerensonskerProperty().get());
            preparedStatement.setString(9, newCase.getCaseDescriptionProperty().get());
            preparedStatement.setBoolean(10, newCase.isBevilgetProperty().get());
            preparedStatement.setString(11, newCase.getBevillingstekstProperty().get());
            preparedStatement.setString(12, newCase.getPlanProperty().get());
            preparedStatement.setString(13, newCase.getOpfoelgningstagProperty().get());
            preparedStatement.setString(14, newCase.getSagsansvarligProperty().get());

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
