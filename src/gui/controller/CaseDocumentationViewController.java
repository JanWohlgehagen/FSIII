package gui.controller;

import be.Case;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.swing.*;

public class CaseDocumentationViewController {
    @FXML
    private ScrollPane scrollpaneDocumentations;
    @FXML
    private TextField txtTitel;
    @FXML
    private TextArea txtAreaDok;

    private CaseDocumentationViewController caseDocumentationViewController;
    private Case currentCase;

    public void btnNewDocumentation(ActionEvent actionEvent) {
        /// TODO: 09/05/2022
    }

    public void setCaseDocumentationViewController(CaseDocumentationViewController caseDocumentationViewController) {
        this.caseDocumentationViewController = caseDocumentationViewController;
    }
    public void setCurrentCase(Case currentCase)
    {
        this.currentCase = currentCase;
    }

    public void handleMouseSaveAndClose(MouseEvent mouseEvent) {
        /// TODO: 09/05/2022
    }

    public void handleMouseSaveAndNextScene(MouseEvent mouseEvent) {
        /// TODO: 09/05/2022
    }
}
