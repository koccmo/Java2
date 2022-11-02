package library.console_ui;

import library.core.request.GetAllBooksTitleRequest;
import library.core.response.GetAllBooksTitleResponse;
import library.core.services.PrintAllBookService;


public class PrintAllBooksUIAction implements UIAction {
    private PrintAllBookService printAllBookService;

    public PrintAllBooksUIAction(PrintAllBookService printAllBookService) {
        this.printAllBookService = printAllBookService;
    }

    @Override
    public void execute() {
        System.out.println("Book list :");
        GetAllBooksTitleRequest getAllBookTitle = new GetAllBooksTitleRequest();
        GetAllBooksTitleResponse getAllBooksTitleResponse = printAllBookService.execute(getAllBookTitle);
        getAllBooksTitleResponse.getBookList().forEach(System.out::println);
        System.out.println("Book list End!");
        System.out.println("");
    }
}
