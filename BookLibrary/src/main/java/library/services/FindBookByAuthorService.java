package library.services;

import library.dataBase.DataBase;

public class FindBookByAuthorService {
    private DataBase dataBase;

    public FindBookByAuthorService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void execute(String author) {
        dataBase.findBookBuyAuthor(author);
    }
}
