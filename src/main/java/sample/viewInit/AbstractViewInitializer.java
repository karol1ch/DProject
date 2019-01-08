package sample.viewInit;

import javafx.fxml.FXMLLoader;
import sample.Main;
import sample.controller.AbstractController;

import java.io.IOException;
import java.net.URL;

public abstract class AbstractViewInitializer {
    protected Main mainApp;
    String pathToView;
    AbstractController controller;

    AbstractViewInitializer(Main mainApp) {
        this.mainApp = mainApp;
    }

    abstract void setPath();
    abstract void setController();

    public final FXMLLoader initView() throws IOException {
        setPath();
        setController();
        URL resource = getClass().getResource(pathToView);
        FXMLLoader loader = new FXMLLoader(resource);
        loader.setController(controller);
        return loader;

    }
}
