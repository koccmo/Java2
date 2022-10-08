package library.console_ui;

import library.dataBase.DataBase;
import library.services.DeleteBookService;

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
        var id = scanner.nextLong();
        deleteBookService.execute(id);
        System.out.println("Your book was removed to list.");
    }
}
