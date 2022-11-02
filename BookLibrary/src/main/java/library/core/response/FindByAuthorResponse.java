package library.core.response;

import library.Book;

import java.util.List;

public class FindByAuthorResponse extends CoreResponse{
    private List<Book> bookList;
    

    public FindByAuthorResponse(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> getBookList() {
        return bookList;
    }
}
