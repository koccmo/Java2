package library.core.services;

import library.core.response.GetAllBooksTitleResponse;
import library.core.request.GetAllBooksTitleRequest;
import library.core.dataBase.DataBase;

import java.util.List;

public class PrintAllBookService {
    private DataBase dataBase;

    public PrintAllBookService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public GetAllBooksTitleResponse execute(GetAllBooksTitleRequest printAllBooksRequest) {
        dataBase.getAllBooksTitle();
        List<String> titleList = dataBase.getAllBooksTitle();
        GetAllBooksTitleResponse printAllBookResponse = new GetAllBooksTitleResponse(titleList);
        return printAllBookResponse;
    }
}
