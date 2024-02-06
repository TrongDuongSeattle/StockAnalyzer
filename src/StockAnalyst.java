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
    public String getUrlText(String url) throws Exception {
        URL oracle = new URL(url);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine;
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
    public List<String> getStocksListCategories(String urlText) {
        Pattern pattern = Pattern.compile("<h2.*?>(.*?)</h2>");
        Matcher matcher = pattern.matcher(urlText);
        List<String> listOfChoices = new ArrayList<String>();
        while (matcher.find()) {
            listOfChoices.add(matcher.group(1));
        }
        return listOfChoices;
    }

    /**
     * Helper method to distinguish subcategories on main page
     *
     * @param urlText      complete webpage
     * @param categoryName h2 headers of webpage
     * @return the portion of the HTML file containing only the subcategories of the given category name
     */
    public String parseStocksListsInListCategory(String urlText, String categoryName) {
        String subcategories = "";
        String newPattern = categoryName + ".*?</div>";
        Pattern pattern = Pattern.compile(newPattern);
        Matcher matcher = pattern.matcher(urlText);
        while (matcher.find()) {
            subcategories = matcher.group();
        }
        return subcategories;
    }

    /**
     * Get stocks from related table of stock page
     * @param urlText Webpage of stock category containing primary and related stock tables
     * @param categoryName Name of related stock table
     * @return Portion of HTML file containing only the related stock table.
     */
    public String parseStocksListsInStocks(String urlText, String categoryName) {
        String subcategories = "";
        String newPattern = categoryName + ".*?</tbody>";
        Pattern pattern = Pattern.compile(newPattern);
        Matcher matcher = pattern.matcher(urlText);
        while (matcher.find()) {
            subcategories = matcher.group();
        }
        return subcategories;
    }

    /**
     * Method to get the list of stocks within a stock list category
     *
     * @param urlText           the text of the page that has the stock list and their categories
     * @param stockCategoryName the stock list category name
     * @return map that contains stock lists and their URLs. Key is stock list name, and value is the URL
     * Example of a map entry <“Mega-Cap”, https://stockanalysis.com/list/mega-cap-stocks/>
     * That makes sense because all the subcategories match the same structure, and the regex finds a match for them and returns it.
     * Preparing the text you need to send for this regex to match could help.
     * A hint: If we exclude only the HTML text that is specific to the specific category and pass it to the regex matcher,
     * it will help to focus only on the subcategory that you are interested in instead of the entire list of all subcategories.
     */

    // Ignore change percentages that are not numbers (e.g. "-")

    public Map<String, String> getStocksListsInListCategory(String urlText, String stockCategoryName) {
        Pattern pattern = Pattern.compile("<li.*?><a href=\"(.*?)\">\\b(.*?)\\b</a>");
        Matcher matcher = pattern.matcher(urlText);
        Map<String, String> mapOfChoices = new LinkedHashMap<String, String>();
        while (matcher.find()) {
            mapOfChoices.put(matcher.group(2), matcher.group(1));
        }
        return mapOfChoices;
    }

    /**
     * Method to get top companies by change rate
     *
     * @param urlText  the text of the page that has the stock list
     * @param topCount how many companies to return
     * @return map that has the top companies and their change rate. Key is the change rate and value is the company name
     */
    public TreeMap<Double, String> getTopCompaniesByChangeRate(String urlText, int topCount) {
        Pattern pattern = Pattern.compile("</a><.*?></td><td .*?>(.*?)</td>" +
                ".*?" +
                "<td class=\"r*[^>]+>(-|-?\\d+\\.\\d+%)</td>");
        Matcher matcher = pattern.matcher(urlText);
        TreeMap<Double, String> TreeMapOfChoices = new TreeMap<Double, String>();
        String newStockPercentage;
        double stockPercentage;
        int count = 0;
        while(matcher.find()) {
            if (matcher.group(2).equals("-")) {
                newStockPercentage = matcher.group(2).replace("-", "0");
            }else {
                newStockPercentage = matcher.group(2).replace("%", "");
            }
            stockPercentage = Double.parseDouble(newStockPercentage);
            TreeMapOfChoices.put(stockPercentage, matcher.group(1));
            count++;
        }
        return TreeMapOfChoices;
    }

    /**
     * Gets table of related stocks
     *
     * @param urlText the portion of the HTML file containing the related stocks only
     * @param topCount number of entries user would like to display
     * @return map that has the top companies and their change rate. Key is the change rate and value is the company name
     */
    public TreeMap<Double, String> getRelatedTopCompaniesByChangeRate(String urlText, int topCount) {
        Pattern pattern = Pattern.compile("</a><.*?></td><td .*?>(.*?)</td>" +
                ".*?" +
                "<td class=\"r*[^>]+>(-|-?\\d+\\.\\d+%)</td>");
        Matcher matcher = pattern.matcher(urlText);
        TreeMap<Double, String> TreeMapOfChoices = new TreeMap<Double, String>();
        String newStockPercentage;
        double stockPercentage;
        int count = 0;
        while(matcher.find()) {
            if (matcher.group(2).equals("-")) {
                newStockPercentage = matcher.group(2).replace("-", "0");
            }else {
                newStockPercentage = matcher.group(2).replace("%", "");
            }
            stockPercentage = Double.parseDouble(newStockPercentage);
            TreeMapOfChoices.put(stockPercentage, matcher.group(1));
            count++;
        }
        return TreeMapOfChoices;
    }
}
