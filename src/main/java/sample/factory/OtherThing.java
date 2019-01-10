package sample.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtherThing implements FirstAidThing {

    private String name;
    private boolean isTrue;

    @Override
    public void addToFirstAidKit() {
        System.out.println("Dodaje " + name);
    }

    @Override
    public boolean isTrueOption() {
        return isTrue;
    }
}
