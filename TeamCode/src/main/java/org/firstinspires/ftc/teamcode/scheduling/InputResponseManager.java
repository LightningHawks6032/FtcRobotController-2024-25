package org.firstinspires.ftc.teamcode.scheduling;

import org.firstinspires.ftc.teamcode.controllers.GamepadController;
import org.firstinspires.ftc.teamcode.controllers.RobotController;

import java.util.function.Consumer;

public  class InputResponseManager {
        GamepadController gamepad;
        RobotController robot;

        public ActionSequencer.ActionGroup<ActionSequencer.StickAction.Data> leftStickAction;
        public ActionSequencer.ActionGroup<ActionSequencer.StickAction.Data> rightStickAction;
        public ActionSequencer.ActionGroup<ActionSequencer.StickAction.Data> dPadAction;
        public ActionSequencer.ActionGroup<ActionSequencer.ButtonAction.Data> AAction;
        public ActionSequencer.ActionGroup<ActionSequencer.ButtonAction.Data> BAction;
        public ActionSequencer.ActionGroup<ActionSequencer.ButtonAction.Data> XAction;
        public ActionSequencer.ActionGroup<ActionSequencer.ButtonAction.Data> YAction;

        LoopGroup loops;

        void onLeftStick() {
            ActionSequencer.StickAction.Data leftStickData = new ActionSequencer.StickAction.DataBuilder()
                    .stick(gamepad.leftStick())
                    .get();
            ActionSequencer.execute(robot, leftStickAction, leftStickData);
        }
        void onRightStick() {
            ActionSequencer.StickAction.Data rightStickData = new ActionSequencer.StickAction.DataBuilder()
                    .stick(gamepad.rightStick())
                    .get();
            ActionSequencer.execute(robot, rightStickAction, rightStickData);
        }

        void onA() {
            ActionSequencer.ButtonAction.Data AData = new ActionSequencer.ButtonAction.DataBuilder()
                    .pressed(gamepad.pressedA())
                    .get();
            ActionSequencer.execute(robot, AAction, AData);
        }
        void onX() {
            ActionSequencer.ButtonAction.Data XData = new ActionSequencer.ButtonAction.DataBuilder()
                    .pressed(gamepad.pressedX())
                    .get();
            ActionSequencer.execute(robot, XAction, XData);
        }
        void onY() {
            ActionSequencer.ButtonAction.Data YData = new ActionSequencer.ButtonAction.DataBuilder()
                    .pressed(gamepad.pressedY())
                    .get();
            ActionSequencer.execute(robot, YAction, YData);
        }
        void onB() {
            ActionSequencer.ButtonAction.Data BData = new ActionSequencer.ButtonAction.DataBuilder()
                    .pressed(gamepad.pressedB())
                    .get();
            ActionSequencer.execute(robot, BAction, BData);
        }
        void onDPad() {
            ActionSequencer.StickAction.Data data = new ActionSequencer.StickAction.DataBuilder()
                    .stick(gamepad.DPad())
                    .get();
            ActionSequencer.execute(robot, dPadAction, data);
        }


        public void loop() {
            loops.loop(robot);
            onLeftStick();
            onRightStick();
            onA();
            onB();
            onX();
            onY();
            onDPad();
        }
        public static class Builder {

            InputResponseManager inputResponseManager;
            public Builder(GamepadController _gamepad, RobotController _robot) {
                inputResponseManager = new InputResponseManager();
                inputResponseManager.gamepad = _gamepad;
                inputResponseManager.robot = _robot;
                inputResponseManager.leftStickAction = new ActionSequencer.ActionGroup<>();
                inputResponseManager.rightStickAction = new ActionSequencer.ActionGroup<>();
                inputResponseManager.AAction = new ActionSequencer.ActionGroup<>();
                inputResponseManager.BAction = new ActionSequencer.ActionGroup<>();
                inputResponseManager.XAction = new ActionSequencer.ActionGroup<>();
                inputResponseManager.YAction = new ActionSequencer.ActionGroup<>();
                inputResponseManager.dPadAction = new ActionSequencer.ActionGroup<>();
            }

            @SafeVarargs
            public final Builder leftStickAction(IAction<ActionSequencer.StickAction.Data>... _leftStickAction) {
                inputResponseManager.leftStickAction.actions = _leftStickAction;
                return this;
            }
            @SafeVarargs
            public final Builder rightStickAction(IAction<ActionSequencer.StickAction.Data>... _rightStickAction) {
                inputResponseManager.rightStickAction.actions = _rightStickAction;
                return this;
            }

            @SafeVarargs
            public final Builder AAction(IAction<ActionSequencer.ButtonAction.Data>... _AAction) {
                inputResponseManager.AAction.actions = _AAction;
                return this;
            }
            @SafeVarargs
            public final Builder BAction(IAction<ActionSequencer.ButtonAction.Data>... _BAction) {
                inputResponseManager.BAction.actions = _BAction;
                return this;
            }
            @SafeVarargs
            public final Builder XAction(IAction<ActionSequencer.ButtonAction.Data>... _XAction) {
                inputResponseManager.XAction.actions = _XAction;
                return this;
            }
            @SafeVarargs
            public final Builder YAction(IAction<ActionSequencer.ButtonAction.Data>... _YAction) {
                inputResponseManager.YAction.actions = _YAction;
                return this;
            }
            @SafeVarargs
            public final Builder DPadAction(IAction<ActionSequencer.StickAction.Data>... _dPadAction) {
                inputResponseManager.dPadAction.actions = _dPadAction;
                return this;
            }

            @SafeVarargs
            public final Builder loops(Consumer<RobotController>... _loops) {
                inputResponseManager.loops = new LoopGroup(_loops);
                return this;
            }


            public final InputResponseManager get() {
                return inputResponseManager;
            }
        }
    }

