package library.console_ui;

import library.core.request.DeleteBookRequest;
import library.core.response.DeleteBookResponse;
import library.core.services.DeleteBookService;

import java.util.List;
import java.util.Scanner;

public class DeleteBookUIAction implements UIAction{
    private DeleteBookService deleteBookService;

    public DeleteBookUIAction (DeleteBookService deleteBookService) {
        this.deleteBookService = deleteBookService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID of Book :");
        var id = scanner.nextLine();
        DeleteBookRequest deleteBookRequest = new DeleteBookRequest(id);
        DeleteBookResponse response = deleteBookService.execute(deleteBookRequest);
        if(response.hasError()) {
            response.getErrors().forEach(coreError -> System.out.println(coreError.getField() + " "
                    + coreError.getDescription()));
        } else {
            if(response.isBookDeleted()) {
                System.out.println("Book was Deleted!");
            } else {
                System.out.println("Book not Deleted!");
            }
        }

    }
}
