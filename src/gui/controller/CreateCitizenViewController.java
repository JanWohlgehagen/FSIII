package gui.controller;

import be.Borger;
import gui.model.CitizenModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateCitizenViewController implements Initializable {

    @FXML
    private GridPane parentPaneGridPane;
    @FXML
    private TextField txtAge;
    @FXML
    private CheckBox checkButtonTemplate;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;

    private CitizenModel citizenModel;

    private boolean isTemplate;

    private DashboardController dashboardController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            checkButtonTemplate.setSelected(isTemplate);
        });
    }

    public void setTemplate(boolean template) {
        isTemplate = template;
    }

    public void btnSave(ActionEvent actionEvent) {
        try {
            Integer.parseInt(txtAge.getText());
        } catch (Exception E) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Alder skal være et tal", ButtonType.OK);
            alert.show();
            return;
        }
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        boolean isTemplate = checkButtonTemplate.isSelected();
        int age = Integer.parseInt(txtAge.getText());
        Borger borger = new Borger(firstName, lastName, isTemplate, age);
        citizenModel.createCitizen(borger);

        Stage stage = (Stage) parentPaneGridPane.getScene().getWindow();
        stage.close();
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public void setCitizenModel(CitizenModel citizenModel) {
        this.citizenModel = citizenModel;
    }


}
