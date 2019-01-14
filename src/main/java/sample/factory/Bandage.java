package sample.factory;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    @Override
    public FirstAidThing createFakeObject() {

        Random rand = new Random();
        List<String> bandageNameList = Arrays.asList("Bandaż", "Pasek materiału");
        List<String> bandageFabricList = Arrays.asList("gips", "wełna", "bawełna", "futro");

        return Bandage.builder()
                .name(bandageNameList.get(rand.nextInt(bandageNameList.size())))
                .fabric(bandageFabricList.get(rand.nextInt(bandageFabricList.size())))
                .isTrue(false)
                .build();
    }
}
