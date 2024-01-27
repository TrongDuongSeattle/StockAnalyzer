import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import java.net.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StockAnalyst implements IStockAnalyst {
    /**
     * Method to get the text of a given web URL
     *
     * @param url the web URL address
     * @return the web URL page text
     */
    @Override
    public String getUrlText(String url) throws Exception {
        URL oracle = new URL(WEB_URL);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine = "";
        String text = "";
        while ((inputLine = in.readLine()) != null) {
            text += inputLine + "\n";

        }
        in.close();
        return text;
    }

    /**
     * Method to get the categories of stock lists
     *
     * @param urlText the text of the page that has the stock list and their categories
     * @return list of stock lists categories
     */
    @Override
    public List<String> getStocksListCategories(String urlText) {
        Pattern pattern = Pattern.compile("<li>(.*?)</li>");
        Matcher matcher = pattern.matcher(urlText);
        while(matcher.find()) {
            //return according to format
//            System.out.println("Class Time: " + matcher.group(1));
//            System.out.println("Exam Day: " + matcher.group(3));
//            System.out.println("Exam time: " + matcher.group(6));
//            System.out.println("++++++++++++++++++++++++++++++");
            System.out.println(matcher.group());
        }

        return null;
    }

    /**
     * Method to get the list of stocks within a stock list category
     *
     * @param urlText           the text of the page that has the stock list and their categories
     * @param stockCategoryName the stock list category name
     * @return map that contains stock lists and their URLs. Key is stock list name, and value is the URL
     * Example of a map entry <“Mega-Cap”, https://stockanalysis.com/list/mega-cap-stocks/>
     * <p>
     * note the ahref gives the full URL
     */
    @Override
    public Map<String, String> getStocksListsInListCategory(String urlText, String stockCategoryName) {
        return null;
    }

    /**
     * Method to get top companies by change rate
     *
     * @param urlText  the text of the page that has the stock list
     * @param topCount how many companies to return
     * @return map that has the top companies and their change rate. Key is the change rate and value is the company name
     * <p>
     * notice it returns a TreeMap
     */
    @Override
    public TreeMap<Double, String> getTopCompaniesByChangeRate(String urlText, int topCount) {
        return null;
    }
}
