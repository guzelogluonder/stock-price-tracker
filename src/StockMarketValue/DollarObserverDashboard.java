package StockMarketValue;

import StockMarketValue.Observer;
import StockMarketValue.ParaGarantiService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Timestamp;

public class DollarObserverDashboard extends JFrame implements Observer {

    String name;
    String currentVale;
    String changeRate;
    ImageIcon redArrow = new ImageIcon("images/red_arrow.png", "");
    ImageIcon greenArrow = new ImageIcon("images/green_arrow.png", "");
    ImageIcon noChangeArrow = new ImageIcon("images/no_change_arrow.png", "");
    ImageIcon dollarIcon = new ImageIcon("images/dollar.png", "");

    JPanel currentValuePanel = new JPanel();
    JPanel changeRatePanel = new JPanel();
    JPanel lineChartPanel = new JPanel();
    JPanel panel = new JPanel();

    JLabel currentValueLabel = new JLabel("N/A");
    JLabel changeRateLabel = new JLabel("N/A");
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    JFreeChart lineChart ;
    JTabbedPane tabbedPane = new JTabbedPane();



    private ParaGarantiService benimPG = new ParaGarantiService();

    public DollarObserverDashboard(String name) {
        initializeUI();
        this.name = name;
    }


    @Override
    public void update() {
        // Update UI
        this.currentVale = benimPG.currentValue;
        this.changeRate = benimPG.changeRate;
        updateUI(this.currentVale, this.changeRate);
    }

    @Override
    public void subscribeObservable(ParaGarantiService p) {
        benimPG = p;
    }


    private void initializeUI() {

        currentValueLabel.setFont(new Font("Serif", Font.BOLD, 56));
        currentValueLabel.setForeground(Color.BLACK);
        currentValueLabel.setIcon(dollarIcon);
        currentValuePanel.add(currentValueLabel);

        changeRateLabel.setFont(new Font("Serif", Font.BOLD, 56));
        changeRateLabel.setForeground(Color.BLACK);
        changeRateLabel.setIcon(noChangeArrow);
        changeRatePanel.add(changeRateLabel);


        tabbedPane.addTab("Current Value", currentValuePanel);
        tabbedPane.addTab("Change Rate", changeRatePanel);

        dataset.addValue( Double.parseDouble("0.00") , "Dollar" ,  new Timestamp(System.currentTimeMillis()));
        /* Create JFreeChart*/
        lineChart = ChartFactory.createLineChart(
                "Change graphic of dollar", // Chart title
                "Date", // X-Axis Label
                "Value", // Y-Axis Label
                dataset
        );

        lineChartPanel = new ChartPanel(lineChart);

        tabbedPane.addTab("Chart", lineChartPanel);

        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(500, 250));
        panel.add(tabbedPane, BorderLayout.CENTER);

        panel.setOpaque(true);

        this.setTitle("STOCK TRACKER");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        this.setVisible(true);
    }

    public void updateUI(String currentValue, String changeRate) {
        currentValueLabel.setText(currentValue);
        changeRateLabel.setText(changeRate);
        if (changeRate.substring(0, 4).equals("0.00")) {
            currentValueLabel.setForeground(Color.BLACK);
            changeRateLabel.setForeground(Color.BLACK);
            changeRateLabel.setIcon(noChangeArrow);
        } else if (changeRate.substring(0, 1).equals("-")) {
            currentValueLabel.setForeground(Color.RED);
            changeRateLabel.setForeground(Color.RED);
            changeRateLabel.setIcon(redArrow);
            dataset.addValue(Double.parseDouble(currentValue.replace(",",".")),"Dollar", new Timestamp(System.currentTimeMillis()));
        } else {
            currentValueLabel.setForeground(Color.GREEN);
            changeRateLabel.setForeground(Color.GREEN);
            changeRateLabel.setIcon(greenArrow);
        }
        this.validate();

    }

}
