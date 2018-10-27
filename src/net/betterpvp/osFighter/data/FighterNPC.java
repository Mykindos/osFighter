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
		return name + " (Level " + level + ")";
	}

	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!FighterNPC.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final FighterNPC other = (FighterNPC) obj;
	    if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
	        return false;
	    }
	    if (this.level != other.level) {
	        return false;
	    }
	    return true;
	}
	
}
