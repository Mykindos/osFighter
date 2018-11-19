package net.betterpvp.osFighter.scheduler.tasks;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.scheduler.Task;

public class EquipTask implements Task {

	private String item;
	
	public EquipTask(String item) {
		this.item = item;
	}
	
	public String getItem() {
		return item;
	}

	@Override
	public void runTask(Fighter i) {
		int slot = i.getInventory().getSlot(item);
		i.getInventory().interact(slot, "Wield", "Wear");
	}

	@Override
	public boolean isComplete(Fighter i) {
		return i.getEquipment().contains(item);
	}

	@Override
	public boolean validate(Fighter i) {
		return i.getInventory().contains(item);
	}

	@Override
	public String toString(){
		return "Equip " + getItem();
	}
}

