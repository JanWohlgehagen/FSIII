package gui.controller.circle;

import be.*;
import gui.controller.DashboardController;
import gui.controller.RelevantFunctionAssessmentViewController;
import gui.controller.RelevantHealthAssessmentViewController;
import gui.model.CaseModel;
import gui.model.CitizenModel;
import gui.util.BestillingsScene;
import gui.util.RelevantFunctionAssessmentScene;
import gui.util.RelevantHealthAssessmentScene;
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

public class AssessmentInformationController implements Initializable {

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
    private TextArea txtAreaOenskerOgM??lFunktionstilstand;
    @FXML
    private TextArea txtAreaVurderingFunktionstilstand;
    @FXML
    private TextArea txtAreaAarsagFunktionstilstand;
    @FXML
    private TextArea txtAreaFagligtNotatFunktionstilstand;
    @FXML
    private TextField txtOpfoelgningFunktionstilstand;
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
    private TabPane tabPaneParent;

    private DashboardController dashboardController;
    private final TooltipBank tooltipBank = new TooltipBank();
    private Citizen citizen;
    private CitizenModel citizenModel;
    private HelbredstilstandsUnderkategori oldValueOfHelbredstilstandsUnderkategori;
    private FunktionstilstandsUnderkategori oldValueOfFunktionstilstandsUnderkategori;
    private CaseModel caseModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(() -> {
            citizen = dashboardController.getSelectedCitizen();
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

    public void handleSaveAndExitBtn(MouseEvent mouseEvent) {
        updateHelbredstilstandsUnderkategori();
        updateFunktionstilstandsUnderkategori();
        updateBorger(citizen);
        closeStage();
    }

    public void handleSaveAndNextBtn(MouseEvent mouseEvent) throws IOException {
        if (dashboardController.getSelectedCase() != null) {
            updateHelbredstilstandsUnderkategori();
            updateFunktionstilstandsUnderkategori();
            updateBorger(citizen);
            goToNextScene();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Du skal v??lge en sag f??rst.", ButtonType.OK);
            alert.show();
        }
    }

    public void openFOverviewHandle(ActionEvent actionEvent) throws IOException {
        ISceneLoader<RelevantFunctionAssessmentViewController> funktionstilstandOverviewScene = new RelevantFunctionAssessmentScene();
        funktionstilstandOverviewScene.loadNewScene(new Stage());
        RelevantFunctionAssessmentViewController relevantFunctionAssessmentViewController = funktionstilstandOverviewScene.getController();
        relevantFunctionAssessmentViewController.setFunctionAssessment(citizen.getFunktionstilstand());
    }

    public void openHOverviewHandle(ActionEvent actionEvent) throws IOException {
        ISceneLoader<RelevantHealthAssessmentViewController> helbredstilstandOverviewScene = new RelevantHealthAssessmentScene();
        helbredstilstandOverviewScene.loadNewScene(new Stage());
        RelevantHealthAssessmentViewController relevantHealthAssessmentViewController = helbredstilstandOverviewScene.getController();
        relevantHealthAssessmentViewController.setHelbredstilstand(citizen.getHelbredstilstand());
    }


    private void updateBorger(Citizen citizen) {
        //Update generelle oplysninger on citizen object
        citizen.getGeneralinformation().setMastery(txtAreaMestring.getText());
        citizen.getGeneralinformation().setMotivation(txtAreaMotivaton.getText());
        citizen.getGeneralinformation().setResources(txtAreaRessourcer.getText());
        citizen.getGeneralinformation().setRoles(txtAreaRoller.getText());
        citizen.getGeneralinformation().setHabits(txtAreaVaner.getText());
        citizen.getGeneralinformation().setEducation(txtAreaUddOgJob.getText());
        citizen.getGeneralinformation().setLifeStory(txtAreaLivshistorie.getText());
        citizen.getGeneralinformation().setNetwork(txtAreaNetvaerk.getText());
        citizen.getGeneralinformation().setHealthInformation(txtAreaHelbredsoplysninger.getText());
        citizen.getGeneralinformation().setAssistiveDevices(txtAreaHjaelpemidler.getText());
        citizen.getGeneralinformation().setHomeDecor(txtAreaBoligensIndretning.getText());

        citizenModel.updateSagsoplysninger(citizen);
    }

    private void goToNextScene() throws IOException {
        ISceneLoader<OrderViewController> bestillingsScene = new BestillingsScene();
        bestillingsScene.loadNewScene((Stage) tabPaneParent.getScene().getWindow());
        OrderViewController orderViewController = bestillingsScene.getController();
        orderViewController.setDashboardController(dashboardController);
        orderViewController.setCaseModel(caseModel);
        orderViewController.setCurrentCitizen(citizen);
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
        txtAreaOenskerOgM??lFunktionstilstand.setDisable(able);
        txtAreaVurderingFunktionstilstand.setDisable(able);
        txtAreaAarsagFunktionstilstand.setDisable(able);
        txtAreaFagligtNotatFunktionstilstand.setDisable(able);
        txtOpfoelgningFunktionstilstand.setDisable(able);
    }

    private void populateGenerelleOplysninger() {
        txtAreaMestring.setText(citizen.getGeneralinformation().getMasteryProperty().get());
        txtAreaMotivaton.setText(citizen.getGeneralinformation().getMotivationProperty().get());
        txtAreaRessourcer.setText(citizen.getGeneralinformation().getResourcesProperty().get());
        txtAreaRoller.setText(citizen.getGeneralinformation().getRolesProperty().get());
        txtAreaVaner.setText(citizen.getGeneralinformation().getHabitsProperty().get());
        txtAreaUddOgJob.setText(citizen.getGeneralinformation().getEducationProperty().get());
        txtAreaLivshistorie.setText(citizen.getGeneralinformation().getLifeStoryProperty().get());
        txtAreaNetvaerk.setText(citizen.getGeneralinformation().getNetworkProperty().get());
        txtAreaHelbredsoplysninger.setText(citizen.getGeneralinformation().getHealthInformationProperty().get());
        txtAreaHjaelpemidler.setText(citizen.getGeneralinformation().getAssistiveDevicesProperty().get());
        txtAreaBoligensIndretning.setText(citizen.getGeneralinformation().getHomeDecorProperty().get());
    }

    private void populateHelbredstilstandsCombobox() {
        List<String> tilstande = new ArrayList<>();
        List<String> forventedetilstande = new ArrayList<>();

        tilstande.add("Ingen aktuelle eller potentielle problemer");
        tilstande.add("Potentielt problem");
        tilstande.add("Aktuelt problem");

        forventedetilstande.add("Forsvinder");
        forventedetilstande.add("Mindskes");
        forventedetilstande.add("Forbliver u??ndret");

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

        HealthAssessment healthAssessment = citizen.getHelbredstilstand();
        FunctionAssessment functionAssessment = citizen.getFunktionstilstand();

        HashMap<String, List<HelbredstilstandsUnderkategori>> helbredstilstandsKort = healthAssessment.getHelbredsTilstandsKort();
        HashMap<String, List<FunktionstilstandsUnderkategori>> funktionstilstandsKort = functionAssessment.getFunktionsTilstandsKort();


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
                                return "-fx-background-color: rgb(236, 99, 99);";
                            } else if (hsKategori.getTilstandProperty().get().equalsIgnoreCase("Ingen aktuelle eller potentielle problemer")) {
                                return "-fx-background-color: rgba(119, 161, 131, 1);";
                            } else {
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
                                return "-fx-background-color: rgb(236, 99, 99);";
                            } else if (fsKategori.getNiveauProperty().get() == 9) {
                                return "-fx-background-color: rgba(119, 161, 131, 1);";
                            } else {
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

            oldValueOfFunktionstilstandsUnderkategori.setUdf??relse(txtAreaUdfoerelseFunktionstilstand.getText());
            oldValueOfFunktionstilstandsUnderkategori.setBetydning(txtAreaBetydningFunktionstilstand.getText());
            oldValueOfFunktionstilstandsUnderkategori.setOenskerOgMaal(txtAreaOenskerOgM??lFunktionstilstand.getText());
            oldValueOfFunktionstilstandsUnderkategori.setVurdering(txtAreaVurderingFunktionstilstand.getText());
            oldValueOfFunktionstilstandsUnderkategori.setAarsag(txtAreaAarsagFunktionstilstand.getText());
            oldValueOfFunktionstilstandsUnderkategori.setFagligNotat(txtAreaFagligtNotatFunktionstilstand.getText());
            oldValueOfFunktionstilstandsUnderkategori.setOpf??lgning(txtOpfoelgningFunktionstilstand.getText());

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
        txtAreaUdfoerelseFunktionstilstand.setText(newValue.getUdf??relseProperty().get());
        txtAreaBetydningFunktionstilstand.setText(newValue.getBetydningProperty().get());
        txtAreaOenskerOgM??lFunktionstilstand.setText(newValue.getOenskerOgMaalProperty().get());
        txtAreaVurderingFunktionstilstand.setText(newValue.getVurderingProperty().get());
        txtAreaAarsagFunktionstilstand.setText(newValue.getAarsagProperty().get());
        txtAreaFagligtNotatFunktionstilstand.setText(newValue.getFagligNotatProperty().get());
        txtAreaObservationFunktionstilstand1.setText(newValue.getObservation().getDescriptionProperty().get());
        txtOpfoelgningFunktionstilstand.setText(newValue.getOpf??lgningProperty().get());
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

    private class TooltipBank {
        final private double TOOLTIP_WIDTH = 250.0;

        final private String mestring = "Definition: Borgerens bevidste eller ubevidste h??ndtering af livet/sygdommen ??? b??de udfordringer og muligheder." +
                "\n \nDokumentationspraksis: Her dokumenteres, hvordan borgeren positivt eller negativt mestrer den modgang vedkommende m??der.";

        final private String motivation = "Definition: Drivkraften bag at borgeren handler p?? en bestemt m??de eller g??r i gang med/opretholder en opgave/indsats." +
                "\n \nDokumentationspraksis: Her dokumenteres borgerens ??nsker for sit liv (overordnet m??l), og hvad der motiverer borgeren.";

        final private String ressourcer = "Definition: De fysiske eller mentale kr??fter, som borgerenen i et vist omfang har til r??dighed og kan udnytte. Fysiske kr??fter " +
                "kan fx v??re i form af fysisk sundhed og styrke. Mentale kr??fter kan fx v??re i til situationer og andre mennesker p??. " +
                "\n \nDokumentationspraksis: Her dokumenteres de ressourcer, borgeren har i forhold til at l??se dagligdagens opgaver. Det kan v??re b??de fysiske og mentale funktioner.";

        final private String roller = "Definition: De roller som er s??rligt vigtige for borgeren i forhold til familie, arbejde og samfund." +
                "\n \nDokumentationspraksis: Her dokumenteres de roller, borgeren angiver at have. Det kan fx v??re rolle som ??gtef??lle, " +
                "bedstefor??lder eller aktiv i beboerforening.";

        final private String vaner = "Definition: Regelm??ssig adf??rd som borgeren har till??rt gennem stadig gentagelse og udf??relse helt eller delvist ubevidst. " +
                "Vaner er fx d??gnrytmen, m??den at blive tiltalt p??, kontakt med medmennesker og relationer, m??de at anskue verden p??" +
                "\n \nDokumentationspraksis: Her dokumenteres borgerens vaner, som er en naturlig del af hverdagen, og som borgeren plejer at g??re. Det kan b??de" +
                "v??re vaner, som borgeren g??r af fysiske og psykiske ??rsager.";

        final private String uddannelseOgJob = "Definition: Nuv??rende eller tidligere uddannelses\u0002og/eller erhvervsm??ssig baggrund." +
                "Fx folkeskole, erhvervsuddannelse og videreg??ende uddannelse." +
                "\n \nDokumentationspraksis: Her dokumenteres borgerens oplysninger om uddannelse og erhverv. Fx hvilke job borgeren har haft, hvilken " +
                "betydning arbejdslivet har haft for borgeren, og har borgeren evt. stadig tilknytning til arbejdspladsen og kolleger.";

        final private String livshistorie = "Definition: En beskrivelse af borgerens oplevelse af v??sentlige begivenheder, interesser og g??rem??l igennem livet." +
                "\n \nDokumentationspraksis: Her dokumenteres borgerens fort??lling om sit liv. \n" +
                "Her dokumenteres borgerens ??nsker for den sidste tid.";

        final private String netvaerk = "Definition: Personer som er t??t p?? borgeren, og som giver praktisk og/eller f??lelsesm??ssigt st??tte og omsorg " +
                "overfor borgeren. Netv??rk kan v??re offentligt eller privat. Et offentligt netv??rk best??r af personlige hj??lpere, sundhedspersonale og andre " +
                "professionelle prim??rt omsorgsgivere. Privat netv??rk er familie, sl??gtning, venner og bekendtskaber." +
                "\n \nDokumentationspraksis: Her dokumenteres borgerens netv??rk i bredere forstand.";

        final private String helbredsoplysninger = "Definition: \nHelbredsoplysninger: Aktuelle eller tidligere sygdomme og handicap der har betydning for borgerens situation. \n" +
                "Sundhedsfaglige kontakter: Medarbejder eller enheder indenfor sundhedsv??senet borgeren er tilknyttet, fx ??jenl??ge, tandl??ge, fodterapeut eller afdeling/ambulatorium." +
                "\n \nDokumentationspraksis: Her kan borgerens sygdomme og handicap dokumenteres for at give et samlet overblik. Hvis oplysningen kommer fra borgeren " +
                "eller p??r??rende, skal dette fremg??. Her kan behandlingsansvarlig l??ge/ambulatorie ift. en konkret sygdom dokumenteres, hvis dette ikke er angivet andre steder.";

        final private String hjaelpemidler = "Definition: Udstyr, produkter og teknologi som anvendes af borgeren i daglige aktiviteter, inkl. s??danne som er tilpasset " +
                "eller s??rligt fremstillet til, implanteret i, placeret p?? eller n??r personen, som anvender dem. (Inkl. almindelige genstande og hj??lpemidler og teknologi til personlig anvendelse). " +
                "\n \nDokumentationspraksis: Her dokumenteres de hj??lpemidler borger selv har anskaffet. Bevilligede hj??lpemidler kan ogs?? dokumenteres eller vises her. ";

        final private String boligensIndretning = "Definition: En beskrivelse af boligens fysiske rammer og omgivelser, der har betydning for borgerens hverdagsliv og funktionsevne." +
                "\n \nDokumentationspraksis: Her dokumenteres b??de det der h??mmer og fremmer borgerens funktionsevne i hverdagen. Kan suppleres med praktiske oplysninger" +
                "fx om der er elevator, d??rtrin eller trapper.";

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
