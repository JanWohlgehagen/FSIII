package be;

import javafx.beans.property.*;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;

public class Observation {
    private ObjectProperty<Timestamp> tidspunkt = new SimpleObjectProperty<>();
    private StringProperty description = new SimpleStringProperty();
    private StringProperty titel = new SimpleStringProperty();

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

    public StringProperty getTitelProperty() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel.set(titel);
    }

    private String formattingOfTidspunkt() {
        return new SimpleDateFormat("dd-MM-yyyy  HH:mm").format(tidspunkt.get());
    }

    @Override
    public String toString() {
        return formattingOfTidspunkt() + " : " + titel.get();
    }
}
