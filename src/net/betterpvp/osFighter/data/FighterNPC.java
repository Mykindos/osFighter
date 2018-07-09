package net.betterpvp.osFighter.data;

public class FighterNPC {
	
	private String name;
	private int level;
	
	public FighterNPC(String name, int level) {
		this.name = name;
		this.level = level;
	}
	
	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	@Override
	public String toString() {
		return name + ", " + level;
	}

}
