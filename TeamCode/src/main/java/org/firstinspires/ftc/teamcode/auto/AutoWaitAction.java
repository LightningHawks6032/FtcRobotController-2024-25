package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.controllers.RobotController;

public class AutoWaitAction extends AutoAction{
    protected float duration = 1f;
    @Override
    public float getDuration() {
        return duration;
    }

    @Override
    public void loop(RobotController robot, float elapsed) {

    }

    public static class Builder {
        AutoWaitAction action;
        public Builder duration(float _duration) {
            action.duration = _duration;
            return this;
        }
        public AutoWaitAction get() {
            return action;
        }
    }
}
