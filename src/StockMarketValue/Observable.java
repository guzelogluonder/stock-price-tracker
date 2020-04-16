package StockMarketValue;

public interface Observable {

    void notifyStock();
    void add(StockTypeObserver g);
    void remove(StockTypeObserver g);
}
