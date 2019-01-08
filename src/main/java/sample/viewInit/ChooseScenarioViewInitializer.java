package sample.viewInit;

import sample.Main;
import sample.controller.ChooseScenarioController;

public class ChooseScenarioViewInitializer extends AbstractViewInitializer {

    public ChooseScenarioViewInitializer(Main mainApp) {
        super(mainApp);
    }

    @Override
    void setPath() {
        super.pathToView = "/chooseScenario.fxml";
    }

    @Override
    void setController() {
        super.controller = new ChooseScenarioController(mainApp);
    }
}
