package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.IMotor;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;

import java.util.function.Consumer;

/// Controls the spin of the floor claw
public class FloorClawSpinControls {
    public class RotateCounterClockwiseAction extends ActionSequencer.ButtonAction {
        @Override
        public void loop(RobotController robot, ActionSequencer.ButtonAction.Data data){
                if (data.pressed) currentPower = Math.min(currentPower + 0.005f, 1f);
                }
    }
    public class RotateClockwiseAction extends ActionSequencer.ButtonAction {
        @Override
        public void loop(RobotController robot, ActionSequencer.ButtonAction.Data data){
            if (data.pressed) currentPower = Math.max(currentPower - 0.005f, -1);
        }
    }
    public class ZeroRotationAction extends ActionSequencer.ButtonAction {
        @Override
        public void loop(RobotController robot, ActionSequencer.ButtonAction.Data data) {
            if (data.pressed) currentPower = 0.5f;
        }
    }
    IMotor motor;
    float currentPower = 0.5f;
    Telemetry telemetry;

    public RotateClockwiseAction rotClockwise;
    public RotateCounterClockwiseAction rotCounterClockwise;
    public ZeroRotationAction zeroRot;


    public Consumer<RobotController> intakeClawSpinLoop = (RobotController i) -> {
        motor.setPower(currentPower);
    };

    public FloorClawSpinControls(IMotor _motor, Telemetry _telemetry) {
        telemetry = _telemetry;
        motor = _motor;

        rotClockwise = new RotateClockwiseAction();
        rotCounterClockwise = new RotateCounterClockwiseAction();
        zeroRot = new ZeroRotationAction();
    }
}
