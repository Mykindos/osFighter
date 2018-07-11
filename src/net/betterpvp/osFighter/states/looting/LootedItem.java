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
	
	@Override
	public String toString() {
		return itemName + ", " + lootCondition.name() + ", " + (replaceable ? "Replace" : "Don't Replace");
	}

	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!LootedItem.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final LootedItem other = (LootedItem) obj;
	    if ((this.itemName == null) ? (other.itemName != null) : !this.itemName.equals(other.itemName)) {
	        return false;
	    }

	    return true;
	}
}
