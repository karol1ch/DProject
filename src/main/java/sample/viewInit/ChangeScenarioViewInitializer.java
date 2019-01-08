package sample.viewInit;

import sample.Main;
import sample.controller.EditScenarioController;

public class ChangeScenarioViewInitializer extends AbstractViewInitializer {

    public ChangeScenarioViewInitializer(Main mainApp) {
        super(mainApp);
    }

    @Override
    void setPath() {
        super.pathToView = "/editScenario.fxml";
    }

    @Override
    void setController() {
        super.controller = new EditScenarioController(mainApp);
    }
}
