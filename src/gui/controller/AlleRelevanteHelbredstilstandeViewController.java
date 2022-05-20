package gui.controller;

import be.FunktionstilstandsUnderkategori;
import be.Helbredstilstand;
import be.HelbredstilstandsUnderkategori;
import gui.resources.nodes.FunktionstilstandNode;
import gui.resources.nodes.HelbredstilstandNode;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AlleRelevanteHelbredstilstandeViewController implements Initializable {
    @FXML
    private VBox vBoxMainbox;

    private Helbredstilstand helbredstilstand;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            for (String key : helbredstilstand.getHelbredsTilstandsKort().keySet()) {
                HelbredstilstandNode helbredstilstandNode = new HelbredstilstandNode(key);
                for (HelbredstilstandsUnderkategori huk : helbredstilstand.getHelbredsTilstandsKort().get(key)) {
                    if (huk.getTilstandProperty().get() != null) {
                        if (!huk.getTilstandProperty().get().equals("Ingen aktuelle eller potentielle problemer")) {
                            helbredstilstandNode.addUK(huk);
                        }
                    }
                }
                vBoxMainbox.getChildren().add(helbredstilstandNode);
            }

        });
    }

    public void setHelbredstilstand(Helbredstilstand helbredstilstand) {
        this.helbredstilstand = helbredstilstand;
    }
}
