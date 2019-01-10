package sample.strategy;

public class FirstLevel implements Strategy {
    @Override
    public int calculate(int x) {
        return x;
    }
}
