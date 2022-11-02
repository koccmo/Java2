package library.console_ui;

import library.core.request.FindByAuthorRequest;
import library.core.response.FindByAuthorResponse;
import library.core.services.FindBookByAuthorService;

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
        FindByAuthorRequest findByAuthorRequest = new FindByAuthorRequest(authorForSearch);
        System.out.println("Books with same Authors start :");
        FindByAuthorResponse findByAuthorResponse = findBookBuyAuthor.execute(findByAuthorRequest);
        findByAuthorResponse.getBookList().forEach(System.out::println);
        System.out.println("Book list finish!");
    }
}
