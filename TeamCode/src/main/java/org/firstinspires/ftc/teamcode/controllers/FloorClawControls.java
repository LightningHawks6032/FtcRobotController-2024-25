package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.IMotor;

public class FloorClawControls {
    IMotor c;
    Telemetry telemetry;
    boolean isOpen;
    public FloorClawControls(IMotor _c, Telemetry _telemetry) {
        c = _c;
        telemetry = _telemetry;
        isOpen = true;
    }

    void close() {
        c.setPower(0.65f);
        isOpen = false;
    }
    void open() {
        c.setPower(0.5f);
        isOpen = true;
    }


    public void loop(int power) {
        if (power > 0 && !isOpen) {open();}
        else if (power < 0 && isOpen) {close();}
    }
}
