package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.scheduling.IAction;

import java.util.function.Function;

public class ConstantAutoAction <DataType> extends AutoAction{

    protected IAction<DataType> action;
    protected float duration=1f;
    Function<RobotController, DataType> data;
    @Override
    public float getDuration() {
        return duration;
    }

    @Override
    public void loop(RobotController robot, float elapsed) {
        action.loop(robot, data.apply(robot));
    }
    public static class Builder<DataType> {
        ConstantAutoAction<DataType> action;

        public Builder() {
            action = new ConstantAutoAction<DataType>();
            action.duration = 1f;
        }

        public ConstantAutoAction.Builder<DataType> duration(float _dur) {
            action.duration = _dur;
            return this;
        }

        public Builder<DataType> dataFunc(Function<RobotController, DataType> _f) {
            action.data = _f;
            return this;
        }

        public Builder<DataType> action(IAction<DataType> _act) {
            action.action = _act;
            return this;
        }

        public ConstantAutoAction<DataType> get() {
            return action;
        }
    }
}
