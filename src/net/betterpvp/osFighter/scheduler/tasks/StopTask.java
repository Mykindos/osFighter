package net.betterpvp.osFighter.scheduler.tasks;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.scheduler.Task;

public class StopTask implements Task {

    @Override
    public void runTask(Fighter i) {
        i.log("Task: Script Stopped");
        i.stop();

    }

    @Override
    public boolean isComplete(Fighter i) {
        return false;
    }

    @Override
    public boolean validate(Fighter i) {
        return true;
    }

    @Override
    public String toString(){
        return "Stopping script";
    }


}
