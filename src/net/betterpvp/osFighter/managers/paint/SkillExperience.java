package net.betterpvp.osFighter.managers.paint;

import java.awt.image.BufferedImage;

import org.osbot.rs07.api.ui.Skill;

import net.betterpvp.osFighter.Fighter;

public class SkillExperience {

	private Fighter i;
	private Skill s;
	private BufferedImage resource;
	
	public SkillExperience(Fighter i, Skill s, BufferedImage resource) {
		this.i = i;
		this.s = s;
		this.resource = resource;
	}
	
	public int getGainedExp() {
		return i.getExperienceTracker().getGainedXP(s);
	}
	
	public BufferedImage getResource() {
		return resource;
	}
	
	public Skill getSkill() {
		return s;
	}
}
