package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Main;
import sample.viewInit.ChooseScenarioViewInitializer;
import sample.viewInit.FirstAidKitInitializer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.init;

public class AdditionalController extends AbstractController {

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button firstAidKitButton;

    public AdditionalController(Main mainApp) {
        super(mainApp);
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

        firstAidKitButton.setOnAction(event -> {
            try {
                init(new FirstAidKitInitializer(mainApp).initView());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
