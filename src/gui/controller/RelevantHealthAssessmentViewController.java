package gui.controller;

import be.HealthAssessment;
import be.HelbredstilstandsUnderkategori;
import gui.resources.nodes.HelbredstilstandNode;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class RelevantHealthAssessmentViewController implements Initializable {
    @FXML
    private VBox vBoxMainbox;

    private HealthAssessment healthAssessment;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            for (String key : healthAssessment.getHelbredsTilstandsKort().keySet()) {
                HelbredstilstandNode helbredstilstandNode = new HelbredstilstandNode(key);
                for (HelbredstilstandsUnderkategori huk : healthAssessment.getHelbredsTilstandsKort().get(key)) {
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

    public void setHelbredstilstand(HealthAssessment healthAssessment) {
        this.healthAssessment = healthAssessment;
    }
}
