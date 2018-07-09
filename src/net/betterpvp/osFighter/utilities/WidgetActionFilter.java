package net.betterpvp.osFighter.utilities;

import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.ui.RS2Widget;

public class WidgetActionFilter implements Filter<RS2Widget> {

    private final String action;

    public WidgetActionFilter(final String action) {
        this.action = action;
    }

    @Override
    public boolean match(RS2Widget rs2Widget) {
        if (rs2Widget == null) {
            return false;
        }
        if (rs2Widget.getInteractActions() == null) {
            return false;
        }
        for (String action : rs2Widget.getInteractActions()) {
            if (this.action.equals(action)) {
                return true;
            }
        }
        return false;
    }
}
