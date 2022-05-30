package be;

import javafx.beans.property.*;

public class Observation {
    private StringProperty description = new SimpleStringProperty();
    private StringProperty title = new SimpleStringProperty();

    public StringProperty getDescriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    @Override
    public String toString() {
        return title.get();
    }
}
