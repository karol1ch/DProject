package sample.strategy;

public class ThirdLevel implements Strategy {
    @Override
    public int calculate(int x) {
        return x * 3;
    }
}
