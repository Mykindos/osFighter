package net.betterpvp.osFighter.antiban;

import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.utility.ConditionalSleep;

import net.betterpvp.osFighter.Fighter;

public abstract class AntiBan{
	
	private String name;
	private double chance;
	private Fighter i;
	private boolean enabled;
	
	public AntiBan(Fighter i, String name, double chance){
		this.name = name;
		this.chance = chance;
		this.i = i;
	}
	

	public abstract void execute() throws InterruptedException;
	
	
	public void setEnabled(boolean bool){
		this.enabled = bool;
	}
	
	public boolean isEnabled(){
		return enabled;
	}
	
	public String getName(){
		return name;
	}
	
	public double getChance(){
		return chance;
	}
	
	public Fighter getInstance(){
		return i;
	}
	
	public void openInventory(){
		getInstance().getTabs().open(Tab.INVENTORY);
		new ConditionalSleep(1000){

			@Override
			public boolean condition() throws InterruptedException {
				// TODO Auto-generated method stub
				return getInstance().getTabs().getOpen().equals(Tab.INVENTORY);
			}
			
		}.sleep();
	}
	
	public void logAB(){
		getInstance().log("AntiBan: " + getName());
	}

}
