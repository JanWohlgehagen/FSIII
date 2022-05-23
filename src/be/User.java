package be;

import be.Credential;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.EnumSet;

public class User {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastName = new SimpleStringProperty();
    private StringProperty fullName = new SimpleStringProperty();
    private EnumSet<UserType> userType = EnumSet.noneOf(UserType.class);
    private Credential credential;

    private StringProperty type = new SimpleStringProperty();

    public User(String firstName, String lastName) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
    }

    public enum UserType {
        TEACHER,
        STUDENT,
        ADMIN
    }

    public void setUserType(String userType) {
        switch (userType) {
            case "STUDENT" -> {
                this.userType.add(UserType.STUDENT);
            }
            case "TEACHER" -> {
                this.userType.add(UserType.TEACHER);
            }
            case "ADMIN" -> {
                this.userType.add(UserType.ADMIN);
            }
        }
    }

    public UserType getUserType() {
        if (!userType.isEmpty()) {
            for (UserType type : userType) {
                return type;
            }
        }
        return null;
    }

    public StringProperty getUserTypeStringProperty() {
        if (!userType.isEmpty()) {
            for (UserType type : userType) {
                this.type.set(type.name());
                return this.type;
            }
        }
        return null;
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

    public StringProperty getFullNameProperty() {
        fullName.set(this.firstName.get() + " " + this.lastName.get());
        return this.fullName;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    @Override
    public String toString() {
        return id.get() + ": " + getFullNameProperty().get();
    }
}
