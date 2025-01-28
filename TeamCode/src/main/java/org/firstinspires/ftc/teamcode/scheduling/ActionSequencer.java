package org.firstinspires.ftc.teamcode.scheduling;


import org.firstinspires.ftc.teamcode.Vec2;
import org.firstinspires.ftc.teamcode.controllers.RobotController;

public class ActionSequencer {
    public static <DataType> void execute(RobotController robot, IAction<DataType> action, DataType data) {
        action.loop(robot, data);
    }

    public static class ActionGroup <DataType> implements IAction<DataType> {
        IAction<DataType>[] actions;
        public void loop(RobotController robot, DataType data) {
            for (IAction<DataType> action : actions){
                execute(robot, action, data);
            }
        }
        @SafeVarargs
        public ActionGroup(IAction<DataType>... _actions) {actions = _actions;}
    }

    public static class StickAction implements IAction<StickAction.Data> {
        public void loop(RobotController robot, Data data) {}
        public static class Data {
            public Vec2 stick;
        }
        public static class DataBuilder {
            Data action;
            public DataBuilder() {
                action = new Data();
                action.stick = new Vec2(0, 0);
            }
            public DataBuilder stick(Vec2 _stick) {
                action.stick = _stick;
                return this;
            }
            public Data get() {
                return action;
            }
        }
    }
}
