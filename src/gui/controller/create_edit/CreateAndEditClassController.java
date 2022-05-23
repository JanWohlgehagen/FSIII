package gui.controller.create_edit;

import be.WClass;
import gui.model.UserModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateAndEditClassController implements Initializable {

    @FXML
    private GridPane parentPaneGridPane;

    @FXML
    private TextField txtName;

    @FXML
    private WClass wClass;

    private UserModel userModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(() -> {
            if (wClass != null) {
                txtName.setText(wClass.getNameProperty().get());
            }
        });

    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public void setClass(WClass wClass) {
        this.wClass = wClass;
    }

    public void btnSave(ActionEvent actionEvent) {
        if (wClass != null) {
            wClass.setName(txtName.getText());
            userModel.editClass(wClass);
        } else {
            WClass newClass = new WClass(txtName.getText());
            userModel.createClass(newClass);
        }
        Stage stage = (Stage) parentPaneGridPane.getScene().getWindow();
        stage.close();
    }
}
