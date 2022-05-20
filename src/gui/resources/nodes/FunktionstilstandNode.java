package gui.resources.nodes;

import be.FunktionstilstandsUnderkategori;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;


public class FunktionstilstandNode extends VBox {
    private Label lbOkTitel;

    private FunktionstilstandNode funktionstilstandNode;

    public FunktionstilstandNode(String OKTitel) {
        lbOkTitel = new Label(OKTitel);


        lbOkTitel.setMinWidth(700);
        lbOkTitel.setMinHeight(50);
        lbOkTitel.setAlignment(Pos.CENTER);


        lbOkTitel.getStyleClass().add("labelTitleFunktionsOplysninger");


        this.setStyle("-fx-alignment: center");
        this.setPadding(new Insets(5, 0, 5, 5));
        this.setSpacing(5);
        this.getChildren().add(lbOkTitel);
        this.setMaxWidth(-1);
        this.funktionstilstandNode = this;

    }

    public void addUK(FunktionstilstandsUnderkategori fuk) {
        //VBox der indeholder hele UK
        VBox vBoxUK = new VBox();

        //Første gridpane der holder UK, Niveau og Forventet Tilstand
        GridPane gridpaneUK_Niveau_ForventetTilstand = new GridPane();

        Label lbUKTitel = new Label("Underkategori");
        Label lbNiveau = new Label("Niveau");
        Label lbforventetTilstand = new Label("Forventet Tilstand");
        gridpaneUK_Niveau_ForventetTilstand.setPadding(new Insets(12, 10, 5, 5));
        gridpaneUK_Niveau_ForventetTilstand.setVgap(5);
        gridpaneUK_Niveau_ForventetTilstand.setHgap(5);
        gridpaneUK_Niveau_ForventetTilstand.setMaxWidth(750);
        gridpaneUK_Niveau_ForventetTilstand.setMaxHeight(55);

        gridpaneUK_Niveau_ForventetTilstand.add(lbUKTitel, 0, 0);
        gridpaneUK_Niveau_ForventetTilstand.add(lbNiveau, 1, 0);
        gridpaneUK_Niveau_ForventetTilstand.add(lbforventetTilstand, 2, 0);

        gridpaneUK_Niveau_ForventetTilstand.add(createSmallTA(fuk.getTilstandsklassifikationProperty().get()), 0, 1);
        gridpaneUK_Niveau_ForventetTilstand.add(createSmallTA(String.valueOf(fuk.getNiveauProperty().get())), 1, 1);
        gridpaneUK_Niveau_ForventetTilstand.add(createSmallTA(String.valueOf(fuk.getForventetTilstandProperty().get())), 2, 1);

        //Andet gridpane der holder alle tekstfelter
        GridPane gridPaneTekstfelter = new GridPane();
        gridPaneTekstfelter.setPadding(new Insets(5, 5, 5, 5));
        gridPaneTekstfelter.setVgap(5);
        gridPaneTekstfelter.setHgap(8);
        gridPaneTekstfelter.setMaxWidth(800);

        //Første række
        Label lbUdfoerelse = new Label("Udførelse");
        Label lbBetydning = new Label("Betydning");
        gridPaneTekstfelter.add(lbUdfoerelse, 0, 0);
        gridPaneTekstfelter.add(lbBetydning, 1, 0);

        gridPaneTekstfelter.add(createTextArea(fuk.getUdførelseProperty().get()), 0, 1);
        gridPaneTekstfelter.add(createTextArea(fuk.getBetydningProperty().get()), 1, 1);

        //Anden række
        Label lbOenskerOgMaal = new Label("Ønsker og Mål");
        Label lbVurdering = new Label("Vurdering");
        gridPaneTekstfelter.add(lbOenskerOgMaal, 0, 2);
        gridPaneTekstfelter.add(lbVurdering, 1, 2);

        gridPaneTekstfelter.add(createTextArea(fuk.getOenskerOgMaalProperty().get()), 0, 3);
        gridPaneTekstfelter.add(createTextArea(fuk.getVurderingProperty().get()), 1, 3);

        //Tredje række
        Label lbAarsag = new Label("Årsag");
        Label lbOpfoelgning = new Label("Opfølgning");
        gridPaneTekstfelter.add(lbAarsag, 0, 4);
        gridPaneTekstfelter.add(lbOpfoelgning, 1, 4);

        gridPaneTekstfelter.add(createTextArea(fuk.getAarsagProperty().get()), 0, 5);
        gridPaneTekstfelter.add(createTextArea(fuk.getOpfølgningProperty().get()), 1, 5);

        //fjerde række
        Label lbObservation = new Label("Observation");
        gridPaneTekstfelter.add(lbObservation, 0, 6);

        //TODO
        TextArea txtObservation = new TextArea();
        txtObservation.setWrapText(true);
        txtObservation.setMaxHeight(100);
        gridPaneTekstfelter.add(txtObservation, 0, 7);

        //Seperator
        Separator separator = new Separator();
        separator.setPadding(new Insets(5, 0, 0, 0));
        vBoxUK.getChildren().add(gridpaneUK_Niveau_ForventetTilstand);
        vBoxUK.getChildren().add(gridPaneTekstfelter);
        vBoxUK.getChildren().add(separator);


        funktionstilstandNode.getChildren().add(vBoxUK);

    }

    private TextArea createSmallTA(String text) {
        TextArea txtArea = new TextArea(text);
        txtArea.setPromptText("Ikke udfyldt");
        txtArea.setWrapText(true);
        txtArea.setMaxHeight(75);
        txtArea.setEditable(false);
        txtArea.setFocusTraversable(false);
        txtArea.setStyle("-fx-font-weight: normal");
        return txtArea;
    }

    private TextArea createTextArea(String text) {
        TextArea txtArea = new TextArea(text);
        txtArea.setPromptText("Ikke udfyldt");
        txtArea.setWrapText(true);
        txtArea.setMaxHeight(100);
        txtArea.setEditable(false);
        txtArea.setFocusTraversable(false);
        txtArea.setStyle("-fx-font-weight: normal");
        return txtArea;

    }

}
