package be;

import javafx.beans.property.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Observation {
    private StringProperty description = new SimpleStringProperty();
    private StringProperty title = new SimpleStringProperty();

    public StringProperty getDescriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty getTitleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    @Override
    public String toString() {
        return title.get();
    }
}
