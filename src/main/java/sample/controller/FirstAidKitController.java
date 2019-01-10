package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Main;
import sample.firstAidKitMiniGame.GameController;
import sample.viewInit.ChooseScenarioViewInitializer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.init;

public class FirstAidKitController extends AbstractController {

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button startGameButton;

    private GameController gameController;

    public FirstAidKitController(Main mainApp) {
        super(mainApp);
        gameController = new GameController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mainMenuButton.setOnAction(event -> {
            try {
                init(new ChooseScenarioViewInitializer(mainApp).initView());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        startGameButton.setOnAction(event -> {
            gameController.startGame();
        });

    }
}
