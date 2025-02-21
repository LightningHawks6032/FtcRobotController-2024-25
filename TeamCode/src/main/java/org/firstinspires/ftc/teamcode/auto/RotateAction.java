package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.CubicBezier;
import org.firstinspires.ftc.teamcode.Vec2;
import org.firstinspires.ftc.teamcode.controllers.MotorControls;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;
import org.firstinspires.ftc.teamcode.scheduling.IAction;

public class RotateAction extends AutoAction{

    protected float duration;
    protected int direction;

    MotorControls.RotateAction action;
    @Override
    float getDuration() {
        return duration;
    }

    @Override
    void loop(RobotController robot, float elapsed) {
        action.loop(robot, new ActionSequencer.StickAction.DataBuilder().stick(new Vec2(direction, 0)).get());
    }
    public static class Builder {
        RotateAction action;

        public Builder(RobotController robot) {
            action = new RotateAction();
            action.direction = 1;
            action.action = robot.motorControls.getRotateAction();
        }

        public RotateAction.Builder duration(float _dur) {
            action.duration = _dur;
            return this;
        }
        public RotateAction.Builder direction(int _d) {
            action.direction = _d;
            return this;
        }
        public RotateAction get() {
            return action;
        }
    }
}
