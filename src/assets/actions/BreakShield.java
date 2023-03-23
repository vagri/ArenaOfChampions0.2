package assets.actions;

import assets.Action;

public class BreakShield extends Action {

        public BreakShield(int effectID, String name, int duration, boolean stackable, boolean isVisible) {
            super(effectID, name, duration, stackable, isVisible);
        }

        static int actionID = 4;
        static boolean isVisible = false;
}
