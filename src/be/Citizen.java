package be;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Citizen {

    private final IntegerProperty ID = new SimpleIntegerProperty();
    private final IntegerProperty age = new SimpleIntegerProperty();
    private final StringProperty firstName = new SimpleStringProperty();
    private final StringProperty lastName = new SimpleStringProperty();
    private final BooleanProperty isTemplate = new SimpleBooleanProperty();
    private int studentID;
    private ObservableList<Observation> observations = FXCollections.observableArrayList();
    private FunctionAssessment functionAssessment;
    private HealthAssessment healthAssessment;
    private Generalinformation generalinformation;
    private User student;

    public Citizen(String firstName, String lastName, boolean isTemplate, int age) {
        this.firstName.set(firstName);
        this.lastName.set(lastName);
        this.isTemplate.set(isTemplate);
        this.age.set(age);
        generalinformation = new Generalinformation();
    }

    public ObservableList<Observation> getObservations() {
        return observations;
    }

    public void setObservations(ObservableList<Observation> observations) {
        this.observations = observations;
    }

    public IntegerProperty getAgeProperty() {
        return age;
    }

    public BooleanProperty isTemplateProperty() {
        return isTemplate;
    }

    public IntegerProperty getIDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public StringProperty getFirstNameProperty() {
        return firstName;
    }

    public StringProperty getLastNameProperty() {
        return lastName;
    }

    public FunctionAssessment getFunktionstilstand() {
        return functionAssessment;
    }

    public void setFunktionstilstand(FunctionAssessment functionAssessment) {
        this.functionAssessment = functionAssessment;
    }

    public HealthAssessment getHelbredstilstand() {
        return healthAssessment;
    }

    public void setHelbredstilstand(HealthAssessment healthAssessment) {
        this.healthAssessment = healthAssessment;
    }

    public Generalinformation getGeneralinformation() {
        return generalinformation;
    }

    public void setGeneralinformation(Generalinformation generalinformation) {
        this.generalinformation = generalinformation;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public User getStudent() {
        return student;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getStudentID() {
        return studentID;
    }

    @Override
    public String toString() {
        return firstName.get() + " " + lastName.get();
    }
}
