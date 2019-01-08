package sample.factory;

import lombok.Data;

@Data
public class OtherThing implements FirstAidThings {

    private String name;
    private String description;
    private boolean isInKit;

    @Override
    public void addToFirstAidKit() {
        System.out.println("Dodaje " + name + ", o następującym zastosowaniu:");
        System.out.println(description);
    }
}
