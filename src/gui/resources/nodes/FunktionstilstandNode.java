package gui.resources.nodes;

import be.FunktionstilstandsUnderkategori;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class FunktionstilstandNode extends VBox {
    private Label lbOkTitel;
    private TextArea txtOKTitel;
    private FunktionstilstandNode funktionstilstandNode;

    public FunktionstilstandNode(String OKTitel)
    {
        lbOkTitel = new Label("Overkategori");
        txtOKTitel = new TextArea(OKTitel);
        txtOKTitel.setMaxHeight(30);
        txtOKTitel.setMaxWidth(185);
        this.setPadding(new Insets(5,5,5,5));
        this.setSpacing(5);
        this.getChildren().add(lbOkTitel);
        this.getChildren().add(txtOKTitel);
        this.funktionstilstandNode = this;

    }

    public void addUK(FunktionstilstandsUnderkategori fuk)
    {
        //VBox der indeholder hele UK
        VBox vBoxUK = new VBox();

        //Første gridpane der holder UK, Niveau og Forventet Tilstand
        GridPane gridpaneUK_Niveau_ForventetTilstand = new GridPane();

        Label lbUKTitel = new Label("Underkategori");
        Label lbNiveau = new Label("Niveau");
        Label lbforventetTilstand = new Label("Forventet Tilstand");
        gridpaneUK_Niveau_ForventetTilstand.setPadding(new Insets(5,5,5,5));
        gridpaneUK_Niveau_ForventetTilstand.setVgap(5);
        gridpaneUK_Niveau_ForventetTilstand.setHgap(5);
        gridpaneUK_Niveau_ForventetTilstand.setMaxWidth(650);
        gridpaneUK_Niveau_ForventetTilstand.setMaxHeight(55);

        gridpaneUK_Niveau_ForventetTilstand.add(lbUKTitel,0,0);
        gridpaneUK_Niveau_ForventetTilstand.add(lbNiveau,1,0);
        gridpaneUK_Niveau_ForventetTilstand.add(lbforventetTilstand,2,0);

        TextArea txtUKTitel = new TextArea(fuk.getTilstandsklassifikationProperty().get());
        TextArea txtNiveau = new TextArea(String.valueOf(fuk.getNiveauProperty().get()));
        TextArea txtForventetTilstand = new TextArea(String.valueOf(fuk.getForventetTilstandProperty().get()));

        gridpaneUK_Niveau_ForventetTilstand.add(txtUKTitel,0,1);
        gridpaneUK_Niveau_ForventetTilstand.add(txtNiveau,1,1);
        gridpaneUK_Niveau_ForventetTilstand.add(txtForventetTilstand,2,1);

        //Andet gridpane der holder alle tekstfelter
        GridPane gridPaneTekstfelter = new GridPane();
        gridPaneTekstfelter.setPadding(new Insets(5,5,5,5));
        gridPaneTekstfelter.setVgap(5);
        gridPaneTekstfelter.setHgap(5);

        //Første række
        Label lbUdfoerelse = new Label("Udførelse");
        Label lbBetydning = new Label("Betydning");
        gridPaneTekstfelter.add(lbUdfoerelse,0,0);
        gridPaneTekstfelter.add(lbBetydning,1,0);

        TextArea txtUdfoerelse = new TextArea(fuk.getUdførelseProperty().get());
        TextArea txtBetydning = new TextArea(fuk.getBetydningProperty().get());
        txtUdfoerelse.setWrapText(true);
        txtBetydning.setWrapText(true);
        gridPaneTekstfelter.add(txtUdfoerelse,0,1);
        gridPaneTekstfelter.add(txtBetydning,1,1);

        //Anden række
        Label lbOenskerOgMaal = new Label("Ønsker og Mål");
        Label lbVurdering = new Label("Vurdering");
        gridPaneTekstfelter.add(lbOenskerOgMaal, 0,2);
        gridPaneTekstfelter.add(lbVurdering, 1,2);

        TextArea txtOenskerOgMaal = new TextArea(fuk.getOenskerOgMaalProperty().get());
        TextArea txtVurdering = new TextArea(fuk.getVurderingProperty().get());
        txtOenskerOgMaal.setWrapText(true);
        txtVurdering.setWrapText(true);
        gridPaneTekstfelter.add(txtOenskerOgMaal,0,3);
        gridPaneTekstfelter.add(txtVurdering,1,3);

        //Tredje række
        Label lbAarsag = new Label("Årsag");
        Label lbOpfoelgning = new Label("Opfølgning");
        gridPaneTekstfelter.add(lbAarsag,0,4);
        gridPaneTekstfelter.add(lbOpfoelgning,1,4);

        TextArea txtAarsag = new TextArea(fuk.getAarsagProperty().get());
        TextArea txtOpfoelgning = new TextArea(fuk.getOpfølgningProperty().get());
        txtAarsag.setWrapText(true);
        txtOpfoelgning.setWrapText(true);
        gridPaneTekstfelter.add(txtAarsag,0,5);
        gridPaneTekstfelter.add(txtOpfoelgning,1,5);

        //fjerde række
        Label lbObservation = new Label("Observation");
        gridPaneTekstfelter.add(lbObservation,0,6);


        TextArea txtObservation = new TextArea();
        txtObservation.setWrapText(true);
        gridPaneTekstfelter.add(txtObservation,0,7);
        vBoxUK.getChildren().add(gridpaneUK_Niveau_ForventetTilstand);
        vBoxUK.getChildren().add(gridPaneTekstfelter);

        funktionstilstandNode.getChildren().add(vBoxUK);

    }
}
