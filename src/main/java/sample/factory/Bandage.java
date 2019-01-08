package sample.factory;


import lombok.Data;

@Data
public class Bandage implements FirstAidThings {

    private String name;
    private String fabric;

    @Override
    public void addToFirstAidKit() {
        System.out.println("Dodaje " + name + ", materiał to  " + fabric);
    }
}
