package net.betterpvp.osFighter.states;

import org.osbot.rs07.api.filter.ContainsNameFilter;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.ui.Skill;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.data.Potions;
import net.betterpvp.osFighter.data.SessionData;
import net.betterpvp.osFighter.utilities.CustomSleep;
import net.betterpvp.osFighter.utilities.UtilMath;

public class EatDrink extends ScriptState{

	private int deviateBy = 0;
	
	private ContainsNameFilter<Item> prayerPots = new ContainsNameFilter<Item>("prayer", "restore");

	@Override
	public boolean validate(Fighter i) throws InterruptedException {

		SessionData data = i.getSessionData();
		if(data.isEatingFood()) {
			return i.myPlayer().getHealthPercent() < 
					(deviateBy == 0 ? data.getHealthToEatBelow() : deviateBy);


		}

		if(data.isDrinkPotions()) {
			if(i.getCombat().isPoisoned() || i.getCombat().isDiseased()) {
				if(data.isDrinkAntidotePotions() || data.isDrinkAntipoisonPotions() || data.isDrinkAntivenomPotions()) {
					return true;
				}
			}

			if(data.isUsePrayer()) {
				if(i.getSkills().getDynamic(Skill.PRAYER) < data.getDrinkBelowPrayer()) {
					return true;
				}
			}

			if(data.isDrinkAttackPotions()) {
				checkStat(i, Skill.ATTACK, Potions.ATTACK);
			}

			if(data.isDrinkStrengthPotions()) {
				checkStat(i, Skill.STRENGTH, Potions.STRENGTH);
			}

			if(data.isDrinkDefencePotions()) {
				checkStat(i, Skill.DEFENCE, Potions.DEFENCE);
			}

			if(data.isDrinkCombatPotions()) {
				checkStat(i, Skill.ATTACK, Potions.COMBAT);
			}

			if(data.isDrinkRangingPotions()) {
				checkStat(i, Skill.RANGED, Potions.RANGED);
			}

			if(data.isDrinkMagicPotions()) {
				checkStat(i, Skill.MAGIC, Potions.MAGIC);
			}

		}
		return false;
	}

	private boolean checkStat(Fighter i, Skill skill, Potions pot) {
		if(i.getSkills().getDynamic(skill) < i.getSkills().getStatic(skill) + 5) {
			if(i.getInventory().contains(pot.getOptions())) {
				return true;
			}
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run(Fighter i) throws InterruptedException {
		SessionData data = i.getSessionData();
		deviateBy = data.getHealthToEatBelow() 
				+ UtilMath.randInt(data.getHealthDeviation() * -1,  data.getHealthDeviation());

		if(data.isEatingFood()) {
			if(i.myPlayer().getHealthPercent() < (deviateBy == 0 ? data.getHealthToEatBelow() : deviateBy)) {
				int healthPct = i.myPlayer().getHealthPercent();
				if(i.getInventory().contains(data.getFoodToEat())) {
					if(i.getInventory().interact("Eat", data.getFoodToEat())) {
						new CustomSleep(() -> i.myPlayer().getHealthPercent() > healthPct, 2000).sleep();
					}
				}else {
					data.setShouldBankNow(true);
					// TODO Explore escape options / stopping script
					/*
					 * Teleport
					 * Walk
					 * 
					 */
					
					return;
				}
			}
		}

		if(data.isDrinkPotions()) {
			if(data.isUsePrayer()) {
				if(data.isBankWhenNoPrayerSupplies()) {
					// Bank if we have no supplies to restore prayer
					if(!i.getInventory().contains(prayerPots)) {
						i.getSessionData().setShouldBankNow(true);
						return;
					}
				}
				
				int points = i.getSkills().getDynamic(Skill.PRAYER);
				if(points < data.getDrinkBelowPrayer()) {
					
					if(i.getInventory().interact("Drink", prayerPots)) {
						new CustomSleep(() -> i.getSkills().getDynamic(Skill.PRAYER) > points, 5000).sleep();
					}
				}
			}
		}

	}

}
