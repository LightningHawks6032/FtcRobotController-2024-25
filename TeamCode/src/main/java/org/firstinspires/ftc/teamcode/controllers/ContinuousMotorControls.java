package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.IMotor;

public class ContinuousMotorControls {
    IMotor c;
    Telemetry telemetry;

    public ContinuousMotorControls(IMotor _c, Telemetry _telemetry) {
        c = _c;
        telemetry = _telemetry;
    }

    public void loop(float power) {
        c.setPower(power);
    }
}
