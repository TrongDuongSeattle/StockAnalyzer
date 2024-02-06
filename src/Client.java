/**
 * Trong Duong
 * trong.duong@bellevuecollege.edu
 * 01/27/24
 * CS320
 * Assignment 1
 */

import java.util.*;

public class Client {
    /**
     * All URl constructors are deprecated now
     * https://docs.oracle.com/en/java/javase/20/docs/api/java.base/java/net/URL.html#constructor-deprecation
     * add throws exception
     */
    public static void main(String[] args) throws Exception {
        //getUserInput();
        System.out.println("##------------------------------------------------------------");
            Scanner sc = new Scanner(System.in);
            System.out.println("These are the available stock list categories, please choose one");
        StockAnalyst stockAnalyzer = new StockAnalyst();
        String webPage = stockAnalyzer.getUrlText(IStockAnalyst.WEB_URL);
        List<String> categories = stockAnalyzer.getStocksListCategories(webPage);
        int i = 0;
        for (String s: categories) {
            System.out.println(i + ". " + s);
            i++;
        }
        i = 0;
        int userSelection =  sc.nextInt();
        System.out.println("##------------------------------------------------------------------");
        System.out.println("These are the available stock list categories, please choose key");
        String subcategories = stockAnalyzer.parseStocksListsInListCategory(webPage, categories.get(userSelection));
        Map<String, String> mapOfCategories = new LinkedHashMap<String, String>(stockAnalyzer.getStocksListsInListCategory(subcategories, categories.get(userSelection)));
        String[] array = new String[mapOfCategories.size()];
        for (Map.Entry<String, String> entry : mapOfCategories.entrySet()) {
            System.out.println(i + ". " + entry.getKey());
            array[i] = entry.getKey();
            i++;
        }
        userSelection = sc.nextInt();
        String stockWebPage = stockAnalyzer.getUrlText("https://stockanalysis.com" + mapOfCategories.get(array[userSelection]));
        System.out.println("##------------------------------------------------------------------");
        System.out.println("How many companies would you like to display?");
        userSelection = sc.nextInt();
        System.out.println("##------------------------------------------------------------------");
        System.out.println("This is the list of the top " + userSelection + " companies by change percentage ");
        Map<Double, String> finalMap = new TreeMap<Double, String>(stockAnalyzer.getTopCompaniesByChangeRate(stockWebPage, userSelection).descendingMap());
        int count = 0;
        for (Map.Entry<Double, String> entry : finalMap.entrySet()) {
            if (count == userSelection) {
                break;
            }
            System.out.println(entry.getValue() + ", " + entry.getKey() + "%");
            count++;
        }
        System.out.println("##------------------------------------------------------------------");
        try {
            System.out.println(stockAnalyzer.getStocksListCategories(stockWebPage).get(1));
            String tableOfRelatedStocks = stockAnalyzer.parseStocksListsInStocks(stockWebPage, stockAnalyzer.getStocksListCategories(stockWebPage).get(1));
            Map<Double, String> relatedStockMap = new TreeMap<Double, String>(stockAnalyzer.getRelatedTopCompaniesByChangeRate(tableOfRelatedStocks, userSelection).descendingMap());
            count = 0;
            for (Map.Entry<Double, String> entry : relatedStockMap.entrySet()) {
                if (count == userSelection) {
                    break;
                }
                System.out.println(entry.getValue() + ", " + entry.getKey() + "%");
                count++;
            }
        } catch (Exception e) {
            return;
        }
    }
}