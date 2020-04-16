package StockMarketValue.gui;

import javax.swing.*;
import java.awt.*;

public class DashboardTabs extends JPanel {
    public DashboardTabs() {
        initializeUI();
    }

    public static void showFrame() {
        JPanel panel = new DashboardTabs();
        panel.setOpaque(true);

        JFrame frame = new JFrame("Simple Tabbed Pane Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }


    private void initializeUI() {
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel dashboardPanel = new JPanel();
        dashboardPanel.add(new JLabel("Current Value"));

        // Add Dashboard Tab
        tabbedPane.addTab("Current Value", dashboardPanel);

        JPanel transactionPanel = new JPanel();
        transactionPanel.add(new JLabel("Arrow"));

        // Add Transactions Tab
        tabbedPane.addTab("Arrow", transactionPanel);

        JPanel accountPanel = new JPanel();
        accountPanel.add(new JLabel("Change Graphic"));

        // Add Account Tab
        tabbedPane.addTab("Change Graphic", accountPanel);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(500, 200));
        this.add(tabbedPane, BorderLayout.CENTER);
    }
}
