package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.CubicBezier;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;

public class PathFollowing extends AutoAction{
    protected float duration = 2f;
    protected float cutoff = duration;
    protected CubicBezier curve;
    protected ActionSequencer.StickAction action;
    @Override
    float getDuration() {
        return duration;
    }

    @Override
    public void loop(RobotController robot, float elapsed) {
        action.loop(robot,
                new ActionSequencer.StickAction.DataBuilder()
                        .stick(curve.vel.at(elapsed / cutoff).norm().scale(0.5f))
                        .get()
        );
    }

    public static class Builder {
        PathFollowing action;

        public Builder(RobotController robot) {
            action = new PathFollowing();
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

        public Builder cutoff(float _c) {
            action.cutoff = _c;
            return this;
        }
        public PathFollowing get() {
            return action;
        }
    }


}
