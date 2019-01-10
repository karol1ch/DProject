package sample.factory;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MockedObjects {

    public List<FirstAidThing> createTrueObjects() {
        List<FirstAidThing> trueFirstAidThings = new ArrayList<>();
        FirstAidThing plaster1 = Plaster.builder().name("Plastry").size(60).isTrue(true).build();
        FirstAidThing plaster2 =  Plaster.builder().name("Plastry").size(1250).isTrue(true).build();
        FirstAidThing plaster3 = Plaster.builder().name("Kompres").size(100).isTrue(true).build();
        FirstAidThing plaster4 =  Plaster.builder().name("Kompres").size(25).isTrue(true).build();

        FirstAidThing bandage1 = Bandage.builder().name("Bandaż").fabric("dzianina").isTrue(true).build();
        FirstAidThing bandage2 = Bandage.builder().name("Bandaż").fabric("len").isTrue(true).build();
        FirstAidThing bandage3 = Bandage.builder().name("Bandaż").fabric("muślin").isTrue(true).build();

        FirstAidThing otherThing1 = OtherThing.builder().name("Rękawiczki jednorazowe").isTrue(true).build();
        FirstAidThing otherThing2 = OtherThing.builder().name("Koc ratunkowy").isTrue(true).build();
        FirstAidThing otherThing3 = OtherThing.builder().name("Chusta trójkątna").isTrue(true).build();
        FirstAidThing otherThing4 = OtherThing.builder().name("maseczka do sztuczenego oddychania").isTrue(true).build();

        trueFirstAidThings.add(plaster1);
        trueFirstAidThings.add(plaster2);
        trueFirstAidThings.add(plaster3);
        trueFirstAidThings.add(plaster4);
        trueFirstAidThings.add(bandage1);
        trueFirstAidThings.add(bandage2);
        trueFirstAidThings.add(bandage3);
        trueFirstAidThings.add(otherThing1);
        trueFirstAidThings.add(otherThing2);
        trueFirstAidThings.add(otherThing3);
        trueFirstAidThings.add(otherThing4);

        return trueFirstAidThings;
    }




}
