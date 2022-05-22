package gui.controller;

import be.FunctionAssessment;
import be.FunktionstilstandsUnderkategori;
import gui.resources.nodes.FunktionstilstandNode;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class RelevantFunctionAssessmentViewController implements Initializable {

    @FXML
    private GridPane parentPane;
    @FXML
    private VBox vBoxAllCategories;
    private FunctionAssessment functionAssessment;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            for (String key : functionAssessment.getFunktionsTilstandsKort().keySet()) {
                FunktionstilstandNode funktionstilstandNode = new FunktionstilstandNode(key);
                for (FunktionstilstandsUnderkategori fuk : functionAssessment.getFunktionsTilstandsKort().get(key)) {
                    if (fuk.getNiveauProperty().get() != 9 && fuk.getNiveauProperty().get() != -1) {
                        funktionstilstandNode.addUK(fuk);
                    }
                }
                vBoxAllCategories.getChildren().add(funktionstilstandNode);
            }

        });
    }

    public void setFunctionAssessment(FunctionAssessment functionAssessment) {
        this.functionAssessment = functionAssessment;
    }
}
