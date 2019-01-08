package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import sample.Main;
import sample.model.Scenario;
import sample.viewInit.AdditionalViewInitializer;
import sample.viewInit.ChangeScenarioViewInitializer;
import sample.viewInit.ChooseScenarioViewInitializer;
import sample.viewInit.ScenarioViewInitializer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static sample.Main.init;

public class ChooseScenarioController extends AbstractController{

    @FXML
    private ListView<Scenario> listView;

    @FXML
    private Button runScenarioButton;

    @FXML
    private Button editScenarioButton;

    @FXML
    private Button newScenarioButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button additionalFeaturesButton;

    @FXML
    private GridPane gridPane;

    public ChooseScenarioController(Main mainApp) {
        super(mainApp);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainApp.loadAllScenarios();
        List<Scenario> allScenarios = mainApp.getAppState().getAllScenarios();
        if(allScenarios != null){
            ObservableList listViewData = FXCollections.observableArrayList(allScenarios);
            listView.setItems(listViewData);
            listView.setCellFactory(new Callback<ListView<Scenario>, ListCell<Scenario>>() {
                @Override
                public ListCell<Scenario> call(ListView<Scenario> param) {
                   ListCell<Scenario> cell = new ListCell<Scenario>(){
                        @Override
                        protected void updateItem(Scenario scenario, boolean empty) {
                            super.updateItem(scenario, empty);

                            if (!empty) {
                                Text text = new Text(scenario.getName());
                                text.wrappingWidthProperty().bind(listView.widthProperty().subtract(10));
                                setGraphic(text);
                            }

                            if(scenario==null || empty){

                            }else {
                                this.setText(scenario.getName());

                            }
                        }
                    };
                    return cell;
                }
            });
        }


        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                runScenarioButton.setDisable(false);
                editScenarioButton.setDisable(false);
                deleteButton.setDisable(false);

            }else{
                runScenarioButton.setDisable(true);
                editScenarioButton.setDisable(true);
                deleteButton.setDisable(true);
            }
        });

        deleteButton.setOnAction(event -> {
            Scenario scenario = listView.getSelectionModel().getSelectedItem();
            File file1 = new File("Scenariusze"+ File.separator+scenario.getName() + ".format");
            File file2 = new File("OpisyScenariuszy" + File.separator + scenario.getPathToFile());
            file1.delete();
            file2.delete();
            try {
                init(new ChooseScenarioViewInitializer(mainApp).initView());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        runScenarioButton.setOnAction(event -> {
            mainApp.getAppState().setScenarioToShow(listView.getSelectionModel().getSelectedItem());

            try {
                init(new ScenarioViewInitializer(mainApp).initView());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        editScenarioButton.setOnAction(event -> {
            mainApp.getAppState().setScenarioToShow(listView.getSelectionModel().getSelectedItem());

            try {
                init(new ChangeScenarioViewInitializer(mainApp).initView());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        newScenarioButton.setOnAction(event -> {

            mainApp.getAppState().setScenarioToShow(null);

            try {
                init(new ChangeScenarioViewInitializer(mainApp).initView());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        additionalFeaturesButton.setOnAction(event -> {
            try {
                init(new AdditionalViewInitializer(mainApp).initView());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


}
