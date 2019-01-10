package sample.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Plaster implements FirstAidThing {

    private String name;
    private int size;
    private boolean isTrue;

    @Override
    public void addToFirstAidKit() {
        System.out.println("Dodaje " + name + " o rozmiarze " + size);
    }

    @Override
    public boolean isTrueOption() {
        return isTrue;
    }
}
