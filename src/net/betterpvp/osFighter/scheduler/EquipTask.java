package net.betterpvp.osFighter.scheduler;

public class EquipTask implements Task{

	private String item;
	
	public EquipTask(String item) {
		this.item = item;
	}
	
	public String getItem() {
		return item;
	}
}

