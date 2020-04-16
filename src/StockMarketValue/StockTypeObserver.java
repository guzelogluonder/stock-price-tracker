package StockMarketValue;


public class StockTypeObserver implements Observer {

    private String name;
    private ParaGarantiService benimPG = new ParaGarantiService();

    public StockTypeObserver(String name) {
        this.name = name;
    }

    @Override
    public void update() {
        // Update UI

        System.out.println(name + " price updated to " + benimPG.myStocks.get(name));
    }

    @Override
    public void subscribeObservable(ParaGarantiService p) {
        benimPG = p;
    }

}
