package sample.factory;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bandage implements FirstAidThing {

    private String name;
    private String fabric;
    private boolean isTrue;

    @Override
    public void addToFirstAidKit() {
        System.out.println("Dodaje " + name + ", materiał to  " + fabric);
    }

    @Override
    public boolean isTrueOption() {
        return isTrue;
    }
}
