package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.controllers.RobotController;

public class AutoActionGroup extends AutoAction {

    AutoAction[] actions;
    @Override
    public float getDuration() {
        float max = actions[0].getDuration();
        for (int i = 1; i < actions.length; i++) {
            if (max < actions[i].getDuration()) {max = actions[i].getDuration();}
        }
        return max;
    }

    @Override
    public void loop(RobotController robot, float elapsed) {
        for (AutoAction a : actions) {
            if (elapsed < a.getDuration()) a.loop(robot, elapsed);
        }
    }
    public AutoActionGroup(AutoAction... _actions) {
        actions = _actions;
    }
}
