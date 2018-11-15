package net.betterpvp.osFighter.states;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.utilities.UtilSleep;
import org.osbot.rs07.api.map.Area;

public class DialogueHandler extends ScriptState{

    private Area stronghold = new Area(1850, 5172, 2408, 5329);
    @Override
    public boolean validate(Fighter i) throws InterruptedException {
        return i.getDialogues().isPendingOption() && stronghold.contains(i.myPlayer())
                || i.getDialogues().isPendingContinuation();
    }

    @Override
    public void run(Fighter i) throws InterruptedException {
        if(i.getDialogues().isPendingContinuation()){
            i.getDialogues().clickContinue();
            return;
        }
        i.getDialogues().selectOption("Don't give them the information",
                "Secure my device and reset my password",
                "Inform Jagex by emailing reportphishing@jagex.com",
                "Not even close friends.",
                "Through account settings on oldschool.runescape.com",
                 "Don't give out your password to anyone.",
                "Use the Account Recovery System.",
                "Report the incident",
                "Don't share your information and report the player.",

                "'verified' mark",
                "Nobody",
                "Don't tell them anything and click the 'Report Abuse' button.",
                "Talk to any banker",
                "Only on the Old School RuneScape website.",
                "Me.",
                "No, you should never allow anyone to level your account.",
                "Don't give them my password.",
                "Set up 2 step authentication",
                "The birthday of a famous person or event",
                "Don't type in my password backwards and report the player.",
                "advice given",
                "report the player who messaged you.",
                "Decline the offer and report that player.",
                "No way! You'll just take my gold for your own! Reported!",
                "No.",
                "reportphishing@jagex.com",
                "Memorable.",
                "Politely tell them no then use the report abuse button.",
                "Nowhere.",
                "No, it might steal my password.",
                "To recover my account if i don't remember my password."






                );

        UtilSleep.sleep(i, 250, 500);
    }
}
