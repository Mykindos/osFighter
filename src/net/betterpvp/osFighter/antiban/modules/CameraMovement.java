package net.betterpvp.osFighter.antiban.modules;

import java.util.Optional;

import org.osbot.rs07.api.model.RS2Object;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.antiban.AntiBan;

public class CameraMovement extends AntiBan{

	public CameraMovement(Fighter i) {
		super(i, "Random Camera Movement", 2.0);
		// TODO Auto-generated constructor stub
	}
	
	private RS2Object last;

	@Override
	public void execute() throws InterruptedException {
		logAB();
		Optional<RS2Object> ob = getInstance().getObjects().getAll().stream().filter(o -> {
			if(last != null){
				return o.isVisible() && o.exists() && o.getPosition() != last.getPosition();
			}
			
			return o.isVisible() && o.exists();
			
		}).findAny();
		
		if(ob.isPresent()){
			last = ob.get();
			getInstance().getCamera().toEntity(last);
		}

	
	}

}
