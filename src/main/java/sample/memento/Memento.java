package sample.memento;


import javafx.collections.ObservableList;

public class Memento {

    ObservableList<String> items;

    public Memento(ObservableList<String> items) {
        this.items = items;
        System.out.println("Dodaje: " + items);
    }

    public ObservableList<String> getItems() {
        System.out.println("Wracam: " + items);
        return items;
    }
}
