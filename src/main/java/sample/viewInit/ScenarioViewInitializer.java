package sample.viewInit;

import sample.Main;
import sample.controller.ScenarioController;

public class ScenarioViewInitializer extends AbstractViewInitializer {

    public ScenarioViewInitializer(Main mainApp) {
        super(mainApp);
    }

    @Override
    void setPath() {
        super.pathToView = "/scenario.fxml";
    }

    @Override
    void setController() {
        super.controller = new ScenarioController(mainApp);
    }
}
