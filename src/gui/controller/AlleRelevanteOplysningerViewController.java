package gui.controller;

import be.Funktionstilstand;
import be.FunktionstilstandsUnderkategori;
import gui.resources.nodes.FunktionstilstandNode;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Separator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.security.Key;
import java.util.ResourceBundle;

public class AlleRelevanteOplysningerViewController implements Initializable {

    @FXML
    private GridPane parentPane;
    @FXML
    private VBox vBoxAllCategories;
    private Funktionstilstand funktionstilstand;


    public AlleRelevanteOplysningerViewController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            for(String key : funktionstilstand.getFunktionsTilstandsKort().keySet())
            {
                FunktionstilstandNode funktionstilstandNode = new FunktionstilstandNode(key);
                for (FunktionstilstandsUnderkategori fuk: funktionstilstand.getFunktionsTilstandsKort().get(key))
                {
                    if(fuk.getNiveauProperty().get() != 9 && fuk.getNiveauProperty().get() != -1)
                    {
                        funktionstilstandNode.addUK(fuk);
                    }
                }
                vBoxAllCategories.getChildren().add(funktionstilstandNode);
            }

        });
    }

    public void setFunktionstilstand(Funktionstilstand funktionstilstand) {
        this.funktionstilstand = funktionstilstand;
    }
}
