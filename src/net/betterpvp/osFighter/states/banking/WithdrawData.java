package net.betterpvp.osFighter.states.banking;

public class WithdrawData {

	private String itemName;
	private int amount = 1;
	
	public WithdrawData(String itemName) {
		this.itemName = itemName;
	}
	
	public WithdrawData(String itemName, int amount) {
		this.itemName = itemName;
		this.amount = amount;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public int getAmount() {
		return amount;
	}
}
