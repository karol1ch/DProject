package sample.factory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FakeFirstAidThingsFactory {

    private Random rand = new Random();

    List<String> otherThingsNameList = Arrays.asList("AED", "Respirator", "Leki na cukrzyce", "Leki na astmę", "Leki przeciwbolowe", "Rękawice papierowe");
    private List<String> bandageNameList = Arrays.asList("Bandaż", "Pasek materiału");
    List<String> plasterNameList = Arrays.asList("Plaster", "Kompres");
    List<Integer> plasterSizeList = Arrays.asList(10, 30, 2000, 1589, 300, 1000);
    private List<String> bandageFabricList = Arrays.asList("gips", "wełna", "bawełna", "futro");


    public FirstAidThing getFirstAidThings(String type){
        if(type == null){
            return new NullFirstAidThing();
        }
        if(type.equalsIgnoreCase("sample.factory.Bandage")){  //Tymczasowe rozwiazanie w equals
            return Bandage.builder()
                    .name(bandageNameList.get(rand.nextInt(bandageNameList.size())))
                    .fabric(bandageFabricList.get(rand.nextInt(bandageFabricList.size())))
                    .isTrue(false)
                    .build();
        }
        else if(type.equalsIgnoreCase("sample.factory.Plaster")){
            return Plaster.builder()
                    .name(plasterNameList.get(rand.nextInt(plasterNameList.size())))
                    .size(plasterSizeList.get(rand.nextInt(plasterSizeList.size())))
                    .isTrue(false)
                    .build();
        }
        else if(type.equalsIgnoreCase("sample.factory.OtherThing")){
            return OtherThing.builder()
                    .name(otherThingsNameList.get(rand.nextInt(otherThingsNameList.size())))
                    .isTrue(false)
                    .build();
        }
        return new NullFirstAidThing();
    }
}
