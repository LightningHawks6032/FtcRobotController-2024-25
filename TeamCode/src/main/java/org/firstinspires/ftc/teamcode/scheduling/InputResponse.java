package org.firstinspires.ftc.teamcode.scheduling;

import org.firstinspires.ftc.teamcode.controllers.GamepadController;
import org.firstinspires.ftc.teamcode.controllers.RobotController;

public class InputResponse {
    public static class InputResponseManager {
        GamepadController gamepad;
        RobotController robot;

        ActionSequencer.ActionGroup<ActionSequencer.StickAction.Data> leftStickAction;
        ActionSequencer.ActionGroup<ActionSequencer.StickAction.Data> rightStickAction;

        public InputResponseManager(GamepadController gamepad, RobotController robot, ActionSequencer.ActionGroup<ActionSequencer.StickAction.Data> leftStickAction, ActionSequencer.ActionGroup<ActionSequencer.StickAction.Data> rightStickAction) {
            this.gamepad = gamepad;
            this.robot = robot;
            this.leftStickAction = leftStickAction;
            this.rightStickAction = rightStickAction;
        }

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

        public void loop() {
            onLeftStick();
            onRightStick();
        }
    }
    public static class Builder {
        GamepadController gamepad;
        RobotController robot;
        ActionSequencer.ActionGroup<ActionSequencer.StickAction.Data> leftStickAction;
        ActionSequencer.ActionGroup<ActionSequencer.StickAction.Data> rightStickAction;
        public Builder(GamepadController _gamepad, RobotController _robot) {
            gamepad = _gamepad;
            robot = _robot;
            leftStickAction = new ActionSequencer.ActionGroup<>();
            rightStickAction = new ActionSequencer.ActionGroup<>();
        }
        @SafeVarargs
        public final Builder leftStickAction(IAction<ActionSequencer.StickAction.Data>... _leftStickAction) {
            leftStickAction.actions = _leftStickAction;
            return this;
        }
        @SafeVarargs
        public final Builder rightStickAction(IAction<ActionSequencer.StickAction.Data>... _rightStickAction) {
            rightStickAction.actions = _rightStickAction;
            return this;
        }

        public final InputResponseManager get() {
            return new InputResponseManager(gamepad, robot, leftStickAction, rightStickAction);
        }
    }
}

