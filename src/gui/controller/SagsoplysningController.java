package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SagsoplysningController implements Initializable {
    @FXML
    private Button btnInformationMestring;
    @FXML
    private Button btnInformationMotivation;
    @FXML
    private Button btnInformationRessoucer;
    @FXML
    private Button btnInformationRoller;
    @FXML
    private Button btnInformationVaner;
    @FXML
    private Button btnInformationUddOgJob;
    @FXML
    private Button btnInformationLivshistorie;
    @FXML
    private Button btnInformationNetvaerk;
    @FXML
    private Button btnInformationHelbredsoplysninger;
    @FXML
    private Button btnInformationHjaelpemidler;
    @FXML
    private Button btnInformationBorgerensIndretning;

    SagsoplysningController sagsoplysningController;
    private TooltipBank tooltipBank = new TooltipBank();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Tooltip tooltip = new Tooltip(tooltipBank.getMestring());
        tooltip.setShowDuration(Duration.INDEFINITE);
        tooltip.setShowDelay(Duration.millis(0));
        tooltip.setWrapText(true);
        tooltip.setPrefWidth(250);


        btnInformationMestring.setTooltip(tooltip);
    }




    public void setSagsoplysningsController(SagsoplysningController sagsoplysningController){
        this.sagsoplysningController = sagsoplysningController;
    }

    private class TooltipBank{
        final private String mestring = "Definition: Borgerens bevidste eller ubevidste håndtering af livet/sygdommen – både udfordringer og muligheder." +
                            "\n \nDokumentationspraksis: Her dokumenteres, hvordan borgeren positivt eller negativt mestrer den modgang vedkommende møder.";

        final private String motivation = "Definition: Drivkraften bag at borgeren handler på en bestemt måde eller går i gang med/opretholder en opgave/indsats." +
                "\n \nDokumentationspraksis: Her dokumenteres borgerens ønsker for sit liv (overordnet mål), og hvad der motiverer borgeren.";

        final private String ressourcer = "Definition: De fysiske eller mentale kræfter, som borgerenen i et vist omfang har til rådighed og kan udnytte. Fysiske kræfter " +
                "kan fx være i form af fysisk sundhed og styrke. Mentale kræfter kan fx være i til situationer og andre mennesker på. " +
                "\n \nDokumentationspraksis: Her dokumenteres de ressourcer, borgeren har i forhold til at løse dagligdagens opgaver. Det kan være både fysiske og mentale funktioner.";

        final private String roller = "Definition: " +
                "\n \nDokumentationspraksis: ";

        final private String vaner = "Definition: " +
                "\n \nDokumentationspraksis: ";

        final private String uddannelseOgJob = "Definition: " +
                "\n \nDokumentationspraksis: ";

        final private String livshistorie = "Definition: " +
                "\n \nDokumentationspraksis: ";

        final private String netvaerk = "Definition: " +
                "\n \nDokumentationspraksis: ";

        final private String helbredsoplysninger = "Definition: " +
                "\n \nDokumentationspraksis: ";

        final private String hjaelpemidler = "Definition: " +
                "\n \nDokumentationspraksis: ";

        final private String borgerensIndretning = "Definition: " +
                "\n \nDokumentationspraksis: ";

        public String getMestring(){
            return this.mestring;
        }
        public String getMotivation(){
            return this.motivation;
        }
        public String getRessourcer(){
            return this.ressourcer;
        }
        public String getRoller(){
            return this.roller;
        }
        public String getVaner(){
            return this.vaner;
        }
        public String getUddannelseOgJob(){
            return this.uddannelseOgJob;
        }
        public String getLivshistorie(){
            return this.livshistorie;
        }
        public String getNetvaerk(){
            return this.netvaerk;
        }
        public String getHelbredsoplysninger(){
            return this.helbredsoplysninger;
        }
        public String getHjaelpemidler(){
            return this.hjaelpemidler;
        }
        public String getBorgerensIndretning(){
            return this.borgerensIndretning;
        }
    }
}
