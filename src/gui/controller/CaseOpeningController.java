package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class CaseOpeningController {

    @FXML
    private ComboBox vaelgSagCbx;
    @FXML
    private Label fornavnLbl;
    @FXML
    private Label efternavnLbl;
    @FXML
    private Label alderLbl;
    @FXML
    private Label antalAktiveSagerLbl;

    @FXML
    private Button createSagBtn;
    @FXML
    private Button editSagBtn;
    @FXML
    private Button deleteSagBtn;
    @FXML
    private Button videreBtn;

    @FXML
    private TextArea sagTextArea;
}
