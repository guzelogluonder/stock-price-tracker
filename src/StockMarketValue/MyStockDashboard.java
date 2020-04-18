package StockMarketValue;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class MyStockDashboard {

    public static void main(String[] args) {

        // Observable (Subject created)
        ParaGarantiService pg = new ParaGarantiService();

        // StockTypes are created
        DollarObserverDashboard dollarObserver = new DollarObserverDashboard("USD");
        pg.add(dollarObserver);
        dollarObserver.subscribeObservable(pg);

        Timer timer = new Timer();
        // It will make observable to provide data once at 3 seconds.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    pg.getStockData();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, 15);
    }
}
