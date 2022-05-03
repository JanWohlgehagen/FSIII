package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnInformationMestring.setTooltip(new Tooltip("Borgerens bevidste eller ubevidste" +
                "håndtering af livet/sygdommen – både" +
                "udfordringer og muligheder."));
    }




    public void setSagsoplysningsController(SagsoplysningController sagsoplysningController){
        this.sagsoplysningController = sagsoplysningController;
    }
}
