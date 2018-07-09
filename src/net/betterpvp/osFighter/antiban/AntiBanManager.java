package net.betterpvp.osFighter.antiban;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.antiban.modules.CameraMovement;
import net.betterpvp.osFighter.antiban.modules.EXPCheck;
import net.betterpvp.osFighter.antiban.modules.FriendCheck;
import net.betterpvp.osFighter.antiban.modules.HoverPlayer;
import net.betterpvp.osFighter.antiban.modules.MouseMovement;
import net.betterpvp.osFighter.antiban.modules.SleepyTime;
import net.betterpvp.osFighter.utilities.UtilMath;

import java.util.Set;

public class AntiBanManager {
	
	
	public AntiBanManager(Fighter i){
		addModule(new FriendCheck(i));
		addModule(new EXPCheck(i));
		addModule(new HoverPlayer(i));
		addModule(new MouseMovement(i));
		addModule(new SleepyTime(i));
		addModule(new CameraMovement(i));
	}
	
	private static Set<AntiBan> ab = new HashSet<>();
	private static HashMap<AntiBan, Double> picker = new HashMap<>();
	
	public static Set<AntiBan> getAntiBans(){
		return ab;
	}
	
	public static boolean isModule(String s){
		for(AntiBan ab : getAntiBans()){
			if(ab.getName().equalsIgnoreCase(s)){
				return true;
			}
		}
		return false;
	}
	
	public static AntiBan getModule(String s){
		for(AntiBan ab : getAntiBans()){
			if(ab.getName().equalsIgnoreCase(s)){
				return ab;
			}
		}
		return null;
	}
	
	public static AntiBan getNextModule(){
		double sumWeights = 0; // total sum of the weights of items
		
		
		for (Entry<AntiBan, Double> map : picker.entrySet()) {
			if(!map.getKey().isEnabled()) continue;
			sumWeights += map.getValue();
		}

		double randNum = UtilMath.randDouble(0, sumWeights);
		double sum = 0;
		for (Entry<AntiBan, Double> map : picker.entrySet()) {
			if(!map.getKey().isEnabled()) continue;
			sum += map.getValue();
			if (randNum <= sum) {
				return map.getKey();
			}
		}
		
		return null;
	}
	
	public static void addModule(AntiBan a){
		getAntiBans().add(a);
		picker.put(a, a.getChance());
	}
	
	public static void removeModule(String name){
		for(AntiBan a : getAntiBans()){
			if(a.getName().equals(name)){
				removeModule(a);
			}
		}
	}
	
	public static void removeModule(AntiBan a){
		getAntiBans().remove(a);
	}

}
