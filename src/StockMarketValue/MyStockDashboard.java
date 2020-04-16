package StockMarketValue;

import StockMarketValue.gui.DashboardTabs;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class MyStockDashboard {

    public static void main(String[] args) {

        // Observable (Subject created)
        ParaGarantiService pg = new ParaGarantiService();

        // StockTypes are created
        //StockTypeObserver euroObserver = new StockTypeObserver("EURO");
        StockTypeObserver dollarObserver = new StockTypeObserver("USD");
        //StockTypeObserver gbpObserver = new StockTypeObserver("GBP");

        // ParaGaranti Servisi update aldiginda kimleri update etsin

        pg.add(dollarObserver);
        dollarObserver.subscribeObservable(pg);

        // It will make observable to provide data once at 3 seconds.


        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DashboardTabs.showFrame();
            }
        });


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                pg.getStockData();
            }
        }, 30000, 1);
    }

}
