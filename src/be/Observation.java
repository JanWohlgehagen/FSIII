package be;

import javafx.beans.property.*;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;

public class Observation {
    private StringProperty description = new SimpleStringProperty();
    private StringProperty titel = new SimpleStringProperty();

    public StringProperty getDescriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty getTitelProperty() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel.set(titel);
    }

    @Override
    public String toString() {
        return titel.get();
    }
}
