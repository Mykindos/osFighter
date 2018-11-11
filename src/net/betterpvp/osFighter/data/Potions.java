package net.betterpvp.osFighter.data;

import org.osbot.rs07.api.filter.ContainsNameFilter;
import org.osbot.rs07.api.model.Item;

public enum Potions {

	/*ATTACK("Attack", "Super Attack"),
	STRENGTH("Strength", "Super Strength"),
	DEFENCE("Defence", "Super Defence"),
	COMBAT("Combat", "Super Combat"),
	RANGED("Ranging"),
	MAGIC("Magic");
	*/

	ATTACK(new ContainsNameFilter<>("Attack")),
	STRENGTH(new ContainsNameFilter<>("Strength")),
	DEFENCE(new ContainsNameFilter<>("Defence")),
	COMBAT(new ContainsNameFilter<>("Combat")),
	RANGED(new ContainsNameFilter<>("Ranging")),
	MAGIC(new ContainsNameFilter<>("Magic")),

	ANTIVENOM(new ContainsNameFilter<>("Anti-venom", "Antidote")),
	ANTIPOISON(new ContainsNameFilter<>("Antipoison", "Anti-venom", "Antidote")),
	ANTIFIRE(new ContainsNameFilter<>("Antifire"));
	
	//String[] options;
	ContainsNameFilter<Item> filter;
	private Potions(ContainsNameFilter<Item> filter) {
		this.filter = filter;
	}

	public ContainsNameFilter<Item> getOptions(){
		return filter;
	}
	
	//public String[] getOptions() {
	//	return options;
	//}
}
