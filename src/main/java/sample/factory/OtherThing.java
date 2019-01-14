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

    @Override
    public FirstAidThing createFakeObject() {
        Random rand = new Random();
        List<String> fakeOtherThingsNameList = Arrays.asList("AED", "Respirator", "Leki na cukrzyce", "Leki na astmę", "Leki przeciwbolowe", "Rękawice papierowe");


        return OtherThing.builder()
                .name(fakeOtherThingsNameList.get(rand.nextInt(fakeOtherThingsNameList.size())))
                .isTrue(false)
                .build();
    }
}
