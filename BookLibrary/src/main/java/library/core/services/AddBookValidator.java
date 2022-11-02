package library.core.services;

import library.Book;
import library.core.dataBase.DataBase;
import library.core.request.AddBookRequest;
import library.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddBookValidator {
    public List<CoreError> validate(AddBookRequest request, DataBase dataBase) {
        List<CoreError> errors = new ArrayList<>();
        validateAuthor(request).ifPresent(errors::add);
        validateTitle(request).ifPresent(errors::add);
        validateBookInLibrary(request, dataBase).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateAuthor(AddBookRequest request) {
        return (request.getAuthor() == null || request.getAuthor().isEmpty())
                ? Optional.of(new CoreError("Author", "Must be fill UP!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateTitle(AddBookRequest request) {
        return (request.getTitle() == null || request.getTitle().isEmpty())
                ? Optional.of(new CoreError("Title", "Must be fill UP!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateBookInLibrary(AddBookRequest request, DataBase dataBase) {
        Book book = new Book(request.getAuthor(), request.getTitle());
        return (dataBase.hasBookInLibrary(book))
                ? Optional.of(new CoreError("Book", "Can`t add to library, it already in!"))
                : Optional.empty();
    }



}


