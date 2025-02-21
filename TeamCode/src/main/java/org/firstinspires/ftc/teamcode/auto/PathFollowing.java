package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.CubicBezier;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;

public class PathFollowing extends AutoAction{
    protected float duration = 2f;
    protected CubicBezier curve;
    @Override
    float getDuration() {
        return duration;
    }

    @Override
    public void loop(RobotController robot, float elapsed) {
        robot.motorControls.getMoveAction().loop(robot,
                new ActionSequencer.StickAction.DataBuilder()
                        .stick(curve.vel.at(elapsed / duration))
                        .get()
        );
    }

    public static class Builder {
        PathFollowing action;

        public Builder() {
            action = new PathFollowing();
            action.curve = new CubicBezier();
        }

        public Builder duration(float _dur) {
            action.duration = _dur;
            return this;
        }
        public Builder curve(CubicBezier curve) {
            action.curve = curve;
            return this;
        }
        public PathFollowing get() {
            return action;
        }
    }


}
