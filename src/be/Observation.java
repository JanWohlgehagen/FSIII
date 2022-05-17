package be;

import javafx.beans.property.*;
import java.time.LocalDateTime;

public class Observation {
    private ObjectProperty<LocalDateTime> tidspunkt = new SimpleObjectProperty<>();
    private StringProperty description = new SimpleStringProperty();
}
