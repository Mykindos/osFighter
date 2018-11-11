package net.betterpvp.osFighter.data;

import org.osbot.rs07.api.filter.ContainsNameFilter;
import org.osbot.rs07.api.model.Item;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

public enum SpecWeapon {

	NONE("None", 0),
	ABYSSAL_BLUDGEON("Abyssal bludgeon", 50),
	ABYSSAL_DAGGER("Abyssal dagger", 50),
	ABYSSAL_TENTACLE("Abyssal tentacle", 50),
	ABYSSAL_WHIP("Abyssal whip", 50),
	ANCIENT_MACE("Ancient mace", 100),
	ARMADYL_GODSWORD("Armadyl godsword", 50),
	ARMADYL_CROSSBOW("Armadyl crossbow", 40),
	BANDOS_GODSWORD("Bandos godsword", 50),
	BARRELCHEST_ANCHOR("Barrelchest anchor", 50),
	BONE_DAGGER("Bone dagger", 75),
	BRINE_SABRE("Brine sabre", 75),
	CRYSTAL_HALBERD("Crystal halberd", 30),
	DARK_BOW("Dark bow", 55),
	DARKLIGHT("Darklight", 50),
	DAWNBRINGER("Dawnbringer", 35),
	DORGESHUUN_CROSSBOW("Dorgeshuun crossbow", 75),
	DRAGON_2H_SWORD("Dragon 2h sword", 60),
	DRAGON_AXE("Dragon axe", 100),
	DRAGON_BATTLEAXE("Dragon battleaxe", 50),
	DRAGON_CLAWS("Dragon claws", 50),
	DRAGON_DAGGER("Dragon dagger", 25),
	DRAGON_HALBERD("Dragon halberd", 30),
	DRAGON_LONGSWORD("Dragon longsword", 25),
	DRAGON_MACE("Dragon mace", 25),
	DRAGON_PICKAXE("Dragon pickaxe", 100),
	DRAGON_SCIMITAR("Dragon scimitar", 55),
	DRAGON_SPEAR("Dragon spear", 25),
	DRAGON_SWORD("Dragon sword", 25),
	DRAGON_WARHAMMER("Dragon warhammer", 50),
	EXCALIBUR("Excalibur", 100),
	GRANITE_HAMMER("Granite hammer", 60),
	GRANITE_MAUL("Granite maul", 50),
	MAGIC_COMP_BOW("Magic comp bow", 35),
	MAGIC_LONGBOW("Magic longbow", 35),
	MAGIC_SHORTBOW("Magic shortbow", 55),
	MAGIC_SHORTBOW_I("Magic shortbow (i)", 50),
	ROD_OF_IVANDIS("Rod of ivandis", 10),
	RUNE_CLAWS("Rune claws", 25),
	RUNE_THROWNAXE("Rune thrownaxe", 10),
	SARADOMIN_GODSWORD("Saradomin godsword", 50),
	SARADOMIN_SWORD("Saradomin sword", 100),
	SARADOMINS_BLESSED_SWORD("Saradomin's blessed sword", 65),
	SEERCULL("Seercull", 100),
	STAFF_OF_LIGHT("Staff of light", 100),
	STAFF_OF_THE_DEAD("Staff of the dead", 100),
	TOXIC_BLOWPIPE("Toxic blowpipe", 50),
	TOXIC_STAFF_OF_THE_DEAD("Toxic staff of the dead", 100),
	ZAMORAK_GODSWORD("Zamorak godsword", 50),
	ZAMORAKIAN_HASTA("Zamorakian hasta", 50),
	ZAMORAKIAN_SPEAR("Zamorakian spear", 50);




	
	
	private String itemName;
	private int cost;
	private ContainsNameFilter<Item> filter;
	
	private SpecWeapon(String itemName, int cost) {
		this.itemName = itemName;
		this.cost = cost;
		this.filter = new ContainsNameFilter<>(itemName);
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public int getCost() {
		return cost;
	}

	public ContainsNameFilter<Item> getFilter(){
		return filter;
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

	public static List<SpecWeapon> getSpecWeapons(){
	//	List<SpecWeapon> temp = new ArrayList<>();
		//for(SpecWeapon w : getSpecWeapons()){
		//	temp.add(w);
	//	}
//
		return Arrays.asList(values());
		//return temp;
	}

	@Override
	public String toString(){
		return itemName;
	}




}
