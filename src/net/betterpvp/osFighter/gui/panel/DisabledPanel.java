package net.betterpvp.osFighter.gui.panel;

import java.awt.*;

import javax.swing.*;

public class DisabledPanel extends JPanel {
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.setEnabledRecursive(this, enabled);
    }

    protected void setEnabledRecursive(Component component, boolean enabled) {
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                child.setEnabled(enabled);

                if (!(child instanceof DisabledPanel)) {
                    setEnabledRecursive(child, enabled);
                }
            }
        }
    }
}