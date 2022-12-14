package library.core.dataBase;

import library.Book;

import java.util.List;

public interface DataBase {
    void addBook(Book book);
    void deleteBook(Long id);
    void finishWork();

    List<String> getAllBooksTitle();
    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);
    List<Book> findByAuthorAndTitle(String author, String title);


    boolean hasBookInLibrary(Book book);
    boolean hasBookInLibraryCheckById(Long id);


}
