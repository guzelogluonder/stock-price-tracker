package StockMarketValue;

public interface Observable {

    void notifyStock();
    void add(DollarObserverDashboard g);
    void remove(DollarObserverDashboard g);
}
