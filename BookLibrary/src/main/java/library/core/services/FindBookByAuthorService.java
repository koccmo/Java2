package library.core.services;

import library.core.request.FindByAuthorRequest;
import library.core.response.FindByAuthorResponse;
import library.core.dataBase.DataBase;

public class FindBookByAuthorService {
    private DataBase dataBase;

    public FindBookByAuthorService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public FindByAuthorResponse execute(FindByAuthorRequest findByAuthorRequest) {
        FindByAuthorResponse findByAuthorResponse =
                new FindByAuthorResponse(dataBase.findByAuthor(findByAuthorRequest.getAuthor()));
        return findByAuthorResponse;
    }
}
