package gui.controller;

import be.Case;
import gui.util.BestillingsScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;

public class BestillingsViewController {
    @FXML
    private TextArea txtAreaBestillingsText;
    @FXML
    private CheckBox checkBoxBevilling;

    private BestillingsViewController bestillingsViewController;

    private Case currentCase;



    public void btnNext(ActionEvent actionEvent) {
        currentCase.setIsBevilget(checkBoxBevilling.isSelected());
        currentCase.setBevillingstekst(txtAreaBestillingsText.getText());
    }

   public void setBestillingsViewController(BestillingsViewController bestillingsViewController)
   {
       this.bestillingsViewController = bestillingsViewController;
   }
   public void setCurrentCase(Case currentCase)
   {
       this.currentCase = currentCase;
       if(currentCase.isBevilgetProperty().get())
       {
           checkBoxBevilling.setSelected(true);
       }
       txtAreaBestillingsText.setText(currentCase.bevillingstekstProperty().get());
   }
}
