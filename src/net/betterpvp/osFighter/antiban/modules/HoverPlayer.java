package net.betterpvp.osFighter.antiban.modules;

import org.osbot.rs07.api.model.Player;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.antiban.AntiBan;

public class HoverPlayer extends AntiBan{

	public HoverPlayer(Fighter i) {
		super(i, "Hover nearby players", 2.0);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void execute() throws InterruptedException {
		logAB();
		
		@SuppressWarnings("unchecked")
		Player d = getInstance().getPlayers().closest(p ->  p.getPosition().distance(getInstance().myPosition()) < 20);
		if(d != null){
			d.hover();
			
		}
		
		
	}

}
