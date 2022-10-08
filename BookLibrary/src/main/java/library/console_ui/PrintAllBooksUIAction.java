package library.console_ui;

import library.dataBase.DataBase;
import library.services.PrintAllBookService;

public class PrintAllBooksUIAction implements UIAction {
    private PrintAllBookService printAllBookService;

    public PrintAllBooksUIAction(PrintAllBookService printAllBookService) {
        this.printAllBookService = printAllBookService;
    }

    @Override
    public void execute() {
        System.out.println("Book list :");
        printAllBookService.execute();
        System.out.println("Book list End!");
        System.out.println("");
    }
}
