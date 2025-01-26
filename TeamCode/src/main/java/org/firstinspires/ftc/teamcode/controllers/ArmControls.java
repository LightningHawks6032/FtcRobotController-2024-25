package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.DCMotor;
import org.firstinspires.ftc.teamcode.hardware.IMotor;

/// Controls vertical slide (for hanging)
public class ArmControls {

    DCMotor sr, sl;
    Telemetry telemetry;
    public ArmControls(DCMotor _sr, DCMotor _sl, Telemetry _telemetry) {
        sr = _sr;
        sl = _sl;
        telemetry = _telemetry;

    }

    void moveWithPower(float power) {
        sr.setPower(power);
        sl.setPower(power);

    }

    public void loop(float power) {
        moveWithPower(power);
    }
}
