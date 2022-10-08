package library.console_ui;


import library.services.AddBookService;

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
        addBookService.execute(author,title);
        System.out.println("Your book was added to list.");
    }
}
