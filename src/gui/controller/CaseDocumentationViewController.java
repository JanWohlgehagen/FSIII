package gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CaseDocumentationViewController {
    @FXML
    private ScrollPane scrollpaneDocumentations;
    @FXML
    private TextField txtTitel;
    @FXML
    private TextArea txtAreaDok;

    private CaseDocumentationViewController caseDocumentationViewController;

    public void btnNewDocumentation(ActionEvent actionEvent) {
    }

    public void btnSaveDocumentation(ActionEvent actionEvent) {
    }

    public void btnNext(ActionEvent actionEvent) {
    }

    public void setCaseDocumentationViewController(CaseDocumentationViewController caseDocumentationViewController) {
        this.caseDocumentationViewController = caseDocumentationViewController;
    }
}
