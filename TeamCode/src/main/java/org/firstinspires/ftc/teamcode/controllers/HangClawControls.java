package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.IMotor;

public class HangClawControls {
    IMotor c;
    Telemetry telemetry;
    boolean isOpen;
    public HangClawControls(IMotor _c, Telemetry _telemetry) {
        c = _c;
        telemetry = _telemetry;
        isOpen = true;
    }

    void close() {
        c.setPower(0.3f);
        isOpen = false;
    }
    void open() {
        c.setPower(-0.1f);
        isOpen = true;
    }


    public void loop(int power) {
        if (power > 0 && !isOpen) {open();}
        else if (power < 0 && isOpen) {close();}
    }
}
