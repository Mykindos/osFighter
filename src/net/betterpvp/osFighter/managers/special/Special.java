package net.betterpvp.osFighter.managers.special;

public class Special {


	private String itemName;
	private int cost;

	public Special(String item, int cost) {
		this.itemName = item;
		this.cost = cost;
	}

	public String getItemName() {
		return itemName;
	}

	public int getCost() {
		return cost;
	}

}
