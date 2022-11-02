package library.core.services;

import library.Book;
import library.core.request.DeleteBookRequest;
import library.core.response.CoreError;
import library.core.response.DeleteBookResponse;
import library.core.dataBase.DataBase;

import java.util.List;

public class DeleteBookService {
    private DataBase dataBase;
    private DeleteBookValidation deleteBookValidation;

    public DeleteBookService(DataBase dataBase, DeleteBookValidation deleteBookValidation) {
        this.dataBase = dataBase;
        this.deleteBookValidation = deleteBookValidation;
    }

    public DeleteBookResponse execute(DeleteBookRequest deleteBookRequest) {
        List<CoreError> errors = deleteBookValidation.validate(deleteBookRequest, dataBase);
        if(errors.isEmpty()) {
            Long bookId = Long.parseLong(deleteBookRequest.getId());
            dataBase.deleteBook(bookId);
            DeleteBookResponse deleteBookResponse = new DeleteBookResponse(true);
            return deleteBookResponse;
        } else {
            DeleteBookResponse deleteBookResponse = new DeleteBookResponse(errors);
            return deleteBookResponse;
        }
    }
}
