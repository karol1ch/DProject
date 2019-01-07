package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.controller.*;
import sample.model.AppState;
import sample.model.Scenario;
import sample.utils.ScenariosReaderWriter;


import java.io.*;
import java.net.URL;
import java.util.List;


public class Main extends Application {
    private static final String CHOOSE_SCENARIO_VIEW = "/chooseScenario.fxml";
    private static final String SCENARIO_VIEW =  "/scenario.fxml";
    private static final String CHANGE_SCENARIO_VIEW = "/editScenario.fxml";

    private AppState appState = AppState.INSTANCE;
    private BorderPane mainRoot;

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL resource = getClass().getResource( "/main.fxml");
        FXMLLoader loader = new FXMLLoader(resource);
        loader.setController(new MainController(this));
        mainRoot = loader.load();
        primaryStage.setTitle("Centrum Symulacji");
        Scene scene = new Scene(mainRoot);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();

      initChooseScenarioView();
    }

    public void initScenarioView() throws IOException { //factory method + templateMethod
        initView(SCENARIO_VIEW, new ScenarioController(this));
    }

    public void initChooseScenarioView() throws IOException {
        initView(CHOOSE_SCENARIO_VIEW, new ChooseScenarioController(this));
    }

    public void initChangeScenarioView() throws IOException {
        initView(CHANGE_SCENARIO_VIEW, new EditScenarioController(this));
    }

    private void initView( String path, AbstractController controller) throws IOException {  //1 - Strategy
        URL resource = getClass().getResource(path);
        FXMLLoader loader = new FXMLLoader(resource);
        loader.setController(controller);
        Parent view = loader.load();
        mainRoot.setCenter(view);
    }


    public static void main(String[] args) {
        launch(args);
    }

    public AppState getAppState() {
        return appState;
    }

    public void loadAllScenarios() {
        ScenariosReaderWriter scenariosReaderWriter = new ScenariosReaderWriter();
        try {
            List<Scenario> scenarios = scenariosReaderWriter.loadScenarios("Scenariusze");
            if(scenarios.size() > 0) {
                appState.setAllScenarios(scenarios);
            }else{

            }
        } catch (IOException e) {
        }
    }
}
