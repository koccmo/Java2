package library.core.response;

import library.Book;

import java.util.List;

public class AddBookResponse extends CoreResponse{
    private Book book;

    public AddBookResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddBookResponse(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }


}
