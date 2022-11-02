package library.core.services;

import library.core.request.Ordering;
import library.core.request.Paging;
import library.core.request.SearchBooksRequest;
import library.core.response.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchBooksValidatorTest {
    @Test
    void fieldTitleEmpty() {
        SearchBooksRequest request = new SearchBooksRequest("James", "");
        SearchBooksValidator validator = new SearchBooksValidator();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void fieldAuthorEmpty() {
        SearchBooksRequest request = new SearchBooksRequest("", "Garden");
        SearchBooksValidator validator = new SearchBooksValidator();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void fieldTitleAndAuthorEmpty() {
        SearchBooksRequest request = new SearchBooksRequest("", "");
        SearchBooksValidator validator = new SearchBooksValidator();
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("Author", errors.get(0).getField());
        assertEquals("Field Author Must be Fill UP!", errors.get(0).getDescription());
        assertEquals("Title", errors.get(1).getField());
        assertEquals("Field Title Must Be Fill UP!", errors.get(1).getDescription());
    }

    @Test
    void orderByAuthorButAuthorIsEmpty() {
        Ordering ordering = new Ordering("Author", "ASCENDING");
        SearchBooksRequest request = new SearchBooksRequest("", "Garden", ordering);
        SearchBooksValidator validator = new SearchBooksValidator();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void orderByTitleButTitleEmpty() {
        Ordering ordering = new Ordering("Title", "ASCENDING");
        SearchBooksRequest request = new SearchBooksRequest("James", "", ordering);
        SearchBooksValidator validator = new SearchBooksValidator();
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }
    @Test
    void orderByNotEqualsTitleOrAuthor() {
        Ordering ordering = new Ordering("Year", "ASCENDING");
        SearchBooksRequest request = new SearchBooksRequest("James", "Garden", ordering);
        SearchBooksValidator validator = new SearchBooksValidator();
        List<CoreError> errors = validator.validate(request);
        CoreError expectedError = new CoreError("OrderBy", "Must be equal Author or Title!");
        assertFalse(errors.isEmpty());
        assertEquals(expectedError, errors.get(0));
    }
    @Test
    void orderDirectionWrongSpecification() {
        Ordering ordering = new Ordering("Title", "Entry");
        SearchBooksRequest request = new SearchBooksRequest("James", "Garden", ordering);
        SearchBooksValidator validator = new SearchBooksValidator();
        List<CoreError> errors = validator.validate(request);
        CoreError expectedError = new CoreError("OrderDirection", "Must be choosing one of Direction!");
        assertFalse(errors.isEmpty());
        assertEquals(expectedError, errors.get(0));
    }
    @Test
    void orderByEmptyOrderDirectionCorrect() {
        Ordering ordering = new Ordering(null, "DESCENDING");
        SearchBooksRequest request = new SearchBooksRequest("James", "Garden", ordering);
        SearchBooksValidator validator = new SearchBooksValidator();
        List<CoreError> errors = validator.validate(request);
        CoreError expectedError = new CoreError("OrderBy", "Must be fill up!");
        assertFalse(errors.isEmpty());
        assertEquals(expectedError, errors.get(0));
    }

    @Test
    void orderDirectionEmptyOrderByCorrect() {
        Ordering ordering = new Ordering("Title", null);
        SearchBooksRequest request = new SearchBooksRequest("James", "Garden", ordering);
        SearchBooksValidator validator = new SearchBooksValidator();
        List<CoreError> errors = validator.validate(request);
        CoreError expectedError = new CoreError("OrderDirection", "Must be fill up!");
        assertFalse(errors.isEmpty());
        assertEquals(expectedError, errors.get(0));
    }
    @Test
    void orderByAndOrderDirectionNotCorrectFillUp() {
        Ordering ordering = new Ordering("Entry", "Down");
        SearchBooksRequest request = new SearchBooksRequest("James", "Garden", ordering);
        SearchBooksValidator validator = new SearchBooksValidator();
        List<CoreError> errors = validator.validate(request);
        CoreError orderByExpectedError = new CoreError("OrderBy",
                "Must be equal Author or Title!");
        CoreError orderDirectionExpectedError = new CoreError("OrderDirection",
                "Must be choosing one of Direction!");
        assertFalse(errors.isEmpty());
        assertEquals(orderByExpectedError, errors.get(0));
        assertEquals(orderDirectionExpectedError, errors.get(1));
    }

    @Test
    void pageNumberIsNullPageSizeIsOne() {
        Ordering ordering = new Ordering("Title", "ASCENDING");
        Paging paging = new Paging(null, 1);
        SearchBooksRequest request = new SearchBooksRequest("A", "", ordering, paging);
        SearchBooksValidator validator = new SearchBooksValidator();
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("PageNumber", errors.get(0).getField());
        assertEquals("Must be fill UP!", errors.get(0).getDescription());
        assertEquals(1, errors.size());
    }

    @Test
    void pageSizeNullPageNumberOne() {
        Ordering ordering = new Ordering("Title", "ASCENDING");
        SearchBooksValidator validator = new SearchBooksValidator();
        Paging paging = new Paging(1, null);
        SearchBooksRequest request = new SearchBooksRequest("A", "", ordering, paging);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("PageSize", errors.get(0).getField());
        assertEquals("Must be fill UP!", errors.get(0).getDescription());
        assertEquals(1, errors.size());
    }

    @Test
    void pageNumberNullPageSizeNull() {
        Ordering ordering = new Ordering("Title", "ASCENDING");
        SearchBooksValidator validator = new SearchBooksValidator();
        Paging paging = new Paging(null, null);
        SearchBooksRequest request = new SearchBooksRequest("A", "", ordering, paging);
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void pageNumberCorrectPageSizeUnCorrect() {
        Ordering ordering = new Ordering("Title", "ASCENDING");
        SearchBooksValidator validator = new SearchBooksValidator();
        Paging paging = new Paging(1, 0);
        SearchBooksRequest request = new SearchBooksRequest("A", "", ordering, paging);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("PageSize", errors.get(0).getField());
        assertEquals("Must be over 0!", errors.get(0).getDescription());
    }

    @Test
    void pageSizeCorrectPageNumberUnCorrect() {
        Ordering ordering = new Ordering("Title", "ASCENDING");
        SearchBooksValidator validator = new SearchBooksValidator();
        Paging paging = new Paging(0, 1);
        SearchBooksRequest request = new SearchBooksRequest("A", "", ordering, paging);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("PageNumber", errors.get(0).getField());
        assertEquals("Must be over 0!", errors.get(0).getDescription());
    }

    @Test
    void unCorrectPageNumberAndPageSize() {
        Ordering ordering = new Ordering("Title", "ASCENDING");
        SearchBooksValidator validator = new SearchBooksValidator();
        Paging paging = new Paging(0, 0);
        SearchBooksRequest request = new SearchBooksRequest("A", "", ordering, paging);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("PageNumber", errors.get(0).getField());
        assertEquals("Must be over 0!", errors.get(0).getDescription());
        assertEquals("PageSize", errors.get(1).getField());
        assertEquals("Must be over 0!", errors.get(1).getDescription());
    }
}