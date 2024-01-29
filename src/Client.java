/**
 * Trong Duong
 * trong.duong@bellevuecollege.edu
 * 01/27/24
 * CS320
 * Assignment 1
 */
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
public class Client {
    /**
     * TODO:
     * Develop the Client.java class, which should contain the main method.
     * The Client class will be used to:
     * test your application's behavior,
     * handle the interaction with the user,
     * and replicate the sample run example.
     * It should not include any business logic (implementation details of the application's core functionalities).
     */

    /**
     * Method to get User Input
     *
     * used recursion to keep running in case of wrong input.
     */
//    static void getUserInput() {
//        try {
//            System.out.println("##------------------------------------------------------------------");
//            Scanner sc = new Scanner(System.in);
//            System.out.println("These are the available stock list categories, please choose one");
//            int userSelelection = sc.nextInt();
//
//
//           //StockAnalyst.getStocksListCategories(userSelelection);
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
//        System.out.println("##------------------------------------------------------------------");
//            Scanner sc = new Scanner(System.in);
//            System.out.println("These are the available stock list categories, please choose one");
        StockAnalyst stockAnalyzer = new StockAnalyst();
        String webPage = stockAnalyzer.getUrlText(IStockAnalyst.WEB_URL);
//        List<String> categories = stockAnalyzer.getStocksListCategories(webPage);
//        int i =0;
//        for (String s: categories) {
//            System.out.println(i + ". " + s);
//            i++;
//        }
//        //make more robust
//        int userSelelection =  sc.nextInt();
        //System.out.println(0);
        Map<String, String> mapOfCategories = stockAnalyzer.getStocksListsInListCategory(webPage, "Popular List");
        for (Map.Entry<String, String> entry : mapOfCategories.entrySet()) {
            System.out.println(entry.getKey() + "-------------------" + entry.getValue());
        }






    }
}