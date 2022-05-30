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

    public List<Case> getAllCasesOnCitizen(int citizenid) {
        List<Case> allCases = new ArrayList<>();
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "SELECT * FROM [Case] WHERE Borger_ID = (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, citizenid);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("Case_ID");
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
                aCase.setDescription(description);
                aCase.setReference(henvisning);
                aCase.setCause(aasagsfritekst);
                aCase.setCauseDiagnosis(aasagsdiagnose);
                aCase.setCauseCondition(aasagstilstand);
                aCase.setCitizenWishes(borgerensonsker);
                aCase.setCaseResponsible(sagsansvarlig);
                aCase.isGrantedProperty().set(bevilling);
                aCase.getGrantedTextProperty().set(bevillings_Tekst);
                aCase.getPlanProperty().set(plan);
                aCase.getFollowUpTagProperty().set(opfoelgnings_Tag);
                allCases.add(aCase);
            }

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
            return null;
        }
        return allCases;
    }

    public void updateCaseOnCitizen(int citizenID, Case selectCase) {

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
            preparedStatement.setString(1, selectCase.getOverCategoryTitleProperty().get());
            preparedStatement.setString(2, selectCase.getSubcategoryTitleProperty().get());
            preparedStatement.setString(3, selectCase.getReferenceProperty().get());
            preparedStatement.setString(4, selectCase.getCauseDiagnosisProperty().get());
            preparedStatement.setString(5, selectCase.getCauseProperty().get());
            preparedStatement.setString(6, selectCase.getCauseConditionProperty().get());
            preparedStatement.setString(7, selectCase.getCitizenWishesProperty().get());
            preparedStatement.setString(8, selectCase.getDescriptionProperty().get());
            preparedStatement.setBoolean(9, selectCase.isGrantedProperty().get());
            preparedStatement.setString(10, selectCase.getGrantedTextProperty().get());
            preparedStatement.setString(11, selectCase.getPlanProperty().get());
            preparedStatement.setString(12, selectCase.getFollowUpTagProperty().get());
            preparedStatement.setString(13, selectCase.getCaseResponsibleProperty().get());
            preparedStatement.setInt(14, citizenID);
            preparedStatement.setInt(15, selectCase.getCaseIDProperty().get());

            preparedStatement.executeUpdate();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    public void deleteCaseOnCitizen(int citizenID, int caseID) {
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

    public Case createCaseOnCitizen(Case newCase) {
        try (Connection connection = dbConnecting.getConnection()) {
            String sql = "INSERT INTO [Case] (Borger_ID, OverkategoriTitle, UnderkategoriTitle, Henvisning,  Aasagsdiagnose,  Aasagsfritekst, Aasagstilstand, Borgerensonsker, Description, Bevilling, Bevillings_Tekst, [Plan], Opfoelgnings_Tag, SagsAnsvarlig)" +
                    " VALUES ( (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?) )";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, newCase.getCitizenIDProperty().get());
            preparedStatement.setString(2, newCase.getOverCategoryTitleProperty().get());
            preparedStatement.setString(3, newCase.getSubcategoryTitleProperty().get());
            preparedStatement.setString(4, newCase.getReferenceProperty().get());
            preparedStatement.setString(5, newCase.getCauseDiagnosisProperty().get());
            preparedStatement.setString(6, newCase.getCauseProperty().get());
            preparedStatement.setString(7, newCase.getCauseConditionProperty().get());
            preparedStatement.setString(8, newCase.getCitizenWishesProperty().get());
            preparedStatement.setString(9, newCase.getDescriptionProperty().get());
            preparedStatement.setBoolean(10, newCase.isGrantedProperty().get());
            preparedStatement.setString(11, newCase.getGrantedTextProperty().get());
            preparedStatement.setString(12, newCase.getPlanProperty().get());
            preparedStatement.setString(13, newCase.getFollowUpTagProperty().get());
            preparedStatement.setString(14, newCase.getCaseResponsibleProperty().get());

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
