package library.core.services;


import library.core.request.Ordering;
import library.core.request.Paging;
import library.core.request.SearchBooksRequest;
import library.core.response.CoreError;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchBooksValidator {


    public List<CoreError> validate(SearchBooksRequest request) {
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(fieldValidate(request));
        if (request.getOrdering() != null) {
            validateOrderBy(request.getOrdering()).ifPresent(errors::add);
            validationOrderDirection(request.getOrdering()).ifPresent(errors::add);
            validationRequiredOrderBy(request.getOrdering()).ifPresent(errors::add);
            validationRequiredOrderDirection(request.getOrdering()).ifPresent(errors::add);
        }
        if (request.getPaging() != null) {
            validationPageSizeEnteredPageNumberNo(request.getPaging()).ifPresent(errors::add);
            validationPageNumberEnteredPageSizeNo(request.getPaging()).ifPresent(errors::add);
            validationPageNumber(request.getPaging()).ifPresent(errors::add);
            validationPageSize(request.getPaging()).ifPresent(errors::add);
        }
        return errors;
    }

    private List<CoreError> fieldValidate (SearchBooksRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getAuthor()) && isEmpty(request.getTitle())) {
            errors.add(new CoreError("Author", "Field Author Must be Fill UP!"));
            errors.add(new CoreError("Title", "Field Title Must Be Fill UP!"));
        }
        return errors;
    }

    private Optional<CoreError> validateOrderBy(Ordering order) {
        return (order.getOrderBy() != null
                && !(order.getOrderBy().equals("Author") || order.getOrderBy().equals("Title")))
                ? Optional.of(new CoreError("OrderBy", "Must be equal Author or Title!"))
                : Optional.empty();
    }

    private Optional<CoreError> validationOrderDirection(Ordering order) {
        return (order.getOrderDirection() != null
                && !(order.getOrderDirection().equals("ASCENDING") || order.getOrderDirection().equals("DESCENDING")))
                ? Optional.of(new CoreError("OrderDirection", "Must be choosing one of Direction!"))
                : Optional.empty();
    }

    private Optional<CoreError> validationRequiredOrderBy(Ordering order) {
        return (order.getOrderDirection() != null && order.getOrderBy() == null)
                ? Optional.of(new CoreError("OrderBy", "Must be fill up!"))
                : Optional.empty();
    }

    private Optional<CoreError> validationRequiredOrderDirection(Ordering order) {
        return (order.getOrderBy() != null && order.getOrderDirection() == null)
                ? Optional.of(new CoreError("OrderDirection", "Must be fill up!"))
                : Optional.empty();
    }

    private Optional<CoreError> validationPageNumberEnteredPageSizeNo(Paging paging) {
        return (paging.getPageNumber() != null && paging.getPageSize() == null)
                ? Optional.of(new CoreError("PageSize", "Must be fill UP!"))
                : Optional.empty();
    }

    private Optional<CoreError> validationPageSizeEnteredPageNumberNo(Paging paging) {
        return (paging.getPageSize() != null && paging.getPageNumber() == null)
                ? Optional.of(new CoreError("PageNumber", "Must be fill UP!"))
                : Optional.empty();
    }

    private Optional<CoreError> validationPageNumber(Paging paging) {
        return (paging.getPageNumber() != null && paging.getPageNumber() <= 0)
                ? Optional.of(new CoreError("PageNumber", "Must be over 0!"))
                : Optional.empty();
    }

    private Optional<CoreError> validationPageSize(Paging paging) {
        return (paging.getPageSize() != null && paging.getPageSize() <= 0)
                ? Optional.of(new CoreError("PageSize", "Must be over 0!"))
                : Optional.empty();
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
