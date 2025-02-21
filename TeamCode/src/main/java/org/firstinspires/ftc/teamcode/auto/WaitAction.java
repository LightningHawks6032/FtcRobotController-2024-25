package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.controllers.RobotController;

public class WaitAction extends AutoAction{
    protected float duration = 1f;
    @Override
    float getDuration() {
        return duration;
    }

    @Override
    void loop(RobotController robot, float elapsed) {

    }

    public static class Builder {
        WaitAction action;
        public Builder duration(float _duration) {
            action.duration = _duration;
            return this;
        }
        public WaitAction get() {
            return action;
        }
    }
}
