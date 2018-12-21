package net.betterpvp.osFighter.managers;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.input.mouse.BotMouseListener;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.data.SessionData;
import net.betterpvp.osFighter.utilities.UtilTime;

public class MouseListener extends BotMouseListener{

	private Fighter i;

	public MouseListener(Fighter i) {
		this.i = i;
	}

	private long lastExecute = System.currentTimeMillis();
	@Override
	public void checkMouseEvent(MouseEvent e) {

		if(i.isDefiningArea()) {
			if(SwingUtilities.isLeftMouseButton(e)) {
				if(e.isShiftDown()) {
					if(UtilTime.elapsed(lastExecute, 200)) {

						SessionData data = i.getSessionData();
						Position pos = getPositionUnderMouse(e.getPoint());

						if(pos != null) {

							data.getCombatAreaPositions().add(pos);
							
							if(data.getCombatAreaPositions().size() == 2) {
								Position a = data.getCombatAreaPositions().get(0);
								Position b = data.getCombatAreaPositions().get(1);
								data.setCombatArea(new Area(a.getX(), a.getY(), b.getX(), b.getY()).setPlane(i.myPosition().getZ()));
							}else {
								data.setCombatArea(new Area(data.getCombatAreaPositions().toArray(new Position[data.getCombatAreaPositions().size()])));
							}
						}

						lastExecute = System.currentTimeMillis();
					}
					e.consume();

				}
			}
		}

	}

	private Position getPositionUnderMouse(Point p) {
		Position pos = null;

		for(int x = 0; x < 150; x++) {
			for(int y = 0; y < 150; y++) {
				Position temp = new Position(i.getMap().getBaseX() + x, i.getMap().getBaseY() + y, i.getMap().getPlane());
				if(temp != null) {
					if(temp.isVisible(i.getBot()) && temp.getPolygon(i.getBot()).contains(p)) {
						pos = temp;
					}
				}

			}
		}

		return pos;
	}

}
