package library.console_ui;

import library.dataBase.DataBase;
import library.services.FinishWorkService;

public class FinishWorkUIAction implements UIAction {
    private FinishWorkService finishWorkService;

    public FinishWorkUIAction(FinishWorkService finishWorkService) {
        this.finishWorkService = finishWorkService;
    }

    @Override
    public void execute() {
        finishWorkService.execute();
        System.out.println("Good Bye!");
    }
}
