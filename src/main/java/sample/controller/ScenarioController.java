package sample.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import sample.Main;
import sample.model.Scenario;
import sample.model.State;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ScenarioController extends AbstractController {

    private Scenario currentScenario;
    private State currentState;
    private State nextSa;
    private boolean firstLoop = false;
    private int progressChange;
    private int secondsSum;

    private static final Integer STARTTIME = 0;
    private Timeline timeline;
    private Integer timeSeconds = STARTTIME;
    private int minute;


    ObservableList <String> items = FXCollections.observableArrayList();

    List <CheckBox> checkBoxList = new ArrayList<>();

    @FXML
    private Button returnToMainMenu;

    @FXML
    private  Button startButton;
    @FXML
    private Label scenarioName;

    @FXML
    private ButtonBar buttonBar;

    @FXML
    private ListView<String> listView;

    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox vBox;

    @FXML
    private VBox vBoxOnChecking;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label percentsValue;

    @FXML
    private Label timeLabel;

    @FXML
    private VBox vBoxRight;

    @FXML
    private Button descriptionButton;

    @FXML
    private Button restartButton;

    @FXML
    private TextArea messageField;

    public ScenarioController(Main mainApp) {
        super(mainApp);
    }

    @FXML
    private void handleMainMenuButtonAction( ActionEvent actionEvent) throws IOException{
        if(firstLoop) {
            timeline.stop();
        }
        mainApp.initChooseScenarioView();
    }

    @FXML
    private void handleStartButtonAction( ActionEvent actionEvent) throws IOException {
        updateScenarioStateView();
        checkList();
        secondsSum = 0;
        minute = 0;
        firstLoop = false;
        stopWatch();
    }

    private void openFile(){

        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "OpisyScenariuszy" + File.separator + currentScenario.getPathToFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setWrapping(){
        listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                ListCell<String> cell = new ListCell<String>() {
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty) {
                            Text text = new Text(item);
                            text.wrappingWidthProperty().bind(listView.widthProperty().subtract(20));
                            setGraphic(text);
                        }
                    }
                };
                return cell;
            }
        });
    }

    private void checkList(){
        for( String string: currentScenario.getCheckListStates()) {
            CheckBox checkBox = new CheckBox(string);
            checkBoxList.add(checkBox);
        }
        vBoxOnChecking.getChildren().addAll(checkBoxList);
    }

    private void progress(){

        progressChange = 0;
        for (CheckBox aCheckBoxList : checkBoxList) {
            if (aCheckBoxList.isSelected()) {
                progressChange++;
            }
        }
        double percents = (double)progressChange / (double)checkBoxList.size();
        progressBar.setProgress(percents);
        percents*=100;
        percentsValue.setText(Integer.toString((int)percents) + " % ");

    }

    private void stopWatch(){
        if (timeline != null) {
            secondsSum += minute * 60 + timeSeconds;
            minute = 0;
            timeline.stop();
        }
        timeSeconds = STARTTIME;
        timeLabel.setText(minute +" min  "+timeSeconds.toString()+" s");
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        event1 -> {
                            progress();
                            timeSeconds++;
                            timeLabel.setText(minute + " min  " + timeSeconds.toString() + " s");
                            if(timeSeconds == 59){
                                minute++;
                                timeSeconds = 0;
                            }
                        }));
        timeline.playFromStart();
    }

    private boolean setMessageField(State state){
        if("".equals(state.getDescription())){
            messageField.setVisible(false);
            return false;
        }
        else{
            messageField.setText(state.getDescription());
            messageField.setVisible(true);
            return true;
        }
    }


    private void updateScenarioStateView() {

        if(firstLoop){
            progress();
        }
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.setItems(items);  // items -> stateHistoryList
        addToList(currentState.getName());
        listView.scrollTo(items.size());
        List<Integer> children = currentState.getChildren();
        for(Integer integer: children){
            if(setMessageField(currentScenario.getStates().get(integer))){
                break;
            }
        }
        List<Button> nextStepButtons = children.stream().map(currentScenario.getStates()::get).map(s -> {
            Button button = new Button(s.getName());
            button.setOnAction((e)->{
                stopWatch();
                currentState = s;
                updateScenarioStateView();
            });
            return button;
        }).collect(Collectors.toList());
        vBox.getChildren().removeAll(vBox.getChildren());
        vBox.getChildren().addAll(nextStepButtons);
        setWrapping();

        if(nextStepButtons.isEmpty()){
            minute = secondsSum / 60;
            secondsSum = secondsSum % 60;
            Label label = new Label("Czas scenariusza: " + minute + " minut  " + secondsSum + " sekund");
            vBox.getChildren().add(label);


        }
        firstLoop = true;
    }


    public void addToList(String string){
        items.add(string);
    }

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        currentScenario = mainApp.getAppState().getScenarioToShow();
        scenarioName.setText(currentScenario.getName());
        try {
            currentState = currentScenario.getInitialState();
        } catch (Exception e) {
            //TODO obsługa błędu
            e.printStackTrace();
        }
        scrollPane.setFitToWidth(true);
        messageField.setVisible(false);
        descriptionButton.setOnAction(event -> {
            openFile();
        });

        restartButton.setOnAction(event -> {
            mainApp.getAppState().setScenarioToShow(currentScenario);
            try {
                if(firstLoop){
                timeline.stop();}

                mainApp.initScenarioView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }




}
