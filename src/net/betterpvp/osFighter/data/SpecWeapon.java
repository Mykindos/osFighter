package net.betterpvp.osFighter.data;

public enum SpecWeapon {
	
	AGS("", 50);
	
	
	private String itemName;
	private int cost;
	
	private SpecWeapon(String itemName, int cost) {
		this.itemName = itemName;
		this.cost = cost;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public int getCost() {
		return cost;
	}
	
	public static SpecWeapon getSpecWeapon(String itemName) {
		
		SpecWeapon wep = null;
		for(SpecWeapon w : values()) {
			if(w.getItemName().equalsIgnoreCase(itemName)) {
				wep = w;
			}
		}
		
		return wep;
		
		
	}

}
