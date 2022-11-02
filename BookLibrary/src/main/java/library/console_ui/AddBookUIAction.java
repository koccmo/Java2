package library.console_ui;


import library.Book;
import library.core.request.AddBookRequest;
import library.core.response.AddBookResponse;
import library.core.response.CoreError;
import library.core.services.AddBookService;

import java.util.Scanner;

public class AddBookUIAction implements UIAction {
    private AddBookService addBookService;

    public AddBookUIAction(AddBookService addBookService) {
        this.addBookService = addBookService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter author of Book :");
        String author = scanner.nextLine();
        System.out.println("Enter title of Book :");
        String title = scanner.nextLine();
        AddBookRequest request = new AddBookRequest(author,title);
        AddBookResponse response = addBookService.execute(request);
        if (response.hasError()) {
            response.getErrors().forEach(coreError -> System.out.println(coreError.getField() + " "
            + coreError.getDescription()));
            System.out.println("Your book not been Added!");
        } else {
            System.out.println("New book id - " + response.getBook().getId());
            System.out.println("Book with id " + response.getBook().getId() + " been added!");
        }

    }
}
