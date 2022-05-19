package gui.resources.nodes;

import be.HelbredstilstandsUnderkategori;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class HelbredstilstandNode extends VBox {

    private HelbredstilstandNode helbredstilstandNode;

    private Label lbOKTitel;

    public HelbredstilstandNode(String OKTitel)
    {
        lbOKTitel = new Label(OKTitel);

        lbOKTitel.setMinWidth(700);
        lbOKTitel.setMinHeight(50);
        lbOKTitel.setAlignment(Pos.CENTER);

        lbOKTitel.getStyleClass().add("labelTitleHelbredsOplysninger");

        this.setStyle("-fx-alignment: center");
        this.setPadding(new Insets(10,0,10,5));
        this.setSpacing(5);
        this.getChildren().add(lbOKTitel);
        this.setMaxWidth(-1);
        this.helbredstilstandNode= this;

    }

    public void addUK(HelbredstilstandsUnderkategori huk)
    {
        //VBox der indeholder hele UK
        VBox vBoxUK = new VBox();
        vBoxUK.setAlignment(Pos.CENTER);
        //Første gridpane der holder UK
        GridPane gridpaneUK_Tilstand_ForventetTilstand = new GridPane();
        Label lbUKTitel = new Label("Underkategori");
        Label lbNiveau = new Label("Niveau");
        Label lbforventetTilstand = new Label("Forventet Tilstand");
        gridpaneUK_Tilstand_ForventetTilstand.setPadding(new Insets(12,10,5,5));
        gridpaneUK_Tilstand_ForventetTilstand.setVgap(5);
        gridpaneUK_Tilstand_ForventetTilstand.setHgap(5);
        gridpaneUK_Tilstand_ForventetTilstand.setMaxWidth(800);
        gridpaneUK_Tilstand_ForventetTilstand.setMaxHeight(55);

        gridpaneUK_Tilstand_ForventetTilstand.add(lbUKTitel,0,0);
        gridpaneUK_Tilstand_ForventetTilstand.add(lbNiveau,1,0);
        gridpaneUK_Tilstand_ForventetTilstand.add(lbforventetTilstand,2,0);

        gridpaneUK_Tilstand_ForventetTilstand.add(createSmallTA(huk.getTilstandsklassifikationProperty().get()), 0, 1);
        gridpaneUK_Tilstand_ForventetTilstand.add(createSmallTA(huk.getTilstandProperty().get()), 1, 1);
        gridpaneUK_Tilstand_ForventetTilstand.add(createSmallTA(huk.getForventetTilstandProperty().get()), 2, 1);

        //Andet gridpane der holder alle tekstfelter
        GridPane gridpaneTekstfelter = new GridPane();
        gridpaneTekstfelter.setPadding(new Insets(12,0,5,5));
        gridpaneTekstfelter.setMaxWidth(800);
        gridpaneTekstfelter.setPrefWidth(800);
        gridpaneTekstfelter.setVgap(5);

        //Første række
        Label lbVurdering = new Label("Vurdering");
        gridpaneTekstfelter.add(lbVurdering, 0, 0);
        gridpaneTekstfelter.add(createTextArea(huk.getVurderingProperty().get()),0,1);

        //Anden række
        Label lbAarsag = new Label("Årsag");
        gridpaneTekstfelter.add(lbAarsag, 0, 2);
        gridpaneTekstfelter.add(createTextArea(huk.getAarsagProperty().get()), 0, 3);

        //Tredje række
        Label lbFagligNotat = new Label("Fagligt Notat");
        gridpaneTekstfelter.add(lbFagligNotat, 0, 4);
        gridpaneTekstfelter.add(createTextArea(huk.getFagligNotatProperty().get()), 0,5 );

        //Separator
        Separator separator = new Separator();
        separator.setPadding(new Insets(5,0,0,0));
        vBoxUK.getChildren().add(gridpaneUK_Tilstand_ForventetTilstand);
        vBoxUK.getChildren().add(gridpaneTekstfelter);
        vBoxUK.getChildren().add(separator);

        helbredstilstandNode.getChildren().add(vBoxUK);

    }

    private TextArea createSmallTA(String text)
    {
        TextArea txtArea = new TextArea(text);
        txtArea.setPromptText("Ikke udfyldt");
        txtArea.setWrapText(true);
        txtArea.setMaxHeight(75);
        txtArea.setEditable(false);
        txtArea.setFocusTraversable(false);
        txtArea.setStyle("-fx-font-weight: normal");
        return txtArea;
    }
    private TextArea createTextArea(String text)
    {
        TextArea txtArea = new TextArea(text);
        txtArea.setPromptText("Ikke udfyldt");
        txtArea.setWrapText(true);
        txtArea.setMinHeight(75);
        txtArea.setMaxHeight(75);
        txtArea.setMaxWidth(800);
        txtArea.setPrefWidth(800);
        txtArea.setEditable(false);
        txtArea.setFocusTraversable(false);
        txtArea.setStyle("-fx-font-weight: normal");
        return txtArea;

    }
}
