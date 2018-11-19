package net.betterpvp.osFighter.scheduler.tasks;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.scheduler.Task;

public class BankTask implements Task {

    boolean ran = false;
    @Override
    public void runTask(Fighter i) {
        i.getSessionData().setShouldBankNow(true);
        ran = true;
    }

    @Override
    public boolean isComplete(Fighter i) {
        return ran;
    }

    @Override
    public boolean validate(Fighter i) {
        return true;
    }

    @Override
    public String toString(){
        return "Bank";
    }
}
