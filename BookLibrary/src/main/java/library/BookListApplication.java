package library;

import library.console_ui.*;
import library.dataBase.DataBase;
import library.dataBase.InMemoryDatabaseImpl;
import library.services.*;


import java.util.Scanner;


public class BookListApplication {

    private static DataBase dataBase = new InMemoryDatabaseImpl();
    private static AddBookService addBookService = new AddBookService(dataBase);
    private static AddBookUIAction addBookUIAction = new AddBookUIAction(addBookService);

    private static DeleteBookService deleteBookService = new DeleteBookService(dataBase);
    private static DeleteBookUIAction deleteBookUIAction = new DeleteBookUIAction(deleteBookService);

    private static FindBookByAuthorService findBookByAuthorService = new FindBookByAuthorService(dataBase);
    private static FindBookByAuthorUIAction findBookByAuthorUIAction = new FindBookByAuthorUIAction(findBookByAuthorService);

    private static FinishWorkService finishWorkService = new FinishWorkService(dataBase);
    private static FinishWorkUIAction finishWorkUIAction = new FinishWorkUIAction(finishWorkService);

    private static PrintAllBookService printAllBookService = new PrintAllBookService(dataBase);
    private static PrintAllBooksUIAction printAllBooksUIAction = new PrintAllBooksUIAction(printAllBookService);

    public static void main(String[] args) {

        while (true) {
            printMenu();
            int selectMenuField = getMenuFieldFromCustomer();
            executeSelectedMenuField(selectMenuField);
            System.out.println("");
        }
    }

    private static void executeSelectedMenuField(int selectMenuField) {
        switch (selectMenuField) {
            case 1: {
                addBookUIAction.execute();
                break;
            }
            case 2: {
                deleteBookUIAction.execute();
                break;
            }
            case 3: {
                printAllBooksUIAction.execute();
                break;
            }
            case 4: {
                findBookByAuthorUIAction.execute();
                break;
            }
            case 5:
                finishWorkUIAction.execute();
                break;
            default:
                System.out.println("Wrong number True again!");
        }
    }



    private static int getMenuFieldFromCustomer() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        int selectMenuField = Integer.parseInt(scanner.nextLine());
        return selectMenuField;
    }


    private static void printMenu() {
        System.out.println("Enter number for your Action!");
        System.out.println("1 : Add book");
        System.out.println("2 : Remove book");
        System.out.println("3 : Print all books");
        System.out.println("4 : Find buy Author");
        System.out.println("5 : Finish");

        System.out.println("");
    }




}
