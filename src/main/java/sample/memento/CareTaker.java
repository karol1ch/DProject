package sample.memento;

public class CareTaker {

    private Memento memento;

    public void add(Memento state){
        memento = state;
    }

    public Memento get(){
        return memento;
    }
}
