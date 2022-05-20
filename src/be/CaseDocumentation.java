package be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CaseDocumentation {
    private IntegerProperty caseID = new SimpleIntegerProperty();
    private IntegerProperty borgerID = new SimpleIntegerProperty();
    private IntegerProperty ID = new SimpleIntegerProperty();
    private StringProperty title = new SimpleStringProperty();
    private StringProperty text = new SimpleStringProperty();
    private LocalDateTime timeStamp;

    public CaseDocumentation(int caseID, int borgerID, String title, LocalDateTime timeStamp) {
        this.caseID.setValue(caseID);
        this.borgerID.setValue(borgerID);
        this.title.setValue(title);
        this.timeStamp = timeStamp;

    }

    public IntegerProperty caseIDProperty() {
        return caseID;
    }

    public void setCaseID(int caseID) {
        this.caseID.set(caseID);
    }


    public IntegerProperty borgerIDProperty() {
        return borgerID;
    }

    public void setBorgerID(int borgerID) {
        this.borgerID.set(borgerID);
    }

    public IntegerProperty IDProperty() {
        return ID;
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

}
