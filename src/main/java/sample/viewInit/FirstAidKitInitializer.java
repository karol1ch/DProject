package sample.viewInit;

import sample.Main;
import sample.controller.FirstAidKitController;

public class FirstAidKitInitializer extends AbstractViewInitializer {

    public FirstAidKitInitializer(Main mainApp) {
        super(mainApp);
    }

    @Override
    void setPath() {
        pathToView = "/firstAidKitView.fxml";
    }

    @Override
    void setController() {
        controller = new FirstAidKitController(mainApp);
    }
}
