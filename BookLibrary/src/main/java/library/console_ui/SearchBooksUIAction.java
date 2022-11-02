package library.console_ui;

import library.core.request.Ordering;
import library.core.request.Paging;
import library.core.request.SearchBooksRequest;
import library.core.response.SearchBooksResponse;
import library.core.services.SearchBooksService;

import java.util.Scanner;

public class SearchBooksUIAction implements UIAction{
    private SearchBooksService searchBooksService;

    public SearchBooksUIAction(SearchBooksService searchBooksService) {
        this.searchBooksService = searchBooksService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Book Author for Search - ");
        String author = scanner.nextLine();
        System.out.println("Enter Book Title for Search - ");
        String title = scanner.nextLine();
        System.out.println("Enter Book Author or Title - ");
        String orderBy = scanner.nextLine();
        System.out.println("Enter ASCENDING or DESCENDING ");
        String orderDirection = scanner.nextLine();
        System.out.println("Enter Page Number");
        Integer pageNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Page Size");
        Integer pageSize = Integer.parseInt(scanner.nextLine());
        Ordering ordering = new Ordering(orderBy, orderDirection);
        Paging paging = new Paging(pageNumber, pageSize);
        SearchBooksRequest request = new SearchBooksRequest(author, title, ordering, paging);
        SearchBooksResponse response = searchBooksService.execute(request);
        if (response.hasError()) {
            response.getErrors().forEach(coreError -> System.out.println("Error: " + coreError.getField()
                    + " " + coreError.getDescription()));
        } else {
            response.getFoundedBookList().forEach(System.out :: println);
        }
    }
}
