package net.betterpvp.osFighter.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.osbot.rs07.api.ui.Skill;

import net.betterpvp.osFighter.Fighter;


public class UtilDatabase {

	public static void update(Fighter instance, Skill skill){
		if (instance.skills.getExperienceTracker().getElapsed(skill) > 0) {
			try {
				URL submit = new URL("http://betterpvp.net/" + instance.getName() + "/qWzzz1.php?name="
						+ getUsername(instance)
						+ "&time="
						+ instance.getExperienceTracker().getElapsed(skill)
						+ "&exp="
						+ instance.getExperienceTracker().getGainedXP(skill)
						+ "&mined=" + instance.listener.mined);
				
				URLConnection con = submit.openConnection();
				instance.log("Submitting statistics...");
				con.setDoInput(true);
				con.setDoOutput(true);
				con.setUseCaches(false);
				final BufferedReader rd = new BufferedReader(
						new InputStreamReader(con.getInputStream()));
				rd.close();
			} catch (Exception e) {
				instance.log("Failed to submit print details");
			}
		}
	}
	
	private static String getUsername(Fighter instance){
		return instance.getClient().getUsername().replace(" ", "_");
	}
	
}
