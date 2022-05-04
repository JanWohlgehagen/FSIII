package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SagsoplysningController implements Initializable {

    @FXML
    private TextArea txtAreaMestring;
    @FXML
    private TextArea txtAreaMotivaton;
    @FXML
    private TextArea txtAreaRessourcer;
    @FXML
    private TextArea txtAreaRoller;
    @FXML
    private TextArea txtAreaVaner;
    @FXML
    private TextArea txtAreaUddOgJob;
    @FXML
    private TextArea txtAreaLivshistorie;
    @FXML
    private TextArea txtAreaNetvaerk;
    @FXML
    private TextArea txtAreaHelbredsoplysninger;
    @FXML
    private TextArea txtAreaHjaelpemidler;
    @FXML
    private TextArea txtAreaBoligensIndretning;



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
    private Button btnInformationBoligensIndretning;

    SagsoplysningController sagsoplysningController;
    private TooltipBank tooltipBank = new TooltipBank();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Setting up tooltips for the information buttons in the view that guides the student
        btnInformationMestring.setTooltip(tooltipBank.getMestring());
        btnInformationMotivation.setTooltip(tooltipBank.getMotivation());
        btnInformationRessoucer.setTooltip(tooltipBank.getRessourcer());
        btnInformationRoller.setTooltip(tooltipBank.getRoller());
        btnInformationVaner.setTooltip(tooltipBank.getVaner());
        btnInformationUddOgJob.setTooltip(tooltipBank.getUddannelseOgJob());
        btnInformationLivshistorie.setTooltip(tooltipBank.getLivshistorie());
        btnInformationNetvaerk.setTooltip(tooltipBank.getNetvaerk());
        btnInformationHelbredsoplysninger.setTooltip(tooltipBank.getHelbredsoplysninger());
        btnInformationHjaelpemidler.setTooltip(tooltipBank.getHjaelpemidler());
        btnInformationBoligensIndretning.setTooltip(tooltipBank.getBoligensIndretning());

    }


    public void setSagsoplysningsController(SagsoplysningController sagsoplysningController){
        this.sagsoplysningController = sagsoplysningController;
    }

    public void generelleOplysningerHandleSaveAndNextBtn(MouseEvent mouseEvent) {
        //TODO
    }

    public void generelleOplysningerHandleSaveAndExitBtn(MouseEvent mouseEvent) {
        //TODO
    }

    public void helbredstilstandHandleSaveAndExitBtn(KeyEvent keyEvent) {
        //TODO
    }

    public void helbredstilstandHandleSaveAndNextBtn(MouseEvent mouseEvent) {
        //TODO
    }


    private class TooltipBank{
        final private double TOOLTIP_WIDTH = 250.0;

        final private String mestring = "Definition: Borgerens bevidste eller ubevidste håndtering af livet/sygdommen – både udfordringer og muligheder." +
                            "\n \nDokumentationspraksis: Her dokumenteres, hvordan borgeren positivt eller negativt mestrer den modgang vedkommende møder.";

        final private String motivation = "Definition: Drivkraften bag at borgeren handler på en bestemt måde eller går i gang med/opretholder en opgave/indsats." +
                "\n \nDokumentationspraksis: Her dokumenteres borgerens ønsker for sit liv (overordnet mål), og hvad der motiverer borgeren.";

        final private String ressourcer = "Definition: De fysiske eller mentale kræfter, som borgerenen i et vist omfang har til rådighed og kan udnytte. Fysiske kræfter " +
                "kan fx være i form af fysisk sundhed og styrke. Mentale kræfter kan fx være i til situationer og andre mennesker på. " +
                "\n \nDokumentationspraksis: Her dokumenteres de ressourcer, borgeren har i forhold til at løse dagligdagens opgaver. Det kan være både fysiske og mentale funktioner.";

        final private String roller = "Definition: De roller som er særligt vigtige for borgeren i forhold til familie, arbejde og samfund." +
                "\n \nDokumentationspraksis: Her dokumenteres de roller, borgeren angiver at have. Det kan fx være rolle som ægtefælle, " +
                "bedsteforælder eller aktiv i beboerforening.";

        final private String vaner = "Definition: Regelmæssig adfærd som borgeren har tillært gennem stadig gentagelse og udførelse helt eller delvist ubevidst. " +
                "Vaner er fx døgnrytmen, måden at blive tiltalt på, kontakt med medmennesker og relationer, måde at anskue verden på" +
                "\n \nDokumentationspraksis: Her dokumenteres borgerens vaner, som er en naturlig del af hverdagen, og som borgeren plejer at gøre. Det kan både" +
                "være vaner, som borgeren gør af fysiske og psykiske årsager.";

        final private String uddannelseOgJob = "Definition: Nuværende eller tidligere uddannelses\u0002og/eller erhvervsmæssig baggrund." +
                "Fx folkeskole, erhvervsuddannelse og videregående uddannelse." +
                "\n \nDokumentationspraksis: Her dokumenteres borgerens oplysninger om uddannelse og erhverv. Fx hvilke job borgeren har haft, hvilken " +
                "betydning arbejdslivet har haft for borgeren, og har borgeren evt. stadig tilknytning til arbejdspladsen og kolleger.";

        final private String livshistorie = "Definition: En beskrivelse af borgerens oplevelse af væsentlige begivenheder, interesser og gøremål igennem livet." +
                "\n \nDokumentationspraksis: Her dokumenteres borgerens fortælling om sit liv. \n" +
                "Her dokumenteres borgerens ønsker for den sidste tid.";

        final private String netvaerk = "Definition: Personer som er tæt på borgeren, og som giver praktisk og/eller følelsesmæssigt støtte og omsorg " +
                "overfor borgeren. Netværk kan være offentligt eller privat. Et offentligt netværk består af personlige hjælpere, sundhedspersonale og andre " +
                "professionelle primært omsorgsgivere. Privat netværk er familie, slægtning, venner og bekendtskaber." +
                "\n \nDokumentationspraksis: Her dokumenteres borgerens netværk i bredere forstand.";

        final private String helbredsoplysninger = "Definition: \nHelbredsoplysninger: Aktuelle eller tidligere sygdomme og handicap der har betydning for borgerens situation. \n" +
                "Sundhedsfaglige kontakter: Medarbejder eller enheder indenfor sundhedsvæsenet borgeren er tilknyttet, fx øjenlæge, tandlæge, fodterapeut eller afdeling/ambulatorium." +
                "\n \nDokumentationspraksis: Her kan borgerens sygdomme og handicap dokumenteres for at give et samlet overblik. Hvis oplysningen kommer fra borgeren " +
                "eller pårørende, skal dette fremgå. Her kan behandlingsansvarlig læge/ambulatorie ift. en konkret sygdom dokumenteres, hvis dette ikke er angivet andre steder.";

        final private String hjaelpemidler = "Definition: Udstyr, produkter og teknologi som anvendes af borgeren i daglige aktiviteter, inkl. sådanne som er tilpasset " +
                "eller særligt fremstillet til, implanteret i, placeret på eller nær personen, som anvender dem. (Inkl. almindelige genstande og hjælpemidler og teknologi til personlig anvendelse). " +
                "\n \nDokumentationspraksis: Her dokumenteres de hjælpemidler borger selv har anskaffet. Bevilligede hjælpemidler kan også dokumenteres eller vises her. ";

        final private String boligensIndretning = "Definition: En beskrivelse af boligens fysiske rammer og omgivelser, der har betydning for borgerens hverdagsliv og funktionsevne." +
                "\n \nDokumentationspraksis: Her dokumenteres både det der hæmmer og fremmer borgerens funktionsevne i hverdagen. Kan suppleres med praktiske oplysninger" +
                "fx om der er elevator, dørtrin eller trapper.";

        public Tooltip getMestring(){
            Tooltip tooltip = new Tooltip(this.mestring);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }
        public Tooltip getMotivation(){
            Tooltip tooltip = new Tooltip(this.motivation);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }
        public Tooltip getRessourcer(){
            Tooltip tooltip = new Tooltip(this.ressourcer);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }
        public Tooltip getRoller(){
            Tooltip tooltip = new Tooltip(this.roller);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }
        public Tooltip getVaner(){
            Tooltip tooltip = new Tooltip(this.vaner);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }
        public Tooltip getUddannelseOgJob(){
            Tooltip tooltip = new Tooltip(this.uddannelseOgJob);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }
        public Tooltip getLivshistorie(){
            Tooltip tooltip = new Tooltip(this.livshistorie);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }
        public Tooltip getNetvaerk(){
            Tooltip tooltip = new Tooltip(this.netvaerk);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }
        public Tooltip getHelbredsoplysninger(){
            Tooltip tooltip = new Tooltip(this.helbredsoplysninger);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }
        public Tooltip getHjaelpemidler(){
            Tooltip tooltip = new Tooltip(this.hjaelpemidler);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }
        public Tooltip getBoligensIndretning(){
            Tooltip tooltip = new Tooltip(this.boligensIndretning);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }
    }
}
