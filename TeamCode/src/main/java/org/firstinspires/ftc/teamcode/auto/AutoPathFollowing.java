package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.util.CubicBezier;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;

public class AutoPathFollowing extends AutoAction{
    protected float duration = 2f;
    protected float cutoff = duration;
    protected float speed = 1f;
    protected CubicBezier curve;
    protected ActionSequencer.StickAction action;
    @Override
    public float getDuration() {
        return duration;
    }

    @Override
    public void loop(RobotController robot, float elapsed) {
        action.loop(robot,
                new ActionSequencer.StickAction.DataBuilder()
                        .stick(curve.vel.at(elapsed / cutoff).scale(1/4f).scale(speed))
                        .get()
        );
    }

    public static class Builder {
        AutoPathFollowing action;

        public Builder(RobotController robot) {
            action = new AutoPathFollowing();
            action.curve = new CubicBezier();
            action.action = robot.motorControls.getMoveAction();
            action.cutoff = 2f;
        }

        public Builder duration(float _dur) {
            action.duration = _dur;
            return this;
        }
        public Builder curve(CubicBezier curve) {
            action.curve = curve;
            return this;
        }

        public Builder speed(float _speed) {
            action.speed = _speed;
            return this;
        }

        public Builder cutoff(float _c) {
            action.cutoff = _c;
            return this;
        }
        public AutoPathFollowing get() {
            return action;
        }
    }


}
