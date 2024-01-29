/**
 * Trong Duong
 * trong.duong@bellevuecollege.edu
 * 01/27/24
 * CS320
 * Assignment 1
 */

import java.util.*;

public class Client {
    /*
     * TODO:
     * Develop the Client.java class, which should contain the main method.
     * The Client class will be used to:
     * test your application's behavior,
     * handle the interaction with the user,
     * and replicate the sample run example.
     * It should not include any business logic (implementation details of the application's core functionalities).
     * getTopCompaniesByChangeRate
     * currently unsorted list, need to find TOP change values.
     */

    /**
     * Method to get User Input
     * used recursion to keep running in case of wrong input.
     */
//    static void getUserInput() {
//        try {
//            System.out.println("##------------------------------------------------------------------");
//            Scanner sc = new Scanner(System.in);
//            System.out.println("These are the available stock list categories, please choose one");
//            int userSelection = sc.nextInt();
//
//
//           //StockAnalyst.getStocksListCategories(userSelection);
//        } catch (InputMismatchException e) {
//            System.out.println("Please enter a valid number");
//            getUserInput();
//        }
//   }
    /**
     * All URl constructors are deprecated now
     * https://docs.oracle.com/en/java/javase/20/docs/api/java.base/java/net/URL.html#constructor-deprecation
     * add throws exception
     */
    public static void main(String[] args) throws Exception {
        //getUserInput();
        System.out.println("##------------------------------------------------------------------");
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
        //make more robust
        int userSelection =  sc.nextInt();
        System.out.println("##------------------------------------------------------------------");
        System.out.println("These are the available stock list categories, please choose key");

        String subcategories = stockAnalyzer.parseStocksListsInListCategory(webPage, categories.get(userSelection));
        Map<String, String> mapOfCategories = new LinkedHashMap<String, String>(stockAnalyzer.getStocksListsInListCategory(subcategories, categories.get(userSelection)));
        //user enters a number....maps aren't indexed....this is my workaround XD
        //something, something iterator?
        String[] array = new String[mapOfCategories.size()];
        for (Map.Entry<String, String> entry : mapOfCategories.entrySet()) {
            System.out.println(i + ". " + entry.getKey());
            array[i] = entry.getKey();
            i++;
        }
        userSelection = sc.nextInt();
        String stockWebPage = stockAnalyzer.getUrlText("https://stockanalysis.com" + mapOfCategories.get(array[userSelection]));

        System.out.println("##------------------------------------------------------------------");
        System.out.println("This is the list of the top (INSERT USER NUM HERE) companies by change percentage ");
        TreeMap<Double, String> finalMap = new TreeMap<Double, String>(stockAnalyzer.getTopCompaniesByChangeRate(stockWebPage,4));
        for (Map.Entry<Double, String> entry : finalMap.entrySet()) {
            System.out.println(entry.getValue() + ", " + entry.getKey() + "%");
        }
        System.out.println("##------------------------------------------------------------------");
    }
}