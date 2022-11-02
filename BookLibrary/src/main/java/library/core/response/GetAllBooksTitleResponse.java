package library.core.response;


import java.util.List;

public class GetAllBooksTitleResponse extends CoreResponse{
    List<String> bookList;

    public GetAllBooksTitleResponse(List<String> bookList) {
        this.bookList = bookList;
    }

    public List<String> getBookList() {
        return bookList;
    }
}
