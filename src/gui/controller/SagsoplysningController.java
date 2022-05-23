package gui.controller;

import be.*;
import gui.model.CaseModel;
import gui.model.CitizenModel;
import gui.util.BestillingsScene;
import gui.util.FunktionsTilstandOverviewScene;
import gui.util.HelbredsTilstandOverviewScene;
import gui.util.ISceneLoader;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class SagsoplysningController implements Initializable {

    @FXML
    private Label lblOverkategoriFunktionstilstand;
    @FXML
    private Label lblTilstandsklassifikationFunktionstilstand;
    @FXML
    private Label lblOverkategoriHelbredstilstand;
    @FXML
    private Label lblTilstandsklassifikationHelbredstilstand;

    @FXML
    private TextArea txtAreaVurderingHelbredstilstand;
    @FXML
    private TextArea txtAreaAarsagHelbredstilstand;
    @FXML
    private TextArea txtAreaFagligtNotatHelbredstilstand;
    @FXML
    private TextArea txtAreaMestring;
    @FXML
    private TextArea txtAreaMotivaton;
    @FXML
    private TextArea txtAreaRessourcer;
    @FXML
    private TextArea txtAreaRoller;
    @FXML
    private TextArea txtAreaVaner;
    @FXML
    private TextArea txtAreaUddOgJob;
    @FXML
    private TextArea txtAreaLivshistorie;
    @FXML
    private TextArea txtAreaNetvaerk;
    @FXML
    private TextArea txtAreaHelbredsoplysninger;
    @FXML
    private TextArea txtAreaHjaelpemidler;
    @FXML
    private TextArea txtAreaBoligensIndretning;
    @FXML
    private TextArea txtAreaUdfoerelseFunktionstilstand;
    @FXML
    private TextArea txtAreaBetydningFunktionstilstand;
    @FXML
    private TextArea txtAreaOenskerOgMålFunktionstilstand;
    @FXML
    private TextArea txtAreaVurderingFunktionstilstand;
    @FXML
    private TextArea txtAreaAarsagFunktionstilstand;
    @FXML
    private TextArea txtAreaFagligtNotatFunktionstilstand;
    @FXML
    private TextField txtOpfoelgningFunktionstilstand;
    @FXML
    private TextArea txtAreaHelhedsvurdering;
    @FXML
    private TextArea txtAreaObservationFunktionstilstand1;
    @FXML
    private TextArea txtAreaObservationHelbredstilstand1;


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
    private Button btnInformationBoligensIndretning;
    @FXML
    private Button btnInformationFunktionstilstandCopy;
    @FXML
    private Button btnInformationFunktionstilstand;

    @FXML
    private ComboBox<String> comboBoxForventetTilstandHelbredstilstand;
    @FXML
    private ComboBox<String> comboBoxTilstandHelbredstilstand;
    @FXML
    private ComboBox<Integer> comboBoxForventetTilstandFunktionstilstand;
    @FXML
    private ComboBox<Integer> comboBoxTilstandFunktionstilstand;

    @FXML
    private VBox vBoxLeftHelbredstilstand;
    @FXML
    private VBox vBoxRightHelbredstilstand;
    @FXML
    private VBox vBoxLeftFunktionstilstand;
    @FXML
    private VBox vBoxRightFunktionstilstand;
    @FXML
    private VBox vBoxMedicinliste;

    @FXML
    private ScrollPane scrollPaneFunktionstilstand;
    @FXML
    private ScrollPane scrollPaneHelbredstilstand;

    @FXML
    private TabPane tabPaneParent;

    private DashboardController dashboardController;
    private final TooltipBank tooltipBank = new TooltipBank();
    private Borger borger;
    private CitizenModel citizenModel;
    private HelbredstilstandsUnderkategori oldValueOfHelbredstilstandsUnderkategori;
    private FunktionstilstandsUnderkategori oldValueOfFunktionstilstandsUnderkategori;
    private CaseModel caseModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            borger = dashboardController.getSelectedCitizen();
            setGenerelleOplysningerTooltips();
            setFunktionstilstandsTooltips();
            populateTilstande();
            populateHelbredstilstandsCombobox();
            populateFunktionstilstandsCombobox();
            populateGenerelleOplysninger();
        });

        comboBoxTilstandHelbredstilstand.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    if (newValue.equalsIgnoreCase("Ingen aktuelle eller potentielle problemer")) {
                        changeAbilityHelbredstilstandsFields(true);
                    } else changeAbilityHelbredstilstandsFields(false);
                }
            }
        });


        comboBoxTilstandFunktionstilstand.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                if (newValue != null) {
                    if (newValue == 9) {
                        changeAbilityFunktionstilstandsFields(true);
                    } else changeAbilityFunktionstilstandsFields(false);
                }
            }
        });
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public void setCitizenModel(CitizenModel citizenModel) {
        this.citizenModel = citizenModel;
    }

    public void setCaseModel(CaseModel caseModel) {
        this.caseModel = caseModel;
    }

    public void generelleOplysningerHandleSaveAndExitBtn(MouseEvent mouseEvent) {
        updateHelbredstilstandsUnderkategori();
        updateFunktionstilstandsUnderkategori();
        updateBorger(borger);
        closeStage();
    }

    public void generelleOplysningerHandleSaveAndNextBtn(MouseEvent mouseEvent) throws IOException {
        if (dashboardController.getSelectedCase() != null) {
            updateHelbredstilstandsUnderkategori();
            updateFunktionstilstandsUnderkategori();
            updateBorger(borger);
            goToNextScene();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal vælge en sag først.", ButtonType.OK);
            alert.show();
        }
    }

    public void helbredstilstandHandleSaveAndExitBtn(MouseEvent mouseEvent) {
        updateHelbredstilstandsUnderkategori();
        updateFunktionstilstandsUnderkategori();
        updateBorger(borger);
        closeStage();
    }

    public void helbredstilstandHandleSaveAndNextBtn(MouseEvent mouseEvent) throws IOException {
        if (dashboardController.getSelectedCase() != null) {
            updateHelbredstilstandsUnderkategori();
            updateFunktionstilstandsUnderkategori();
            updateBorger(borger);
            goToNextScene();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal vælge en sag først.", ButtonType.OK);
            alert.show();
        }
    }

    public void funktionstilstandHandleSaveAndExitBtn(MouseEvent mouseEvent) {
        updateHelbredstilstandsUnderkategori();
        updateFunktionstilstandsUnderkategori();
        updateBorger(borger);
        closeStage();
    }

    public void funktionstilstandHandleSaveAndNextBtn(MouseEvent mouseEvent) throws IOException {
        if (dashboardController.getSelectedCase() != null) {
            updateHelbredstilstandsUnderkategori();
            updateFunktionstilstandsUnderkategori();
            updateBorger(borger);
            goToNextScene();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal vælge en sag først.", ButtonType.OK);
            alert.show();
        }
    }

    public void medicinlisteHandleSaveAndExitBtn(MouseEvent mouseEvent) {
        updateHelbredstilstandsUnderkategori();
        updateFunktionstilstandsUnderkategori();
        extractMedicineList(); //TODO
        updateBorger(borger);
        closeStage();
    }

    public void medicinlisteHandleSaveAndNextBtn(MouseEvent mouseEvent) throws IOException {
        if (dashboardController.getSelectedCase() != null) {
            updateHelbredstilstandsUnderkategori();
            updateFunktionstilstandsUnderkategori();
            updateBorger(borger);
            goToNextScene();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal vælge en sag først.", ButtonType.OK);
            alert.show();
        }
    }

    public void handleAddTxtFieldMedicineList(ActionEvent actionEvent) {
        vBoxMedicinliste.getChildren().add(new TextField());
        updateHelbredstilstandsUnderkategori();
        updateFunktionstilstandsUnderkategori();
    }


    public void openFOverviewHandle(ActionEvent actionEvent) throws IOException {
        ISceneLoader<AlleRelevanteOplysningerViewController> funktionstilstandOverviewScene = new FunktionsTilstandOverviewScene();
        funktionstilstandOverviewScene.loadNewScene(new Stage());
        AlleRelevanteOplysningerViewController alleRelevanteOplysningerViewController = funktionstilstandOverviewScene.getController();
        alleRelevanteOplysningerViewController.setFunktionstilstand(borger.getFunktionstilstand());
    }

    public void openHOverviewHandle(ActionEvent actionEvent) throws IOException {
        ISceneLoader<AlleRelevanteHelbredstilstandeViewController> helbredstilstandOverviewScene = new HelbredsTilstandOverviewScene();
        helbredstilstandOverviewScene.loadNewScene(new Stage());
        AlleRelevanteHelbredstilstandeViewController alleRelevanteHelbredstilstandeViewController = helbredstilstandOverviewScene.getController();
        alleRelevanteHelbredstilstandeViewController.setHelbredstilstand(borger.getHelbredstilstand());
    }


    private void updateBorger(Borger borger) {
        //Update generelle oplysninger on citizen object
        borger.setMestring(txtAreaMestring.getText());
        borger.setMotivation(txtAreaMotivaton.getText());
        borger.setRessourcer(txtAreaRessourcer.getText());
        borger.setRoller(txtAreaRoller.getText());
        borger.setVaner(txtAreaVaner.getText());
        borger.setUddannelse(txtAreaUddOgJob.getText());
        borger.setLivshistorie(txtAreaLivshistorie.getText());
        borger.setNetvaerk(txtAreaNetvaerk.getText());
        borger.setHelbredsoplysninger(txtAreaHelbredsoplysninger.getText());
        borger.setHjaelpemidler(txtAreaHjaelpemidler.getText());
        borger.setBoligensIndretning(txtAreaBoligensIndretning.getText());

        citizenModel.updateSagsoplysninger(borger);
    }

    private void goToNextScene() throws IOException {
        ISceneLoader<BestillingsViewController> bestillingsScene = new BestillingsScene();
        bestillingsScene.loadNewScene((Stage) tabPaneParent.getScene().getWindow());
        BestillingsViewController bestillingsViewController = bestillingsScene.getController();
        bestillingsViewController.setDashboardController(dashboardController);
        bestillingsViewController.setCaseModel(caseModel);
        bestillingsViewController.setCurrentCitizen(borger);
    }

    private void closeStage() {
        Stage stage = (Stage) tabPaneParent.getScene().getWindow();
        stage.close();
    }

    private void changeAbilityHelbredstilstandsFields(boolean able) {
        comboBoxForventetTilstandHelbredstilstand.setDisable(able);
        txtAreaVurderingHelbredstilstand.setDisable(able);
        txtAreaAarsagHelbredstilstand.setDisable(able);
        txtAreaFagligtNotatHelbredstilstand.setDisable(able);
    }

    private void changeAbilityFunktionstilstandsFields(boolean able) {
        comboBoxForventetTilstandFunktionstilstand.setDisable(able);
        txtAreaUdfoerelseFunktionstilstand.setDisable(able);
        txtAreaBetydningFunktionstilstand.setDisable(able);
        txtAreaOenskerOgMålFunktionstilstand.setDisable(able);
        txtAreaVurderingFunktionstilstand.setDisable(able);
        txtAreaAarsagFunktionstilstand.setDisable(able);
        txtAreaFagligtNotatFunktionstilstand.setDisable(able);
        txtOpfoelgningFunktionstilstand.setDisable(able);
    }

    private void populateGenerelleOplysninger() {
        txtAreaMestring.setText(borger.getMestringProperty().get());
        txtAreaMotivaton.setText(borger.getMotivationProperty().get());
        txtAreaRessourcer.setText(borger.getRessourcerProperty().get());
        txtAreaRoller.setText(borger.getRollerProperty().get());
        txtAreaVaner.setText(borger.getVanerProperty().get());
        txtAreaUddOgJob.setText(borger.getUddannelseProperty().get());
        txtAreaLivshistorie.setText(borger.getLivshistorieProperty().get());
        txtAreaNetvaerk.setText(borger.getNetvaerkProperty().get());
        txtAreaHelbredsoplysninger.setText(borger.getHelbredsoplysningerProperty().get());
        txtAreaHjaelpemidler.setText(borger.getHjaelpemidlerProperty().get());
        txtAreaBoligensIndretning.setText(borger.getBoligensIndretningProperty().get());
    }

    private void populateHelbredstilstandsCombobox() {
        List<String> tilstande = new ArrayList<>();
        List<String> forventedetilstande = new ArrayList<>();

        tilstande.add("Ingen aktuelle eller potentielle problemer");
        tilstande.add("Potentielt problem");
        tilstande.add("Aktuelt problem");

        forventedetilstande.add("Forsvinder");
        forventedetilstande.add("Mindskes");
        forventedetilstande.add("Forbliver uændret");

        comboBoxTilstandHelbredstilstand.getItems().addAll(tilstande);
        comboBoxForventetTilstandHelbredstilstand.getItems().addAll(forventedetilstande);
    }

    private void populateFunktionstilstandsCombobox() {
        List<Integer> niveauer = new ArrayList<>();
        niveauer.add(0);
        niveauer.add(1);
        niveauer.add(2);
        niveauer.add(3);
        niveauer.add(4);
        niveauer.add(9);

        comboBoxForventetTilstandFunktionstilstand.getItems().addAll(niveauer);
        comboBoxTilstandFunktionstilstand.getItems().addAll(niveauer);
    }

    private void populateTilstande() {
        int insertionCounter = 0;

        Helbredstilstand helbredstilstand = borger.getHelbredstilstand();
        Funktionstilstand funktionstilstand = borger.getFunktionstilstand();

        HashMap<String, List<HelbredstilstandsUnderkategori>> helbredstilstandsKort = helbredstilstand.getHelbredsTilstandsKort();
        HashMap<String, List<FunktionstilstandsUnderkategori>> funktionstilstandsKort = funktionstilstand.getFunktionsTilstandsKort();


        for (String klassifikation : helbredstilstandsKort.keySet()) {
            TableView<HelbredstilstandsUnderkategori> tableView = new TableView();
            tableView.setItems(FXCollections.observableList(helbredstilstandsKort.get(klassifikation)));
            TableColumn<HelbredstilstandsUnderkategori, String> tableColumn = new TableColumn<>();
            tableColumn.setText(klassifikation);
            tableColumn.setCellValueFactory(addSubCategory -> addSubCategory.getValue().getTilstandsklassifikationProperty());
            tableView.setFixedCellSize(25);
            tableView.prefHeightProperty().bind(Bindings.size(tableView.getItems()).multiply(tableView.getFixedCellSize()).add(30));
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            tableView.getColumns().add(tableColumn);
            tableView.setMaxWidth(245.0);
            tableView.getStyleClass().add("Helbredstable-view");

            // populates text areas as well as the comboboxes for a given subcategory
            tableView.setOnMouseClicked(event -> {

                updateHelbredstilstandsUnderkategori();

                oldValueOfHelbredstilstandsUnderkategori = tableView.getSelectionModel().getSelectedItem();
                populateTxtAreasHelbredstilstand(tableView.getSelectionModel().getSelectedItem());
            });


            tableView.setRowFactory(tv -> new TableRow<HelbredstilstandsUnderkategori>() {
                @Override
                protected void updateItem(HelbredstilstandsUnderkategori hsKategori, boolean empty) {
                    super.updateItem(hsKategori, empty);
                    if (!empty && hsKategori != null) {
                        this.styleProperty().bind(Bindings.createStringBinding(() -> {
                            if (hsKategori.getTilstandProperty().get() == null) {
                                return "-fx-background-color: rgba(185, 105, 144, 1);";
                            } else if (hsKategori.getTilstandProperty().get().equalsIgnoreCase("Ingen aktuelle eller potentielle problemer")) {
                                return "-fx-background-color: rgba(119, 161, 131, 1);";
                            } else {
                                //return "-fx-background-color: rgba(198,178,47,1);"; themed yellow
                                return "-fx-background-color: rgb(220, 138, 77);";
                            }
                        }, hsKategori.getTilstandProperty()));
                    } else {
                        setText(null);
                        setGraphic(null);
                        this.styleProperty().unbind();

                        setStyle("");
                    }
                }
            });

            if (insertionCounter % 2 == 0) {
                vBoxLeftHelbredstilstand.getChildren().add(tableView);
            } else {
                vBoxRightHelbredstilstand.getChildren().add(tableView);
            }
            insertionCounter++;
        }
        insertionCounter = 0;

        for (String klassifikation : funktionstilstandsKort.keySet()) {
            TableView<FunktionstilstandsUnderkategori> tableView = new TableView();
            tableView.setItems(FXCollections.observableList(funktionstilstandsKort.get(klassifikation)));
            TableColumn<FunktionstilstandsUnderkategori, String> tableColumn = new TableColumn<>();
            tableColumn.setText(klassifikation);
            tableColumn.setCellValueFactory(addSubCategory -> addSubCategory.getValue().getTilstandsklassifikationProperty());
            tableView.setFixedCellSize(25);
            tableView.prefHeightProperty().bind(Bindings.size(tableView.getItems()).multiply(tableView.getFixedCellSize()).add(30));
            tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            tableView.getColumns().add(tableColumn);
            tableView.setMaxWidth(245.0);
            tableView.getStyleClass().add("Funktionstable-view");

            // populates text areas as well as the comboboxes for a given subcategory
            tableView.setOnMouseClicked(event -> {

                updateFunktionstilstandsUnderkategori();

                oldValueOfFunktionstilstandsUnderkategori = tableView.getSelectionModel().getSelectedItem();
                populateTxtAreasFunktionstilstand(tableView.getSelectionModel().getSelectedItem());
            });

            tableView.setRowFactory(tv -> new TableRow<FunktionstilstandsUnderkategori>() {
                @Override
                protected void updateItem(FunktionstilstandsUnderkategori fsKategori, boolean empty) {
                    super.updateItem(fsKategori, empty);
                    if (!empty && fsKategori != null) {
                        this.styleProperty().bind(Bindings.createStringBinding(() -> {
                            if (fsKategori.getNiveauProperty().get() == -1) {
                                return "-fx-background-color: rgba(185, 105, 144, 1);";
                            } else if (fsKategori.getNiveauProperty().get() == 9) {
                                return "-fx-background-color: rgba(119, 161, 131, 1);";
                            } else {
                                //return "-fx-background-color: rgba(198,178,47,1);"; themed yellow
                                return "-fx-background-color: rgb(220, 138, 77);";
                            }
                        }, fsKategori.getNiveauProperty()));
                    } else {
                        setText(null);
                        setGraphic(null);
                        this.styleProperty().unbind();

                        setStyle("");
                    }
                }
            });

            if (insertionCounter % 2 == 0) {
                vBoxLeftFunktionstilstand.getChildren().add(tableView);
            } else {
                vBoxRightFunktionstilstand.getChildren().add(tableView);
            }
            insertionCounter++;
        }
    }

    private void updateFunktionstilstandsUnderkategori() {
        if (oldValueOfFunktionstilstandsUnderkategori != null) {
            oldValueOfFunktionstilstandsUnderkategori.setNiveau(comboBoxTilstandFunktionstilstand.getSelectionModel().getSelectedItem());

            if (comboBoxForventetTilstandFunktionstilstand.getSelectionModel().getSelectedItem() == null) // in case the user chooses 9 in the first dropdown or the user hasnt opened the assesment.
                oldValueOfFunktionstilstandsUnderkategori.setForventetTilstand(-1); // -1 is the standard value so we dont save this in the database.
            else
                oldValueOfFunktionstilstandsUnderkategori.setForventetTilstand(comboBoxForventetTilstandFunktionstilstand.getSelectionModel().getSelectedItem());

            oldValueOfFunktionstilstandsUnderkategori.setUdførelse(txtAreaUdfoerelseFunktionstilstand.getText());
            oldValueOfFunktionstilstandsUnderkategori.setBetydning(txtAreaBetydningFunktionstilstand.getText());
            oldValueOfFunktionstilstandsUnderkategori.setOenskerOgMaal(txtAreaOenskerOgMålFunktionstilstand.getText());
            oldValueOfFunktionstilstandsUnderkategori.setVurdering(txtAreaVurderingFunktionstilstand.getText());
            oldValueOfFunktionstilstandsUnderkategori.setAarsag(txtAreaAarsagFunktionstilstand.getText());
            oldValueOfFunktionstilstandsUnderkategori.setFagligNotat(txtAreaFagligtNotatFunktionstilstand.getText());
            oldValueOfFunktionstilstandsUnderkategori.setOpfølgning(txtOpfoelgningFunktionstilstand.getText());

            Observation observation = new Observation();
            observation.setDescription(txtAreaObservationFunktionstilstand1.getText());

            oldValueOfFunktionstilstandsUnderkategori.setObservation(observation);
        }
    }

    private void updateHelbredstilstandsUnderkategori() {
        if (oldValueOfHelbredstilstandsUnderkategori != null) {
            oldValueOfHelbredstilstandsUnderkategori.setVurdering(txtAreaVurderingHelbredstilstand.getText());
            oldValueOfHelbredstilstandsUnderkategori.setAarsag(txtAreaAarsagHelbredstilstand.getText());
            oldValueOfHelbredstilstandsUnderkategori.setFagligNotat(txtAreaFagligtNotatHelbredstilstand.getText());
            oldValueOfHelbredstilstandsUnderkategori.setTilstand(comboBoxTilstandHelbredstilstand.getSelectionModel().getSelectedItem());
            oldValueOfHelbredstilstandsUnderkategori.setForventetTilstand(comboBoxForventetTilstandHelbredstilstand.getSelectionModel().getSelectedItem());

            Observation observation = new Observation();
            observation.setDescription(txtAreaObservationHelbredstilstand1.getText());

            oldValueOfHelbredstilstandsUnderkategori.setObservation(observation);
        }
    }

    private void populateTxtAreasHelbredstilstand(HelbredstilstandsUnderkategori newValue) {
        comboBoxTilstandHelbredstilstand.getSelectionModel().select(newValue.getTilstandProperty().get());
        comboBoxForventetTilstandHelbredstilstand.getSelectionModel().select(newValue.getForventetTilstandProperty().get());
        txtAreaVurderingHelbredstilstand.setText(newValue.getVurderingProperty().get());
        txtAreaAarsagHelbredstilstand.setText(newValue.getAarsagProperty().get());
        txtAreaFagligtNotatHelbredstilstand.setText(newValue.getFagligNotatProperty().get());
        txtAreaObservationHelbredstilstand1.setText(newValue.getObservation().getDescriptionProperty().get());
        lblOverkategoriHelbredstilstand.setText(newValue.getOverkategoriProperty().get());
        lblTilstandsklassifikationHelbredstilstand.setText(newValue.getTilstandsklassifikationProperty().get());
    }

    private void populateTxtAreasFunktionstilstand(FunktionstilstandsUnderkategori newValue) {
        comboBoxTilstandFunktionstilstand.getSelectionModel().select(Integer.valueOf(newValue.getNiveauProperty().get()));
        comboBoxForventetTilstandFunktionstilstand.getSelectionModel().select(Integer.valueOf(newValue.getForventetTilstandProperty().get()));
        txtAreaUdfoerelseFunktionstilstand.setText(newValue.getUdførelseProperty().get());
        txtAreaBetydningFunktionstilstand.setText(newValue.getBetydningProperty().get());
        txtAreaOenskerOgMålFunktionstilstand.setText(newValue.getOenskerOgMaalProperty().get());
        txtAreaVurderingFunktionstilstand.setText(newValue.getVurderingProperty().get());
        txtAreaAarsagFunktionstilstand.setText(newValue.getAarsagProperty().get());
        txtAreaFagligtNotatFunktionstilstand.setText(newValue.getFagligNotatProperty().get());
        txtAreaObservationFunktionstilstand1.setText(newValue.getObservation().getDescriptionProperty().get());
        txtOpfoelgningFunktionstilstand.setText(newValue.getOpfølgningProperty().get());
        lblOverkategoriFunktionstilstand.setText(newValue.getOverKategoriProperty().get());
        lblTilstandsklassifikationFunktionstilstand.setText(newValue.getTilstandsklassifikationProperty().get());
    }


    private void setGenerelleOplysningerTooltips() {
        // Setting up tooltips for the information buttons in the view that guides the student
        btnInformationMestring.setTooltip(tooltipBank.getMestring());
        btnInformationMotivation.setTooltip(tooltipBank.getMotivation());
        btnInformationRessoucer.setTooltip(tooltipBank.getRessourcer());
        btnInformationRoller.setTooltip(tooltipBank.getRoller());
        btnInformationVaner.setTooltip(tooltipBank.getVaner());
        btnInformationUddOgJob.setTooltip(tooltipBank.getUddannelseOgJob());
        btnInformationLivshistorie.setTooltip(tooltipBank.getLivshistorie());
        btnInformationNetvaerk.setTooltip(tooltipBank.getNetvaerk());
        btnInformationHelbredsoplysninger.setTooltip(tooltipBank.getHelbredsoplysninger());
        btnInformationHjaelpemidler.setTooltip(tooltipBank.getHjaelpemidler());
        btnInformationBoligensIndretning.setTooltip(tooltipBank.getBoligensIndretning());
    }

    private void setFunktionstilstandsTooltips() {
        Image image = new Image("gui/resources/images/FunktionstilstandsNiveau.PNG");
        ImageView imageView = new ImageView(image);
        Tooltip tooltip = new Tooltip();
        tooltip.setGraphic(imageView);
        tooltip.setShowDuration(Duration.INDEFINITE);
        tooltip.setShowDelay(Duration.millis(0));
        tooltip.setHideDelay(Duration.seconds(2));
        btnInformationFunktionstilstand.setTooltip(tooltip);
        btnInformationFunktionstilstandCopy.setTooltip(tooltip);
    }

    /*
    Extracts a list of strings from the textfields in the container where the medicine list is shown
     */
    private List<String> extractMedicineList() {
        List<Node> nodeList = vBoxMedicinliste.getChildren();
        List<String> medicineList = new ArrayList<>();

        for (Node node : nodeList) {
            TextField txtField = (TextField) node;
            if (!txtField.getText().isEmpty() || !txtField.getText().isBlank())
                medicineList.add(txtField.getText());
        }
        return medicineList;
    }


    private class TooltipBank {
        final private double TOOLTIP_WIDTH = 250.0;

        final private String mestring = "Definition: Borgerens bevidste eller ubevidste håndtering af livet/sygdommen – både udfordringer og muligheder." +
                "\n \nDokumentationspraksis: Her dokumenteres, hvordan borgeren positivt eller negativt mestrer den modgang vedkommende møder.";

        final private String motivation = "Definition: Drivkraften bag at borgeren handler på en bestemt måde eller går i gang med/opretholder en opgave/indsats." +
                "\n \nDokumentationspraksis: Her dokumenteres borgerens ønsker for sit liv (overordnet mål), og hvad der motiverer borgeren.";

        final private String ressourcer = "Definition: De fysiske eller mentale kræfter, som borgerenen i et vist omfang har til rådighed og kan udnytte. Fysiske kræfter " +
                "kan fx være i form af fysisk sundhed og styrke. Mentale kræfter kan fx være i til situationer og andre mennesker på. " +
                "\n \nDokumentationspraksis: Her dokumenteres de ressourcer, borgeren har i forhold til at løse dagligdagens opgaver. Det kan være både fysiske og mentale funktioner.";

        final private String roller = "Definition: De roller som er særligt vigtige for borgeren i forhold til familie, arbejde og samfund." +
                "\n \nDokumentationspraksis: Her dokumenteres de roller, borgeren angiver at have. Det kan fx være rolle som ægtefælle, " +
                "bedsteforælder eller aktiv i beboerforening.";

        final private String vaner = "Definition: Regelmæssig adfærd som borgeren har tillært gennem stadig gentagelse og udførelse helt eller delvist ubevidst. " +
                "Vaner er fx døgnrytmen, måden at blive tiltalt på, kontakt med medmennesker og relationer, måde at anskue verden på" +
                "\n \nDokumentationspraksis: Her dokumenteres borgerens vaner, som er en naturlig del af hverdagen, og som borgeren plejer at gøre. Det kan både" +
                "være vaner, som borgeren gør af fysiske og psykiske årsager.";

        final private String uddannelseOgJob = "Definition: Nuværende eller tidligere uddannelses\u0002og/eller erhvervsmæssig baggrund." +
                "Fx folkeskole, erhvervsuddannelse og videregående uddannelse." +
                "\n \nDokumentationspraksis: Her dokumenteres borgerens oplysninger om uddannelse og erhverv. Fx hvilke job borgeren har haft, hvilken " +
                "betydning arbejdslivet har haft for borgeren, og har borgeren evt. stadig tilknytning til arbejdspladsen og kolleger.";

        final private String livshistorie = "Definition: En beskrivelse af borgerens oplevelse af væsentlige begivenheder, interesser og gøremål igennem livet." +
                "\n \nDokumentationspraksis: Her dokumenteres borgerens fortælling om sit liv. \n" +
                "Her dokumenteres borgerens ønsker for den sidste tid.";

        final private String netvaerk = "Definition: Personer som er tæt på borgeren, og som giver praktisk og/eller følelsesmæssigt støtte og omsorg " +
                "overfor borgeren. Netværk kan være offentligt eller privat. Et offentligt netværk består af personlige hjælpere, sundhedspersonale og andre " +
                "professionelle primært omsorgsgivere. Privat netværk er familie, slægtning, venner og bekendtskaber." +
                "\n \nDokumentationspraksis: Her dokumenteres borgerens netværk i bredere forstand.";

        final private String helbredsoplysninger = "Definition: \nHelbredsoplysninger: Aktuelle eller tidligere sygdomme og handicap der har betydning for borgerens situation. \n" +
                "Sundhedsfaglige kontakter: Medarbejder eller enheder indenfor sundhedsvæsenet borgeren er tilknyttet, fx øjenlæge, tandlæge, fodterapeut eller afdeling/ambulatorium." +
                "\n \nDokumentationspraksis: Her kan borgerens sygdomme og handicap dokumenteres for at give et samlet overblik. Hvis oplysningen kommer fra borgeren " +
                "eller pårørende, skal dette fremgå. Her kan behandlingsansvarlig læge/ambulatorie ift. en konkret sygdom dokumenteres, hvis dette ikke er angivet andre steder.";

        final private String hjaelpemidler = "Definition: Udstyr, produkter og teknologi som anvendes af borgeren i daglige aktiviteter, inkl. sådanne som er tilpasset " +
                "eller særligt fremstillet til, implanteret i, placeret på eller nær personen, som anvender dem. (Inkl. almindelige genstande og hjælpemidler og teknologi til personlig anvendelse). " +
                "\n \nDokumentationspraksis: Her dokumenteres de hjælpemidler borger selv har anskaffet. Bevilligede hjælpemidler kan også dokumenteres eller vises her. ";

        final private String boligensIndretning = "Definition: En beskrivelse af boligens fysiske rammer og omgivelser, der har betydning for borgerens hverdagsliv og funktionsevne." +
                "\n \nDokumentationspraksis: Her dokumenteres både det der hæmmer og fremmer borgerens funktionsevne i hverdagen. Kan suppleres med praktiske oplysninger" +
                "fx om der er elevator, dørtrin eller trapper.";

        public Tooltip getMestring() {
            Tooltip tooltip = new Tooltip(this.mestring);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }

        public Tooltip getMotivation() {
            Tooltip tooltip = new Tooltip(this.motivation);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }

        public Tooltip getRessourcer() {
            Tooltip tooltip = new Tooltip(this.ressourcer);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }

        public Tooltip getRoller() {
            Tooltip tooltip = new Tooltip(this.roller);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }

        public Tooltip getVaner() {
            Tooltip tooltip = new Tooltip(this.vaner);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }

        public Tooltip getUddannelseOgJob() {
            Tooltip tooltip = new Tooltip(this.uddannelseOgJob);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }

        public Tooltip getLivshistorie() {
            Tooltip tooltip = new Tooltip(this.livshistorie);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }

        public Tooltip getNetvaerk() {
            Tooltip tooltip = new Tooltip(this.netvaerk);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }

        public Tooltip getHelbredsoplysninger() {
            Tooltip tooltip = new Tooltip(this.helbredsoplysninger);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }

        public Tooltip getHjaelpemidler() {
            Tooltip tooltip = new Tooltip(this.hjaelpemidler);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }

        public Tooltip getBoligensIndretning() {
            Tooltip tooltip = new Tooltip(this.boligensIndretning);
            tooltip.setShowDuration(Duration.INDEFINITE);
            tooltip.setShowDelay(Duration.millis(0));
            tooltip.setWrapText(true);
            tooltip.setPrefWidth(TOOLTIP_WIDTH);
            return tooltip;
        }
    }
}
