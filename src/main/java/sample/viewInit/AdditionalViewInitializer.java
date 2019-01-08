package sample.viewInit;

import sample.Main;
import sample.controller.AdditionalController;

public class AdditionalViewInitializer extends AbstractViewInitializer {

    public AdditionalViewInitializer(Main mainApp) {
        super(mainApp);
    }

    @Override
    void setPath() {
        pathToView = "/additionalView.fxml";
    }

    @Override
    void setController() {
        controller = new AdditionalController(mainApp);
    }
}
