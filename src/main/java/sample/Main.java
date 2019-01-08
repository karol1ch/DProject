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
import sample.viewInit.ChooseScenarioViewInitializer;

import java.io.IOException;
import java.net.URL;
import java.util.List;


public class Main extends Application {

    private AppState appState = AppState.INSTANCE;
    private static BorderPane mainRoot;

    public BorderPane getMainRoot() {
        return mainRoot;
    }

    public void setMainRoot(BorderPane mainRoot) {
        this.mainRoot = mainRoot;
    }

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

        init(new ChooseScenarioViewInitializer(this).initView());
    }

    public static void init(FXMLLoader loader) throws IOException {
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
