package net.betterpvp.osFighter.utilities;

import org.osbot.rs07.api.filter.ContainsNameFilter;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.ui.Skill;

import net.betterpvp.osFighter.Fighter;

public class UtilItem {
	
	
	@SuppressWarnings("unchecked")
	public static boolean hasItem(Fighter i, String item){
		ContainsNameFilter<Item> filter = new ContainsNameFilter<Item>(item);
		return i.getInventory().contains(filter) || i.getEquipment().contains(filter);
	}
	
	@SuppressWarnings("unchecked")
	public static boolean hasItem(Fighter i, Filter<Item> f){
		return i.getInventory().contains(f) || i.getEquipment().contains(f);
	}
	
	public static String getBestPickaxe(Fighter i){
		int lvl = i.getSkills().getStatic(Skill.MINING);
		if(i.getBank().isOpen()){
			
			if(lvl >= 61){
				if(i.getBank().contains("Infernal pickaxe") || UtilItem.hasItem(i, "Infernal pickaxe")){
					return "Infernal pickaxe";
				}else if(i.getBank().contains("3rd age pickaxe") || UtilItem.hasItem(i, "3rd age pickaxe")){
					return "3rd age pickaxe";
				}else if(i.getBank().contains("Dragon pickaxe")|| UtilItem.hasItem(i, "Dragon pickaxe")){
					return "Dragon pickaxe";
				}
				
			}
			if(lvl >= 41){
				if(i.getBank().contains("Rune pickaxe")|| UtilItem.hasItem(i, "Rune pickaxe")){
					return "Rune pickaxe";
				}
			}
			if(lvl >= 31){
				if(i.getBank().contains("Adamant pickaxe")|| UtilItem.hasItem(i, "Adamant pickaxe")){
					return "Adamant pickaxe";
				}
			}
			
			if(lvl >= 21){
				if(i.getBank().contains("Mithril pickaxe")|| UtilItem.hasItem(i, "Mithril pickaxe")){
					return "Mithril pickaxe";
				}
			}
			
			if(lvl >= 11){
				if(i.getBank().contains("Black pickaxe")|| UtilItem.hasItem(i, "Black pickaxe")){
					return "Black pickaxe";
				}
			}
			
			if(lvl >= 6){
				if(i.getBank().contains("Steel pickaxe")|| UtilItem.hasItem(i, "Steel pickaxe")){
					return "Steel pickaxe";
				}
			}
			
			if(i.getBank().contains("Iron pickaxe")|| UtilItem.hasItem(i, "Iron pickaxe")){
				return "Iron pickaxe";
			}
			
			
			
			
		}
		
		return "Bronze pickaxe";
	}

}
