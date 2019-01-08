package sample.factory;

import lombok.Data;

@Data
public class Plaster implements FirstAidThings {

    private String name;
    private int size;

    @Override
    public void addToFirstAidKit() {
        System.out.println("Dodaje " + name + " o rozmiarze " + size);
    }
}
