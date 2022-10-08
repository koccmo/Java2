package library.services;

import library.dataBase.DataBase;

public class DeleteBookService {
    private DataBase dataBase;

    public DeleteBookService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void execute(Long id) {
        dataBase.deleteBook(id);
    }
}
