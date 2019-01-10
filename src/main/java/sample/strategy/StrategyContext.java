package sample.strategy;

public class StrategyContext {
    private Strategy strategy;

    public StrategyContext(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int x){
        return strategy.calculate(x);
    }
}
