package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.IMotor;

/// Controls vertical slide (for hanging)
public class ArmControls {

    IMotor sr, sl;
    Telemetry telemetry;
    public ArmControls(IMotor _sr, IMotor _sl, Telemetry _telemetry) {
        sr = _sr;
        sl = _sl;
        telemetry = _telemetry;

    }

    void moveWithPower(float power) {
        sr.setPower(power);
        sl.setPower(power);
    }

    public void loop(float power) {
        moveWithPower(power * 075f);
    }
}
