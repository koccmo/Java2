package library.services;

import library.dataBase.DataBase;

public class PrintAllBookService {
    private DataBase dataBase;

    public PrintAllBookService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void execute() {
        dataBase.printBookList();
    }
}
