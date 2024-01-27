/**
 * Trong Duong
 * trong.duong@bellevuecollege.edu
 * 01/27/24
 * CS320
 * Assignment 1
 */
import java.util.InputMismatchException;
import java.util.Scanner;
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
    static void getUserInput() {
        try {
            System.out.println("##------------------------------------------------------------------");
            Scanner sc = new Scanner(System.in);
            System.out.println("These are the available stock list categories, please choose one");
            int userSelelection = sc.nextInt();

           //StockAnalyst.getStocksListCategories(userSelelection);
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number");
            getUserInput();
        }
    }
    /**
     * All URl constructors are deprecated now
     * https://docs.oracle.com/en/java/javase/20/docs/api/java.base/java/net/URL.html#constructor-deprecation
     * add throws exception
     */
    public static void main(String[] args) throws Exception {
        //getUserInput();
        StockAnalyst obj = new StockAnalyst();
        String webPage = obj.getUrlText(IStockAnalyst.WEB_URL);
        obj.getStocksListCategories(webPage);




    }
}