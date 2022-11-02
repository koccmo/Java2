package library.core.services;

import library.core.dataBase.DataBase;
import library.core.request.ExitRequest;
import library.core.response.ExitResponse;

public class FinishWorkService {
    private DataBase dataBase;

    public FinishWorkService(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public ExitResponse execute(ExitRequest exitRequest) {
        dataBase.finishWork();
        ExitResponse exitResponse = new ExitResponse();
        return exitResponse;
    }
}
