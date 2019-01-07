package sample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.*;




@Builder
@Data
@AllArgsConstructor
public class Scenario implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String FIRST_STATE_NAME = "START";
    public static final String LAST_STATE_NAME = "KONIEC";


    private String name;
    private  String pathToFile;
    private Map<Integer,State> states;
    private List<String> checkListStates;



    public Scenario() {
        states = new HashMap<>();
        checkListStates = new ArrayList<>();
    }




    public State getInitialState() throws Exception {

        return states.values().stream()
                .filter(s -> FIRST_STATE_NAME.equals(s.getName()))
                .findAny()
                .orElseThrow(() -> new Exception("Brak stanu o nazwie 'START'"));
    }

    public static Scenario createEmptyScenario(){
        Scenario scenario = new Scenario();
        Map<Integer,State> states = new HashMap<>();
        State endState = new State(999,LAST_STATE_NAME,Collections.emptyList(),"");
        State startState = new State(0,FIRST_STATE_NAME, Arrays.asList(endState.getNumber()),"");
        states.put(startState.getNumber(),startState);
        states.put(endState.getNumber(),endState);
        scenario.setStates(states);
        scenario.setName("Nowy scenariusz");
        scenario.setPathToFile("");
        scenario.setCheckListStates(new LinkedList<>());
        return scenario;
    }

    public Integer getNextId() {
        return getStates().keySet().stream().max(Integer::compareTo).get()+1;
    }
}
