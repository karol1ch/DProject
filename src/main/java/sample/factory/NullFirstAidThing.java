package sample.factory;

public class NullFirstAidThing implements FirstAidThing {

    @Override
    public void addToFirstAidKit() {
        System.out.println("Pusty obiekt.");
    }

    @Override
    public boolean isTrueOption() {
        return false;
    }
}
