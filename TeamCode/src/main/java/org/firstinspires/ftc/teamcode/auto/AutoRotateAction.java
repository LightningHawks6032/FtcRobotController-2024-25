package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.util.Vec2;
import org.firstinspires.ftc.teamcode.controllers.MotorControls;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;

public class AutoRotateAction extends AutoAction{

    protected float duration;
    protected int direction;

    MotorControls.RotateAction action;
    @Override
    public float getDuration() {
        return duration;
    }

    @Override
    public void loop(RobotController robot, float elapsed) {
        action.loop(robot, new ActionSequencer.StickAction.DataBuilder().stick(new Vec2(direction, 0)).get());
    }
    public static class Builder {
        AutoRotateAction action;

        public Builder(RobotController robot) {
            action = new AutoRotateAction();
            action.direction = 1;
            action.action = robot.motorControls.getRotateAction();
        }

        public AutoRotateAction.Builder duration(float _dur) {
            action.duration = _dur;
            return this;
        }
        public AutoRotateAction.Builder direction(int _d) {
            action.direction = _d;
            return this;
        }
        public AutoRotateAction get() {
            return action;
        }
    }
}
