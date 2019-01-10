package sample.strategy;

public class SecondLevel implements Strategy {
    @Override
    public int calculate(int x) {
        return x * 2;
    }
}
