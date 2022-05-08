package gui.controller;

import be.Case;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class BestillingsViewController implements Initializable {
    @FXML
    private TextArea txtAreaBestillingsText;
    @FXML
    private CheckBox checkBoxBevilling;

    private BestillingsViewController bestillingsViewController;

    private Case currentCase;
    private DashboardController dashBoardController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void btnNext(ActionEvent actionEvent) {
        currentCase.setIsBevilget(checkBoxBevilling.isSelected());
        currentCase.setBevillingstekst(txtAreaBestillingsText.getText());
    }

    public void setBestillingsViewController(BestillingsViewController bestillingsViewController) {
        this.bestillingsViewController = bestillingsViewController;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashBoardController = dashboardController;
        currentCase = dashBoardController.getSelectedCase();

        if (currentCase.isBevilgetProperty().get()) {
            checkBoxBevilling.setSelected(true);
        }
        txtAreaBestillingsText.setText(currentCase.getBevillingstekstProperty().get());
    }
}
