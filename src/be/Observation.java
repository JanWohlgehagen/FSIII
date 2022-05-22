package be;

import javafx.beans.property.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Observation {
    private ObjectProperty<Timestamp> time = new SimpleObjectProperty<>();
    private StringProperty description = new SimpleStringProperty();
    private StringProperty title = new SimpleStringProperty();

    public StringProperty getDescriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Timestamp getTime() {
        return time.get();
    }

    public void setTime(Timestamp newTime) {
        this.time.set(newTime);
    }

    public StringProperty getTitleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    private String formattingOfTime() {
        return new SimpleDateFormat("dd-MM-yyyy  HH:mm").format(this.time);
    }

    @Override
    public String toString() {
        return formattingOfTime() + " : " + title.get();
    }
}
