package library.console_ui;

import library.dataBase.DataBase;
import library.services.FindBookByAuthorService;

import java.util.Scanner;

public class FindBookByAuthorUIAction implements UIAction {
    private FindBookByAuthorService findBookBuyAuthor;

    public FindBookByAuthorUIAction(FindBookByAuthorService findBookBuyAuthor) {
        this.findBookBuyAuthor = findBookBuyAuthor;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book author for search :");
        String authorForSearch = scanner.nextLine();
        System.out.println("Books with same Authors start :");
        findBookBuyAuthor.execute(authorForSearch);
        System.out.println("Book list finish!");
    }
}
