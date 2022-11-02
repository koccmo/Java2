package library;

import library.console_ui.*;
import library.core.dataBase.DataBase;
import library.core.dataBase.InMemoryDatabaseImpl;
import library.core.services.*;


import java.util.Scanner;


public class BookListApplication {

    private static DataBase dataBase = new InMemoryDatabaseImpl();

    private static AddBookValidator addBookValidator = new AddBookValidator();
    private static AddBookService addBookService = new AddBookService(dataBase, addBookValidator);
    private static AddBookUIAction addBookUIAction = new AddBookUIAction(addBookService);

    private static DeleteBookValidation validation = new DeleteBookValidation();
    private static DeleteBookService deleteBookService = new DeleteBookService(dataBase, validation);
    private static DeleteBookUIAction deleteBookUIAction = new DeleteBookUIAction(deleteBookService);

    private static FindBookByAuthorService findBookByAuthorService = new FindBookByAuthorService(dataBase);
    private static FindBookByAuthorUIAction findBookByAuthorUIAction = new FindBookByAuthorUIAction(findBookByAuthorService);

    private static FinishWorkService finishWorkService = new FinishWorkService(dataBase);
    private static FinishWorkUIAction finishWorkUIAction = new FinishWorkUIAction(finishWorkService);

    private static PrintAllBookService printAllBookService = new PrintAllBookService(dataBase);
    private static PrintAllBooksUIAction printAllBooksUIAction = new PrintAllBooksUIAction(printAllBookService);

    private static SearchBooksValidator searchBooksValidator = new SearchBooksValidator();
    private static SearchBooksService searchBooksService = new SearchBooksService(dataBase,searchBooksValidator);
    private static SearchBooksUIAction searchBooksUIAction = new SearchBooksUIAction(searchBooksService);

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
                searchBooksUIAction.execute();
                break;
            case 6:
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
        System.out.println("5 : Search Book");
        System.out.println("6 : Finish");

        System.out.println("");
    }




}
