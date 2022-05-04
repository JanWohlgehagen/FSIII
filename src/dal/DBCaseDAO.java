package dal;

import be.Case;
import be.Person;

import java.sql.*;
import java.util.List;

public class DBCaseDAO {

    private DBConnecting dbConnecting;

    public DBCaseDAO(DBConnecting dbConnecting) {
        this.dbConnecting = dbConnecting;
    }

    public List<Case> getAllCasesOnCitizen(int id){
        try (Connection connection = dbConnecting.getConnection()) {
            //TODO
            throw new UnsupportedOperationException();

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
            return null;
        }
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

    public void updateCaseOnCitizen(int citizenID, int caseID){
        try (Connection connection = dbConnecting.getConnection()) {
            //TODO
            throw new UnsupportedOperationException();

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    public void deleteCaseOnCitizen(int citizenID, int caseID){
        try (Connection connection = dbConnecting.getConnection()) {
            //TODO
            throw new UnsupportedOperationException();

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    public Case createCaseOnCitizen(int citizenID){
        try (Connection connection = dbConnecting.getConnection()) {
            //TODO
            throw new UnsupportedOperationException();

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
            return null;
        }
    }
}
