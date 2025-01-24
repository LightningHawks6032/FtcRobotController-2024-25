package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.IMotor;

public class HorizontalSlideControls {
    IMotor c;
    Telemetry telemetry;

    public HorizontalSlideControls(IMotor _c, Telemetry _telemetry) {
        c = _c;
        telemetry = _telemetry;
    }

    public void loop(int power) {
        c.setPower(power/2f);
    }
}
