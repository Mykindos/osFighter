package net.betterpvp.osFighter.data;

import java.util.Arrays;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;

public enum Bank {

	AUTO("Auto", null),
	LUMBRIDGE_UPPER("Lumbridge Upper", Banks.LUMBRIDGE_UPPER),
	LUMBRIDGE_LOWER("Lumbridge Lower", Banks.LUMBRIDGE_LOWER),
	AL_KHARID("Al Kharid", Banks.AL_KHARID),
	ARCEUUS_HOUSE("Arceuus House", Banks.ARCEUUS_HOUSE),
	ARDOUGNE_NORTH("Ardougne North", Banks.ARDOUGNE_NORTH),
	ARDOUGNE_SOUTH("Ardougne South", Banks.ARDOUGNE_SOUTH),
	CAMELOT("Camelot", Banks.CAMELOT),
	CANIFIS("Canifis", Banks.CANIFIS),
	CASTLE_WARS("Castle Wars", Banks.CASTLE_WARS),
	CATHERBY("Catherby", Banks.CATHERBY),
	DRAYNOR("Draynor", Banks.DRAYNOR),
	DUEL_ARENA("Duel Arena", Banks.DUEL_ARENA),
	EDGEVILLE("Edgeville", Banks.EDGEVILLE),
	FALADOR_EAST("Falador East", Banks.FALADOR_EAST),
	FALADOR_WEST("Falador West", Banks.FALADOR_WEST),
	GNOME_STRONGHOLD("Gnome Stronghold", Banks.GNOME_STRONGHOLD),
	GRAND_EXCHANGE("Grand Exchange", Banks.GRAND_EXCHANGE),
	HOSIDIUS_HOUSE("Hosidius House", Banks.HOSIDIUS_HOUSE),
	LOVAKENGJ_HOUSE("Lovakenj House", Banks.LOVAKENGJ_HOUSE),
	LOVAKITE_MINE("Lovakite Mine", Banks.LOVAKITE_MINE),
	PEST_CONTROL("Pest Control", Banks.PEST_CONTROL),
	PISCARILIUS_HOUSE("Piscalirius House", Banks.PISCARILIUS_HOUSE),
	SHAYZIEN_HOUSE("Shayzien House", Banks.SHAYZIEN_HOUSE),
	TZHAAR("Tzhaar", Banks.TZHAAR),
	VARROCK_EAST("Varrock East", Banks.VARROCK_EAST),
	VARROCK_WEST("Varrock West", Banks.VARROCK_WEST),
	YANILLE("Yanille", Banks.YANILLE),
	CRAFTING_GUILD("Crafting Guild", new Area(2934, 3283, 2940, 3278)),
	PISCATORIS("Piscatoris", new Area(2325, 3694, 2334, 3684)),
	ROGUES_DEN("Rogue's Den", new Area(3046, 4975, 3039, 4969).setPlane(1)),
	SHILO_VILLAGE("Shilo Village", new Area(2856, 2950, 2848, 2958)),
	PORT_SARIM("Port Sarim - Deposit", new Area(3042, 3237, 3050, 3234)),
	SHANTAY_PASS("Shantay Pass", new Area(3305, 3122, 3310, 3118)),
	NARDAH("Nardah", new Area(3424, 2894, 3431, 2889)),
	MINING_GUILD("Mining Guild", new Area(3010, 9721, 3017, 9715)),
	KOUREND("Kourend", new Area(1610, 3683, 1613, 3679).setPlane(2)),
	KELDAGRIM("Keldagrim", new Area(2834, 10214, 2841, 10204));
	
	private String name;
	private Area area;
	
	private Bank(String name, Area area){
		this.name = name;
		this.area = area;
	}
	
	public String getName(){
		return name;
	}
	
	public Area getArea(){
		return area;
	}
	
	public static Bank getBank(String name){
		return Arrays.asList(values()).stream().filter(b -> b.getName().equalsIgnoreCase(name)).findFirst().get();
	}
	
	public static Area[] getBankArea() {
		Area[] areas = new Area[values().length];
		
		for(int i = 0; i < values().length; i++) {
			areas[i] = values()[i].getArea();
		}
		
		return areas;
	}

	@Override
	public String toString(){
		return getName();
	}

}
