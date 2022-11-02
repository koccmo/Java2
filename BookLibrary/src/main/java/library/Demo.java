package library;

import library.core.dataBase.DataBase;
import library.core.dataBase.InMemoryDatabaseImpl;
import library.core.request.SearchBooksRequest;
import library.core.response.CoreError;
import library.core.services.SearchBooksValidator;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        DataBase dataBase = new InMemoryDatabaseImpl();
        Book book = new Book("James", "Title");
        dataBase.addBook(book);
        List<Book> bookList = dataBase.findByTitle("Garden");
        System.out.println("bookList = " + bookList);
        System.out.println(bookList.isEmpty());
    }
}
