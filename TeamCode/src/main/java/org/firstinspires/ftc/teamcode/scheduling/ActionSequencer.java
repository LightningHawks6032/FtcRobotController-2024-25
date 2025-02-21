package org.firstinspires.ftc.teamcode.scheduling;


import android.os.Build;

import org.firstinspires.ftc.teamcode.Toggle;
import org.firstinspires.ftc.teamcode.Vec2;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import java.util.function.Predicate;
public class ActionSequencer {
    public static <DataType> void execute(RobotController robot, IAction<DataType> action, DataType data) {
        if (action == null) {System.err.println("Undefined action called"); return;}
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
    public static class TriggerAction implements IAction<TriggerAction.Data> {
        public void loop(RobotController robot, Data data) {}
        public static class Data {
            public float trigger;
        }
        public static class DataBuilder {
            Data action;
            public DataBuilder() {
                action = new Data();
                action.trigger = 0;
            }
            public DataBuilder trigger(float _trigger) {
                action.trigger = _trigger;
                return this;
            }
            public Data get() {
                return action;
            }
        }
    }
    public static class ExecuteIf <DataType> implements IAction<DataType> {
        IAction<DataType> action;
        public Predicate<DataType> pred;
        @Override
        public void loop(RobotController robot, DataType data) {
            if (pred.test(data) && action != null) {
                action.loop(robot, data);
            }
        }
        public static class Builder <_DataType> {
            ExecuteIf<_DataType> action;
            public Builder () {
                action = new ExecuteIf<>();
            }

            public Builder<_DataType> action(IAction<_DataType> _action) {
                action.action = _action;
                return this;
            }
            public Builder<_DataType> predicate(Predicate<_DataType> _pred) {
                action.pred = _pred;
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
    public static class CardinalStickAction implements IAction<StickAction.Data> {
        public IAction<ButtonAction.Data> up, down, left, right;
        @Override
        public void loop(RobotController robot, StickAction.Data data) {
            execute(robot, right, new ButtonAction.DataBuilder().pressed(data.stick.x>0).get());
            execute(robot, left, new ButtonAction.DataBuilder().pressed(data.stick.x<0).get());
            execute(robot, up, new ButtonAction.DataBuilder().pressed(data.stick.y>0).get());
            execute(robot, down, new ButtonAction.DataBuilder().pressed(data.stick.y<0).get());
        }
        public static class Builder {
            CardinalStickAction action;
            public Builder() {
                action = new CardinalStickAction();
            }
            public Builder up(IAction<ButtonAction.Data> buttonAction) {
                action.up = buttonAction;
                return this;
            }
            public Builder down(IAction<ButtonAction.Data> buttonAction) {
                action.down = buttonAction;
                return this;
            }
            public Builder left(IAction<ButtonAction.Data> buttonAction) {
                action.left = buttonAction;
                return this;
            }
            public Builder right(IAction<ButtonAction.Data> buttonAction) {
                action.right = buttonAction;
                return this;
            }
            public CardinalStickAction get() {return action;}
        }

    }
    public static class AxialStickAction implements IAction<StickAction.Data> {
        public IAction<TriggerAction.Data> horizontal, vertical;
        @Override
        public void loop(RobotController robot, StickAction.Data data) {
            execute(robot, horizontal, new TriggerAction.DataBuilder().trigger(data.stick.x).get());
            execute(robot, vertical, new TriggerAction.DataBuilder().trigger(data.stick.y).get());
        }
        public static class Builder {
            AxialStickAction action;
            public Builder() {
                action = new AxialStickAction();
            }
            public AxialStickAction.Builder horizontal(IAction<TriggerAction.Data> triggerAction) {
                action.horizontal = triggerAction;
                return this;
            }
            public AxialStickAction.Builder vertical(IAction<TriggerAction.Data> triggerAction) {
                action.vertical = triggerAction;
                return this;
            }
            public AxialStickAction get() {return action;}
        }
    }
    public static class SwitchButtonAction implements IAction<ButtonAction.Data> {
        public ButtonAction action1, action2;
        public Toggle toggle;
        @Override
        public void loop(RobotController robot, ButtonAction.Data data) {
            toggle.loop(data.pressed);
            if (toggle.state) {action1.loop(robot, data);}
            else {action2.loop(robot, data);}
        }
        public static class Builder {
            SwitchButtonAction action;
            public Builder() {
                action = new SwitchButtonAction();
                action.action1 = new ButtonAction();
                action.action2 = new ButtonAction();
                action.toggle = new Toggle(false);
            }
            public final Builder first(ButtonAction buttonAction) {
                action.action1 = buttonAction;
                return this;
            }
            public final Builder second(ButtonAction buttonAction) {
                action.action2 = buttonAction;
                return this;
            }
            public final SwitchButtonAction get() {return action;}
        }
    }
}
