package library.core.services;

import library.core.request.AddBookRequest;
import library.core.response.AddBookResponse;
import library.Book;
import library.core.dataBase.DataBase;
import library.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;

public class AddBookService {
    private DataBase dataBase;
    private AddBookValidator addBookValidator;

    public AddBookService(DataBase dataBase, AddBookValidator addBookValidator) {
        this.dataBase = dataBase;
        this.addBookValidator = addBookValidator;
    }

    public AddBookResponse execute (AddBookRequest addBookRequest) {
        List<CoreError> errors = addBookValidator.validate(addBookRequest, dataBase);
        if (errors.isEmpty()) {
            Book book = new Book(addBookRequest.getAuthor(), addBookRequest.getTitle());
            dataBase.addBook(book);
            AddBookResponse addBookResponse = new AddBookResponse(book);
            return addBookResponse;
        } else {
            AddBookResponse addBookResponse = new AddBookResponse(errors);
            return  addBookResponse;
        }
    }
}
