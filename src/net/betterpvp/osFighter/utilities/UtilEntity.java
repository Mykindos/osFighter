package net.betterpvp.osFighter.utilities;

import java.util.Arrays;

import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.RS2Object;

import net.betterpvp.osFighter.Fighter;

public class UtilEntity {

	/**
	 * 
	 * @param ent The entity
	 * @param str The actions you are searching for
	 * @return True if the entity contains all of the given actions
	 */
	public static boolean hasAction(Entity ent, String[] str){
			
			return Arrays.stream(str).allMatch(s -> ent.hasAction(s));
		
	}


	/**
	 * 
	 * @param i Instance variable
	 * @param args The actions, or the name of the entity to look for.
	 * @return An Object or NPC that has the actions listed, or the name listed
	 */
	@SuppressWarnings("unchecked")
	public static Entity getEntity(Fighter i, String... args){

		RS2Object obj = i.objects.closest(o -> hasAction(o, args));
		if(obj == null){
			obj = i.objects.closest(args);
			if(obj != null) return obj;
		}else{
			return obj;
		}

		// If no object was found
		NPC npc = i.npcs.closest(n -> hasAction(n, args) );
		if(npc == null){
			npc = i.npcs.closest(args);
			if(npc != null) return npc;
		}else{
			return npc;
		}

		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static NPC getNPC(Fighter i, String... args){

		NPC npc = i.npcs.closest(n -> hasAction(n, args) );
		if(npc == null){
			npc = i.npcs.closest(args);
			if(npc != null) return npc;
		}else{
			return npc;
		}

		return null;
	}
	
}





