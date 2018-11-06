package net.betterpvp.osFighter.managers;

import java.awt.event.KeyEvent;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.input.keyboard.BotKeyListener;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.utilities.UtilTime;

public class KeyListener extends BotKeyListener{

	private Fighter i;

	public KeyListener(Fighter i) {
		this.i = i;
	}

	private long lastExecute = System.currentTimeMillis();

	@Override
	public void checkKeyEvent(KeyEvent e) {

		if(UtilTime.elapsed(lastExecute, 50)) {
			if(e.getKeyCode() == KeyEvent.VK_F1) {
				i.log("Area Definition Started!");
				i.log("To define your area, hold shift and left click the top left and \n\t\t\tbottom right corners of your fight area.");
				i.log("Advanced instructions available in the forum post");
				i.setDefiningArea(true);
				i.getSessionData().getCombatAreaPositions().clear();
				i.getSessionData().setCombatArea(new Area(0,0,0,0));
			}else if(e.getKeyCode() == KeyEvent.VK_F2) {
				i.setDefiningArea(false);
				i.log("Area Definition Stopped!");
			}else if(e.getKeyCode() == KeyEvent.VK_F3) {
				i.getSessionData().setSafeSpot(i.myPosition());
				i.log("Safe Spot set to your current position!");
			}
			lastExecute = System.currentTimeMillis();
		}
	}

}
