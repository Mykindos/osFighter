package net.betterpvp.osFighter.data;

import java.util.ArrayList;
import java.util.List;

import org.osbot.rs07.script.Script;

public class SessionData {
	

	private boolean banking;

	private int maxLoopTime, minLoopTime;

	private Bank bank;


	private long lastAntiban;
	private long antibanGap;
	private List<FighterNPC> currentTargets = new ArrayList<>();


	
	public SessionData(){
		
	}
	
	public List<FighterNPC> getCurrentTargets(){
		return currentTargets;
	}
	
	public void setBank(Bank b){
		this.bank = b;
	}
	
	public Bank getBank(){
		return bank;
	}
	
	
	
	public void setBanking(boolean bool){
		this.banking = bool;
	}
	
	public boolean isBanking(){
		return banking;
	}
	
	
	public void setMaxLoopTime(int i){
		this.maxLoopTime = i;
	}
	
	public void setMinLoopTime(int i){
		this.minLoopTime = i;
	}
	
	
	public int getMaxLoopTime(){
		return maxLoopTime;
	}
	
	public int getMinLoopTime(){
		return minLoopTime;
	}
	
	
	
	public void setLastAntiban(long l){
		this.lastAntiban = l;
	}
	
	public long getLastAntiban(){
		return lastAntiban;
	}
	
	public void setAntibanGap(){
		antibanGap = Script.random(240000, 1020000);
	}
	
	public long getAntibanGap(){
		return antibanGap;
	}
	

	

}
