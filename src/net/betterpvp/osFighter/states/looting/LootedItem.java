package net.betterpvp.osFighter.states.looting;

public class LootedItem {

	private String itemName;
	private LootCondition lootCondition;
	private boolean replaceable;
	
	public LootedItem(String itemName, LootCondition lootCondition, boolean replaceable) {
		this.itemName = itemName;
		this.lootCondition = lootCondition;
		this.replaceable = replaceable;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public LootCondition getLootCondition() {
		return lootCondition;
	}
	
	public boolean isReplaceable() {
		return replaceable;
	}
}
