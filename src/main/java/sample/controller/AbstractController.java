package sample.controller;

import javafx.fxml.Initializable;
import sample.Main;

public abstract class AbstractController implements Initializable {

    protected Main mainApp;

    public AbstractController(Main mainApp) {
        this.mainApp = mainApp;
    }
}
