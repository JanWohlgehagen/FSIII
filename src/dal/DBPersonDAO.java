package dal;

import be.Person;

public class DBPersonDAO {

    private DBConnecting dbConnecting;

    public DBPersonDAO(DBConnecting dbConnecting) {
        this.dbConnecting = dbConnecting;
    }

    public Person getPersonById(int id){
        //TODO
        throw new UnsupportedOperationException();
    }
}
