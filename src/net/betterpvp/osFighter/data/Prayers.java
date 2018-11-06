package net.betterpvp.osFighter.data;

import org.osbot.rs07.api.ui.PrayerButton;

import java.util.ArrayList;
import java.util.List;

public enum Prayers {

	
	NONE("None", null, null),
	THICK_SKIN("Thick Skin", PrayerButton.THICK_SKIN, PrayerType.DEFENSIVE),
	BURST_OF_STRENGTH("Burst of Strength", PrayerButton.BURST_OF_STRENGTH, PrayerType.OFFENSIVE),
	CLARITY_OF_THOUGHT("Clarity of Thought", PrayerButton.CLARITY_OF_THOUGHT, PrayerType.OFFENSIVE),
	SHARP_EYE("Sharp Eye", PrayerButton.SHARP_EYE, PrayerType.OFFENSIVE),
	MYSTIC_WILL("Mystic Will", PrayerButton.MYSTIC_WILL, PrayerType.OFFENSIVE),
	ROCK_SKIN("Rock Skin", PrayerButton.ROCK_SKIN, PrayerType.DEFENSIVE),
	SUPERHUMAN_STRENGTH("Superhuman Strength", PrayerButton.SUPERHUMAN_STRENGTH, PrayerType.OFFENSIVE),
	IMPROVED_REFLEXES("Improved Reflexes", PrayerButton.IMPROVED_REFLEXES, PrayerType.OFFENSIVE),
	RAPID_RESTORE("Rapid Restore", PrayerButton.RAPID_RESTORE, PrayerType.HEALING),
	RAPID_HEAL("Rapid Heal", PrayerButton.RAPID_HEAL, PrayerType.HEALING),
	PROTECT_ITEM("Protect Item", PrayerButton.PROTECT_ITEM, PrayerType.UTILITY),
	HAWK_EYE("Hawk Eye", PrayerButton.HAWK_EYE, PrayerType.OFFENSIVE),
	MYSTIC_LORE("Mystic Lore", PrayerButton.MYSTIC_LORE, PrayerType.OFFENSIVE),
	STEEL_SKIN("Steel Skin", PrayerButton.STEEL_SKIN, PrayerType.DEFENSIVE),
	ULTIMATE_STRENGTH("Ultimate Strength", PrayerButton.ULTIMATE_STRENGTH, PrayerType.OFFENSIVE),
	INCREDIBLE_REFLEXES("Incredible Reflexes", PrayerButton.INCREDIBLE_REFLEXES, PrayerType.OFFENSIVE),
	PROTECT_FROM_MAGIC("Protect from Magic", PrayerButton.PROTECT_FROM_MAGIC, PrayerType.PROTECTION),
	PROTECT_FROM_MISSILES("Protect from Missiles", PrayerButton.PROTECT_FROM_MISSILES, PrayerType.PROTECTION),
	PROTECT_FROM_MELEE("Protect from Melee", PrayerButton.PROTECT_FROM_MELEE, PrayerType.PROTECTION),
	EAGLE_EYE("Eagle Eye", PrayerButton.EAGLE_EYE, PrayerType.OFFENSIVE),
	MYSTIC_MIGHT("Mystic Might", PrayerButton.MYSTIC_MIGHT, PrayerType.OFFENSIVE),
	PRESERVE("Preserve", PrayerButton.PRESERVE, PrayerType.UTILITY),
	CHIVALRY("Chivalry", PrayerButton.CHIVALRY, PrayerType.OFFENSIVE),
	PIETY("Piety", PrayerButton.PIETY, PrayerType.OFFENSIVE),
	RIGOUR("Rigour", PrayerButton.RIGOUR, PrayerType.OFFENSIVE),
	AUGURY("Augury", PrayerButton.AUGURY, PrayerType.OFFENSIVE);


	
	private String name;
	private PrayerButton button;
	private PrayerType type;
	
	private Prayers(String name, PrayerButton button, PrayerType type) {
		
		this.name = name;
		this.button = button;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public PrayerButton getButton() {
		return button;
	}
	
	public PrayerType getType() {
		return type;
	}

	public static List<Prayers> getPrayersOfType(PrayerType t){
		List<Prayers> temp = new ArrayList<>();
		for (Prayers p : values()){
			if(p.getType() == t || p == NONE){
				temp.add(p);
			}
		}

		return temp;
	}

	@Override
	public String toString(){
		return name;
	}
	
}

