package gui.controller;

import be.Borger;
import be.Funktionstilstand;
import be.Helbredstilstand;
import bll.ManagerFacade;
import gui.model.CitizenModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateCitizenViewController {

    @FXML
    private  GridPane parentPaneGridPane;
    @FXML
    private  TextField txtAge;
    @FXML
    private CheckBox checkButtonTemplate;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;

    private CitizenModel citizenModel;

    private CreateCitizenViewController createCitizenViewController;
    private DashboardController dashboardController;

    public CreateCitizenViewController() throws IOException {
        citizenModel = new CitizenModel(new ManagerFacade());
    }

    public void btnSave(ActionEvent actionEvent) {
        try{Integer.parseInt(txtAge.getText());}
        catch (Exception E)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Alder skal være et tal", ButtonType.OK);
            alert.show();
            return;
        }
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        boolean isTemplate= checkButtonTemplate.isSelected();
        int age = Integer.parseInt(txtAge.getText());
        Borger borger = new Borger(firstName, lastName, isTemplate, age);
        borger.setFunktionstilstand(new Funktionstilstand());
        citizenModel.createCitizen(borger);

        dashboardController.updateCitizenList();

        Stage stage = (Stage) parentPaneGridPane.getScene().getWindow();
        stage.close();
    }

    public void setCreateCitizenViewController(CreateCitizenViewController createCitizenViewController) {
        this.createCitizenViewController = createCitizenViewController;
    }

    public void setDashboardController(DashboardController dashboardController)
    {
        this.dashboardController = dashboardController;
    }
}
