package gui.controller;

import be.Case;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UdfoerelseIOgLeveringController {
    @FXML
    private GridPane parentPane;
    @FXML
    private ScrollPane scrollpaneDocumentations;
    @FXML
    private TextField txtTitel;
    @FXML
    private TextArea txtAreaDok;

    private UdfoerelseIOgLeveringController udfoerelseIOgLeveringController;
    private Case currentCase;

    public void btnNewDocumentation(ActionEvent actionEvent) {
        /// TODO: 09/05/2022
    }

    public void setCaseDocumentationViewController(UdfoerelseIOgLeveringController udfoerelseIOgLeveringController) {
        this.udfoerelseIOgLeveringController = udfoerelseIOgLeveringController;
    }
    public void setCurrentCase(Case currentCase)
    {
        this.currentCase = currentCase;
    }

    public void handleMouseSaveAndClose(MouseEvent mouseEvent) {
        // TODO: 5/9/2022  
        getStage().close();
    }

    public void handleMouseSaveAndNextScene(MouseEvent mouseEvent) {
        /// TODO: 09/05/2022
    }

    private Stage getStage(){
        return (Stage) parentPane.getScene().getWindow();
    }
}
