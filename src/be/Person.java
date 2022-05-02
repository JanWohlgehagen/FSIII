package be;

import be.user.UserType;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.EnumSet;

public class Person {

    protected IntegerProperty id = new SimpleIntegerProperty();
    protected StringProperty firstName = new SimpleStringProperty();
    protected StringProperty lastName = new SimpleStringProperty();
    protected EnumSet<UserType> userType = EnumSet.noneOf(UserType.class);

    public Person(String firstName, String lastName) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
    }

    public void setUserType(UserType userType){
        this.userType.add(userType);
    }

    public boolean checkType(UserType userType){
        return this.userType.contains(userType);
    }

    public UserType getUserType(){
        if(!userType.isEmpty()){
            for (UserType type: userType) {
                return type;
            }
        }return null;
    }

    public IntegerProperty getIdProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public StringProperty getFirstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty getLastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
}
