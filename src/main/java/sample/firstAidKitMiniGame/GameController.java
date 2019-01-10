package sample.firstAidKitMiniGame;

import sample.factory.FakeFirstAidThingsFactory;
import sample.factory.FirstAidThing;
import sample.factory.MockedObjects;

import java.util.*;

public class GameController {

    private Scanner scanner = new Scanner(System.in);
    private Random rand = new Random();

    private int points;
    private boolean condition;
    int randomIndex;
    int answer;

    private GameView gameView;
    private List<FirstAidThing> trueList;
    private FakeFirstAidThingsFactory factory;
    private List<FirstAidThing> threeOptions = new ArrayList<>();

    public GameController(){
        gameView = new GameView();
        MockedObjects mockedObjects = new MockedObjects();
        trueList = mockedObjects.createTrueObjects();
        factory = new FakeFirstAidThingsFactory();
    }

    public void startGame(){
        String field;
        gameView.startGameView();
        do {
            field = scanner.nextLine();
            System.out.println(field);
        } while (!field.equalsIgnoreCase("START"));
        play();
    }

    private void play(){
        gameView.startPlayView();
        prepareGame();
        do{
            List<FirstAidThing> tempThreeOptions = generateOptions();
            for( int i = 0; i < 3; i++) {
                randomIndex = rand.nextInt(tempThreeOptions.size());
                System.out.print(i + ": ");
                tempThreeOptions.get(randomIndex).addToFirstAidKit();
                threeOptions.add(tempThreeOptions.get(randomIndex));
                tempThreeOptions.remove(randomIndex);
            }
            System.out.println("Podaj poprawną odpowiedź:");
            answer = scanner.nextInt();
            if(threeOptions.get(answer).isTrueOption()){
                points++;
                condition = true;
            }
            else{
                System.out.println("Koniec. Twój wynik to:");
                System.out.println(points);
                condition = false;
            }

        } while(condition);

        System.out.println("Jeśli chcesz kontynuować wciśnij 1, jeśli chcesz przerwać wciśnij 2");
        answer = scanner.nextInt();
        if(answer == 1){
            startGame();
        }
        else if( answer == 2){
            stopGame();
        }
    }

    private void prepareGame(){
        condition = true;
        points = 0;
    }

    private List<FirstAidThing> generateOptions(){
        randomIndex = rand.nextInt(trueList.size());
        FirstAidThing p1 = trueList.get(randomIndex);
        trueList.remove(randomIndex);
        FirstAidThing p2 = factory.getFirstAidThings(p1.getClass().getName());
        FirstAidThing p3 = factory.getFirstAidThings(p1.getClass().getName());
        List<FirstAidThing> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        return list;
    }

    private void stopGame(){
        System.exit(1);
    }



}
