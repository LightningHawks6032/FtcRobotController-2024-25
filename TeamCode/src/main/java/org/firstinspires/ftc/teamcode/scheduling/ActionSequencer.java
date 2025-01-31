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
    public static class ButtonAction implements IAction<ButtonAction.Data> {
        public void loop(RobotController robot, Data data) {}
        public static class Data {
            public boolean pressed;
        }
        public static class DataBuilder {
            Data action;
            public DataBuilder() {
                action = new Data();
                action.pressed = false;
            }
            public DataBuilder pressed(boolean _pressed) {
                action.pressed = _pressed;
                return this;
            }
            public Data get() {
                return action;
            }
        }
    }
    public static class ExecuteIf <DataType> implements IAction<ExecuteIf.Data<DataType>> {
        IAction<DataType> action;
        @Override
        public void loop(RobotController robot, Data<DataType> data) {
            if (data.pred && action != null) {
                action.loop(robot, data.data);
            }
        }

        public static class Data <_DataType> {
            boolean pred;
            _DataType data;
        }
        public static class DataBuilder <_DataType>{
            ExecuteIf.Data<_DataType> action;
            public DataBuilder() {
                action = new ExecuteIf.Data<>();
                action.pred = false;
            }
            public ExecuteIf.DataBuilder<_DataType> pred(boolean _pred) {
                action.pred = _pred;
                return this;
            }
            public ExecuteIf.Data<_DataType> get() {
                return action;
            }
        }
        public static class Builder <_DataType> {
            ExecuteIf<_DataType> action;
            public Builder<_DataType> action(IAction<_DataType> _action) {
                action.action = (IAction<_DataType>) _action;
                return this;
            }
            public ExecuteIf<_DataType> get() {return action;}
        }
    }
    public static class LinkAction <T1, T2> implements IAction<LinkAction.Data<T1, T2>> {
        IAction<T1> action1;
        IAction<T2> action2;
        @Override
        public void loop(RobotController robot, Data<T1, T2> data) {
            action1.loop(robot, data.data1);
            action2.loop(robot, data.data2);
        }

        public static class Data <T1, T2> {
            T1 data1;
            T2 data2;
        }
        public static class DataBuilder <T1, T2>{
            LinkAction.Data<T1, T2> action;
            public DataBuilder() {
                action = new LinkAction.Data<>();
            }
            public LinkAction.DataBuilder<T1, T2> data1(T1 data) {
                action.data1 = data;
                return this;
            }public LinkAction.DataBuilder<T1, T2> data2(T2 data) {
                action.data2 = data;
                return this;
            }

            public LinkAction.Data<T1, T2> get() {
                return action;
            }
        }
        public static class Builder <T1, T2> {
            LinkAction<T1, T2> action;
            public Builder<T1, T2> action1(IAction<T1> _action) {
                action.action1 =  _action;
                return this;
            }
            public Builder<T1, T2> action2(IAction<T2> _action) {
                action.action2 =  _action;
                return this;
            }
            public LinkAction<T1, T2> get() {return action;}
        }
    }
}
