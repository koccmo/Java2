package library.dataBase;

import library.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryDatabaseImpl implements DataBase{
    private Long idNumber = 1L;
    private List<Book> bookLibrary = new ArrayList<>();

    @Override
    public void addBook(Book book) {
        book.setId(idNumber);
        bookLibrary.add(book);
        idNumber++;
    }

    @Override
    public void deleteBook(Long id) {
        bookLibrary.remove(findById(id));
    }

    @Override
    public void printBookList() {
        for (Book book : bookLibrary) {
            System.out.println(book.getTitle());
        }
    }

    @Override
    public void findBookBuyAuthor(String author) {
        bookLibrary.stream()
                .filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    @Override
    public Book findById(Long id) {
        return bookLibrary.stream()
                .filter(book -> book.getId().equals(id))
                .findAny().get();
    }

    @Override
    public void finishWork() {
        System.exit(0);
    }
}
