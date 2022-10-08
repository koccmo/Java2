package library.services;

import library.dataBase.DataBase;

public class FinishWorkService {
    private DataBase dataBase;

    public FinishWorkService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void execute() {
        dataBase.finishWork();
    }
}
