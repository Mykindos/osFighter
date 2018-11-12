package net.betterpvp.osFighter.states;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.data.SessionData;
import net.betterpvp.osFighter.states.ScriptState;
import net.betterpvp.osFighter.utilities.UtilTime;
import net.betterpvp.osFighter.utilities.UtilWalking;

public class ResetArea extends ScriptState {
    @Override
    public boolean validate(Fighter i) throws InterruptedException {
        return i.getSessionData().getResetPosition() != null
            && UtilTime.elapsed(i.getSessionData().getLastAttacked(), 10000);
    }

    @Override
    public void run(Fighter i) throws InterruptedException {
            SessionData data = i.getSessionData();
        if(data.getResetPosition().distance(i.myPlayer()) > 5){
            UtilWalking.webWalk(i, UtilWalking.getArea(data.getResetPosition(), 3), null, false);
        }else{
            data.setLastAttacked(System.currentTimeMillis());
        }

    }
}
