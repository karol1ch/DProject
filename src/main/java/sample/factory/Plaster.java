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

    @Override
    public FirstAidThing createFakeObject() {
        Random rand = new Random();
        List<String> fakePlasterNameList = Arrays.asList("Plaster", "Kompres");
        List<Integer> fakePlasterSizeList = Arrays.asList(10, 30, 2000, 1589, 300, 1000);

        return Plaster.builder()
                .name(fakePlasterNameList.get(rand.nextInt(fakePlasterNameList.size())))
                .size(fakePlasterSizeList.get(rand.nextInt(fakePlasterSizeList.size())))
                .isTrue(false)
                .build();
    }
}
