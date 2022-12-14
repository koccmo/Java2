package library.core.services;

import library.Book;
import library.core.dataBase.DataBase;
import library.core.request.Ordering;
import library.core.request.Paging;
import library.core.request.SearchBooksRequest;
import library.core.response.CoreError;
import library.core.response.SearchBooksResponse;

import java.util.*;
import java.util.stream.Collectors;

public class SearchBooksService {
    private DataBase dataBase;
    private SearchBooksValidator searchBooksValidator;

    public SearchBooksService(DataBase dataBase, SearchBooksValidator searchBooksValidator) {
        this.dataBase = dataBase;
        this.searchBooksValidator = searchBooksValidator;
    }

    public SearchBooksResponse execute(SearchBooksRequest request) {
        List<CoreError> errors = searchBooksValidator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchBooksResponse(errors, null);
        }

        List<Book> books = search(request);
        books = order(books, request.getOrdering());
        books = pagination(books, request.getPaging());
        return new SearchBooksResponse(null, books);
    }

    private List<Book> search(SearchBooksRequest request) {
        List<Book> search = null;
        if (request.getAuthor().isEmpty() && !request.getTitle().isEmpty()) {
            search = dataBase.findByTitle(request.getTitle());
        }
        if (!request.getAuthor().isEmpty() && request.getTitle().isEmpty()) {
            search = dataBase.findByAuthor(request.getAuthor());
        }
        if (!request.getAuthor().isEmpty() && !request.getTitle().isEmpty()) {
            search = dataBase.findByAuthorAndTitle(request.getAuthor(),
                    request.getTitle());
        }
        return search;
    }

    private List<Book> order(List<Book> bookList, Ordering order) {
        if(order != null) {
            Comparator<Book> comparator = order.getOrderBy().equals("Author")
                    ? Comparator.comparing(Book::getAuthor)
                    : Comparator.comparing(Book::getTitle);
            if(order.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return bookList.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return bookList;
        }
    }

    private List<Book> pagination (List<Book> bookList, Paging paging) {
        if (paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
             return bookList.stream()
            .skip(skip)
            .limit(paging.getPageSize())
            .collect(Collectors.toList());
           } else return bookList;
    }

//    private List<Book> order(List<Book> bookList, SearchBooksRequest request) {
//        List<Book> sortedList = null;
//        if (request.getOrdering().getOrderBy().equals("Author")
//                && request.getOrdering().getOrderDirection().equals("ASCENDING")) {
//            sortedList = bookList.stream()
//                    .sorted(Comparator.comparing(Book::getAuthor))
//                    .collect(Collectors.toList());
//        }
//        if (request.getOrdering().getOrderBy().equals("Author")
//                && request.getOrdering().getOrderDirection().equals("DESCENDING")) {
//            sortedList = bookList.stream()
//                    .sorted(Comparator.comparing(Book::getAuthor).reversed())
//                    .collect(Collectors.toList());
//        }
//        if (request.getOrdering().getOrderBy().equals("Title")
//                && request.getOrdering().getOrderDirection().equals("ASCENDING")) {
//            sortedList = bookList.stream()
//                    .sorted(Comparator.comparing(Book::getTitle))
//                    .collect(Collectors.toList());
//        }
//        if (request.getOrdering().getOrderBy().equals("Title")
//                && request.getOrdering().getOrderDirection().equals("DESCENDING")) {
//            sortedList = bookList.stream()
//                    .sorted(Comparator.comparing(Book::getTitle).reversed())
//                    .collect(Collectors.toList());
//        }
//        return sortedList;
//    }
}
