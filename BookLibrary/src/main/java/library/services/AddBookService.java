package library.services;

import library.Book;
import library.dataBase.DataBase;

public class AddBookService {
    private DataBase dataBase;

    public AddBookService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void execute (String author, String title) {
        Book book = new Book(author, title);
        dataBase.addBook(book);
    }
}
