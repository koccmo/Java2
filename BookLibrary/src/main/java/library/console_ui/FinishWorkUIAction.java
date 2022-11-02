package library.console_ui;

import library.core.request.ExitRequest;
import library.core.services.FinishWorkService;

public class FinishWorkUIAction implements UIAction {
    private FinishWorkService finishWorkService;

    public FinishWorkUIAction(FinishWorkService finishWorkService) {
        this.finishWorkService = finishWorkService;
    }

    @Override
    public void execute() {
        ExitRequest exitRequest= new ExitRequest();
        finishWorkService.execute(exitRequest);
        System.out.println("Good Bye!");
    }
}
