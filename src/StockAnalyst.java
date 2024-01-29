import java.util.*;

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
        URL oracle = new URL(url);
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
        Pattern pattern = Pattern.compile("<h2.*?>(.*?)</h2>");
        Matcher matcher = pattern.matcher(urlText);
        int i = 0;
        List<String> listOfChoices = new ArrayList<String>();
        while(matcher.find()) {
            listOfChoices.add(matcher.group(1));
        }
        return listOfChoices;
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
     * <a href="/list/biggest-companies/">Biggest Companies By Market Cap</a>
     * example call
     * obj.getStocksListsInCategory(
     * must click on list to get to data...
     *
     * feels duplicative
     */
    @Override
    public Map<String, String> getStocksListsInListCategory(String urlText, String stockCategoryName) {
        // Key is stock list name, and value is the URL
        //"<li><a (.*?)>(.*?)</a></li>"
        //<ul class="list.*?><li><a href="(.*?)">(.*?)</a> </li></ul>
        //<ul .*?><li><a href="(.*?)">(.*?)</a> </li></ul>
        //<li><a href="(.*?)">(.*?)</a> </li>
        //(<li>)<a href=\"(.*?)\">(</a> </li>(</ul></div>))*?(.*?)</a>
        Pattern pattern = Pattern.compile("<li><a href=\"(.*?)\">");
        Matcher matcher = pattern.matcher(urlText);
        int i = 0;
        Map<String, String> mapOfChoices = new HashMap<String, String>();
        while(matcher.find()) {
           // System.out.println(matcher.group());
            //System.out.println(matcher.group(2) + "-----------" + matcher.group(1));
            //mapOfChoices.put(matcher.group(2), matcher.group(1));
        }

        System.out.println( mapOfChoices.isEmpty());
        return mapOfChoices;
    }

    /**
     * Method to get top companies by change rate
     *
     * @param urlText  the text of the page that has the stock list
     * @param topCount how many companies to return
     * @return map that has the top companies and their change rate. Key is the change rate and value is the company name
     * <p>
     * notice it returns a TreeMap
     *
     */
    @Override
    public TreeMap<Double, String> getTopCompaniesByChangeRate(String urlText, int topCount) {

        return null;
    }
}
