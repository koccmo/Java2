package library.core.services;

import library.Book;
import library.core.dataBase.DataBase;
import library.core.request.AddBookRequest;
import library.core.request.DeleteBookRequest;
import library.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeleteBookValidation {
    public List<CoreError> validate (DeleteBookRequest request , DataBase dataBase) {
        List<CoreError> errors = new ArrayList<>();
        idHasOnlyNumbersValidate(request).ifPresent(errors::add);
        idNotEmptyValidate(request).ifPresent(errors::add);
        bookInDataBase(request, dataBase).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> idHasOnlyNumbersValidate(DeleteBookRequest request) {
        return (request.getId().matches("[0-9]+"))
                ? Optional.empty()
                : Optional.of(new CoreError("Id", "Id must has only Numbers"));
    }

    private Optional<CoreError> idNotEmptyValidate(DeleteBookRequest  request) {
        return (request.getId() == null || request.getId().isEmpty())
                ? Optional.of(new CoreError("ID", "Must be fill UP!"))
                : Optional.empty();
    }

    private Optional<CoreError> bookInDataBase(DeleteBookRequest request, DataBase dataBase) {
        Long id = Long.parseLong(request.getId());
        boolean hasBookInLibrary = dataBase.hasBookInLibraryCheckById(id);
        return (!hasBookInLibrary)
                ? Optional.of(new CoreError("Id", "Not found book with this ID!"))
                : Optional.empty();
    }


}
