package StockMarketValue;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParaGarantiService implements Observable {

    private List<StockTypeObserver> stockTypeList = new ArrayList<>();
    Map<String, String> myStocks = new HashMap<>();


    @Override
    public void notifyStock() {
        for (StockTypeObserver g : stockTypeList) {
            g.update();
        }
    }

    @Override
    public void add(StockTypeObserver g) {

        stockTypeList.add(g);

    }

    @Override
    public void remove(StockTypeObserver g) {

        stockTypeList.remove(g);

    }

    public void pullStockPrice(String title) {
        // this.title = title;
        notifyStock();
    }

    public void getStockData() {
        String url = "https://realtime.paragaranti.com/asp/xml/icpiyasaX.xml";
        try {

            // URL object is created
            URL obj = new URL(url);

            // HTTP Request done. Like opening a browser.
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Read packet by packet
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));


            String inputLine;

            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader(response.toString())));

            NodeList errNodes = doc.getElementsByTagName("STOCK");

            // I have all data

            // I need to parse

            if (errNodes.getLength() > 0) {
                Element euroData = (Element) errNodes.item(2);
                Element dollarData = (Element) errNodes.item(1);
                Element gbpData = (Element) errNodes.item(3);

                //myStocks.put("EURO", euroData.getElementsByTagName("LAST").item(0).getTextContent());
                myStocks.put("USD", dollarData.getElementsByTagName("LAST").item(0).getTextContent());
                //myStocks.put("GBP", gbpData.getElementsByTagName("LAST").item(0).getTextContent());

                //System.out.println("euro " + myStocks.get("EURO"));
                System.out.println("dollar " + myStocks.get("USD"));
                //System.out.println("gbp " + myStocks.get("GBP"));


                notifyStock();

            } else {
                // success
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }


    }
}
