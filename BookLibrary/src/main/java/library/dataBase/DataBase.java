package library.dataBase;

import library.Book;

public interface DataBase {
    void addBook(Book book);
    void deleteBook(Long id);
    void printBookList();
    void findBookBuyAuthor(String author);
    Book findById(Long id);
    void finishWork();
}
