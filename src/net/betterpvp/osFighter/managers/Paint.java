package net.betterpvp.osFighter.managers;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.image.BufferedImage;
import java.beans.SimpleBeanInfo;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;

import net.betterpvp.osFighter.data.SessionData;
import net.betterpvp.osFighter.utilities.UtilFile;
import net.betterpvp.osFighter.utilities.UtilTime;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.ui.Message;
import org.osbot.rs07.api.ui.Skill;
import org.osbot.rs07.canvas.paint.Painter;
import org.osbot.rs07.input.mouse.BotMouseListener;
import org.osbot.rs07.listener.MessageListener;

import net.betterpvp.osFighter.Fighter;
import net.betterpvp.osFighter.managers.paint.PaintObject;
import net.betterpvp.osFighter.managers.paint.SkillExperience;
import net.betterpvp.osFighter.managers.paint.objects.Label;
import net.betterpvp.osFighter.managers.paint.objects.MousePathPoint;
import net.betterpvp.osFighter.managers.paint.objects.PaintImage;
import net.betterpvp.osFighter.managers.paint.objects.UpdatingLabel;


public class Paint extends BotMouseListener implements Painter, MessageListener {


    private Fighter i;
    public int mined = 0;
    private Point pos;

    private List<PaintObject> po = new ArrayList<>();
    private List<PaintObject> statLabels = new ArrayList<>();
    private int mX, mY;
    private long angle;
    private BasicStroke cursorStroke = new BasicStroke(2);
    private Color cursorColor = Color.CYAN;
    private AffineTransform oldTransform;
    private LinkedList<MousePathPoint> mousePath = new LinkedList<>();
    private String url = "http://mykindos.me/osFighter/";
    private String dir;
    private PaintImage activePaint;

    public Paint(Fighter i) {
        this.i = i;
        dir = getInstance().getDirectoryData() + "osFighter/";
        SessionData data = i.getSessionData();

        i.getSkills().getExperienceTracker().start(Skill.HITPOINTS);
        i.getSkills().getExperienceTracker().start(Skill.ATTACK);
        i.getSkills().getExperienceTracker().start(Skill.STRENGTH);
        i.getSkills().getExperienceTracker().start(Skill.DEFENCE);
        i.getSkills().getExperienceTracker().start(Skill.RANGED);
        i.getSkills().getExperienceTracker().start(Skill.MAGIC);
        i.getSkills().getExperienceTracker().start(Skill.PRAYER);


        try {
            data.getExpTrack().add(new SkillExperience(i, Skill.HITPOINTS, UtilFile.loadImage(dir, url, "Hitpoints.png")));
            data.getExpTrack().add(new SkillExperience(i, Skill.ATTACK, UtilFile.loadImage(dir, url,"Attack.png")));
            data.getExpTrack().add(new SkillExperience(i, Skill.STRENGTH, UtilFile.loadImage(dir, url,"Strength.png")));
            data.getExpTrack().add(new SkillExperience(i, Skill.DEFENCE, UtilFile.loadImage(dir, url, "Defence.png")));
            data.getExpTrack().add(new SkillExperience(i, Skill.RANGED, UtilFile.loadImage(dir, url, "Ranged.png")));
            data.getExpTrack().add(new SkillExperience(i, Skill.MAGIC,UtilFile.loadImage(dir, url, "Magic.png")));
            data.getExpTrack().add(new SkillExperience(i, Skill.PRAYER, UtilFile.loadImage(dir, url, "Prayer.png")));

            BufferedImage paintImg = UtilFile.loadImage(dir, url, "paint.png");
            activePaint = new PaintImage("Active Paint", 0, i.getDisplay().getScreenHeight() - 22 - paintImg.getHeight(), paintImg);
            addPaintObject(activePaint);
            //addPaintObject(new PaintImage("Active Paint", 0, 309, paintImg));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        loadCommonLabels();


    }


    @Override
    public void onPaint(Graphics2D g) {


        drawMouse(i, g);
        update();
        draw(g);

        if (i.isDefiningArea() || (!i.hasStarted() && i.getSessionData().getCombatAreaPositions().size() > 0)) {

            g.setColor(Color.RED);

            for (Position p : i.getSessionData().getCombatArea().getPositions()) {
                g.drawPolygon(p.getPolygon(i.getBot()));
            }
            g.setColor(Color.GREEN);
            if (i.getSessionData().getCombatAreaPositions().size() <= 4) {
                for (Position p : i.getSessionData().getCombatAreaPositions()) {
                    g.drawPolygon(p.getPolygon(i.getBot()));
                }
            }


            //g.setColor(Color.GREEN);
            //g.drawPolygon(i.getSessionData().getCombatArea().getPolygon());


        }


    }

    public void draw(Graphics2D g) {
        if (paintEnabled) {

            for (PaintObject p : po) {
                if (p.isEnabled()) {
                    p.draw(g);
                }
            }
            if (i.getSessionData().getSafeSpot() != null) {
                g.setColor(Color.YELLOW);
                g.drawPolygon(i.getSessionData().getSafeSpot().getPolygon(i.getBot()));
            }

            if (i.getSessionData().getResetPosition() != null) {
                g.setColor(Color.CYAN);
                g.drawPolygon(i.getSessionData().getResetPosition().getPolygon(i.getBot()));
            }

            if (i.getSessionData().isAFKMode() && i.getSessionData().getResetPosition() == null) {
                g.setColor(Color.RED);
                Font f1 = new Font("Calibri", Font.BOLD, 20);
                g.setFont(f1);
                g.drawString("You have not set a reset tile!", 11, 365);
                g.drawString("Press F4 to set the reset tile where you are standing", 11, 385);
                return;
            }

            if (i.getSessionData().isSafeSpotting() && i.getSessionData().getSafeSpot() == null) {
                g.setColor(Color.RED);
                Font f1 = new Font("Calibri", Font.BOLD, 16);
                g.setFont(f1);
                g.drawString("You have not set a safe spot! Press F3 to set the safe spot where you are standing", 11, 365);
                return;
            }

            i.getSessionData().getExpTrack().sort(new Comparator<SkillExperience>() {

                @Override
                public int compare(SkillExperience a, SkillExperience b) {
                    return b.getGainedExp() - a.getGainedExp();
                }

            });

            int x = 9;
            int y = activePaint.getY() + 35;
            for (SkillExperience e : i.getSessionData().getExpTrack()) {
                if (e.getGainedExp() <= 0) continue;
                if (x >= 260) {
                    x = 9;
                    y += 42;
                }

                g.drawImage(e.getResource(), x, y, null);
                Font f1 = new Font("Calibri", Font.BOLD, 12);
                g.setFont(f1);
                g.setColor(Color.black);

                g.drawString("TTL: ", x + 42, y + 10);
                g.drawString("EXP / H: ", x + 42, y + 24);
                g.drawString("Total: ", x + 42, y + 38);

                Font f2 = new Font("Calibri", Font.PLAIN, 12);
                g.setFont(f2);
                g.drawString(UtilTime.totalTime((int) i.getExperienceTracker().getTimeToLevel(e.getSkill())), x + 66, y + 10);
                g.drawString(NumberFormat.getNumberInstance(Locale.US).format(
                        i.getExperienceTracker().getGainedXPPerHour(e.getSkill())), x + 87, y + 24);
                g.drawString(NumberFormat.getNumberInstance(Locale.US).format(
                        e.getGainedExp()) + " (" + i.getExperienceTracker().getGainedLevels(e.getSkill()) + ")", x + 75, y + 38);

                x += 250;

            }

        }

    }

    public void update() {
        for (PaintObject p : po) {
            if (p.isEnabled()) {
                p.update(this);

            }
        }

        activePaint.setY(i.getDisplay().getScreenHeight() - 22 - activePaint.getHeight());
    }

    public void addPaintObject(PaintObject p) {
        po.add(p);
    }

    public void addCommonPaintObject(PaintObject p) {
        po.add(p);
        statLabels.add(p);
    }

    public PaintObject getPaintObject(String ID) {
        for (PaintObject p : po) {
            if (p.getID().equals(ID)) {
                return p;
            }
        }

        return null;
    }

    public Point getMousePosition() {
        return this.pos;
    }

    public Fighter getInstance() {
        return this.i;
    }


    public void loadCommonLabels() {
        int paintY = getPaintObject("Active Paint").getY();
        addPaintObject(new Label("Version", 5, 15, "v" + i.getVersion()));
        addPaintObject(new Label("Hotkeys", 5, 27, "Hotkeys"));
        addPaintObject(new Label("F1", 5, 39, "F1: Enable Area Selection"));
        addPaintObject(new Label("F2", 5, 51, "F2: Disable Area Selection"));
        addPaintObject(new Label("F3", 5, 63, "F3: Set Safe Spot"));
        addPaintObject(new Label("F4", 5, 75, "F4: Set Reset Position"));
        addPaintObject(new UpdatingLabel("TimeRunning", 335, paintY + 27) {
            @Override
            public void update(Paint p) {
                setY(getPaintObject("Active Paint").getY() + 27);
                setText("Time Ran: " + UtilTime.totalTime((int) i.getSkills().getExperienceTracker().getElapsed(Skill.HITPOINTS)));
            }
        });

        addPaintObject(new UpdatingLabel("Edit Settings", 335, paintY + 15, 100, 18) {
            @Override
            public void update(Paint p) {
                setY(getPaintObject("Active Paint").getY() + 15);
                setText("Edit Settings");
            }
        });
    }

    private void drawMouse(Fighter instance, Graphics2D g) {
        oldTransform = g.getTransform();
        mX = instance.getMouse().getPosition().x;
        mY = instance.getMouse().getPosition().y;

        g.setColor(Color.CYAN);

        g.drawRect(mX - 1, mY + 20, 1, 25);
        g.drawRect(mX - 1, mY - 45, 1, 25);
        g.drawRect(mX + 20, mY - 1, 25, 1);
        g.drawRect(mX - 45, mY - 1, 25, 1);

        g.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

        //MOUSE TRAIL
        while (!mousePath.isEmpty() && mousePath.peek().isUp())
            mousePath.remove();
        Point clientCursor = instance.getMouse().getPosition();
        MousePathPoint mpp = new MousePathPoint(clientCursor.x, clientCursor.y, 300);
        if (mousePath.isEmpty() || !mousePath.getLast().equals(mpp))
            mousePath.add(mpp);
        MousePathPoint lastPoint = null;
        for (MousePathPoint a : mousePath) {
            if (lastPoint != null) {
                g.setColor(new Color(255, 255, 255, a.getAlpha()));    //trail color
                g.drawLine(a.x, a.y, lastPoint.x, lastPoint.y);
            }
            lastPoint = a;
        }

        if (mX != -1) {
            g.setStroke(cursorStroke);
            g.setColor(cursorColor);
            g.drawLine(mX - 3, mY - 3, mX + 2, mY + 2);
            g.drawLine(mX - 3, mY + 2, mX + 2, mY - 3);

            g.rotate(Math.toRadians(angle += 6), mX, mY);

            g.draw(new Arc2D.Double(mX - 12, mY - 12, 24, 24, 330, 60, Arc2D.OPEN));
            g.draw(new Arc2D.Double(mX - 12, mY - 12, 24, 24, 151, 60, Arc2D.OPEN));

            g.setTransform(oldTransform);
        }
    }


    private boolean paintEnabled = true;

    @Override
    public void checkMouseEvent(MouseEvent e) {
        if (getPaintObject("Edit Settings").getRectangle().contains(e.getPoint())) {
            if (i.getGUI() == null) return;

            i.started = false;
            i.getGUI().setVisible(true);
            if (!i.getBot().getScriptExecutor().isPaused()) {
                e.consume();
            }
        } else if (getPaintObject("Active Paint").getRectangle().contains(e.getPoint())) {

            if (getPaintObject("Active Paint").isEnabled()) {
                getPaintObject("Active Paint").setEnabled(false);
                for (PaintObject p : statLabels) {
                    p.setEnabled(false);

                }

                paintEnabled = false;
            } else {
                getPaintObject("Active Paint").setEnabled(true);
                for (PaintObject p : statLabels) {
                    p.setEnabled(true);

                }

                paintEnabled = true;
            }
            if (!i.getBot().getScriptExecutor().isPaused()) {
                e.consume();
            }

        }


    }


    @Override
    public void onMessage(Message arg0) throws InterruptedException {
    }



}
