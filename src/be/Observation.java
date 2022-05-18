package be;

import javafx.beans.property.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Observation {
    private ObjectProperty<Timestamp> tidspunkt = new SimpleObjectProperty<>();
    private StringProperty description = new SimpleStringProperty();

    public StringProperty getDescriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public Timestamp getTidspunkt() {
        return tidspunkt.get();
    }

    public void setTidspunkt(Timestamp tidspunkt) {
        this.tidspunkt.set(tidspunkt);
    }
}
