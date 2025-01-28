package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.DCMotor;
import org.firstinspires.ftc.teamcode.hardware.IMotor;


public class WinchControls {
    DCMotor c;
    Telemetry telemetry;

    public WinchControls(DCMotor _c, Telemetry _telemetry) {
        c = _c;
        telemetry = _telemetry;
        locked = false;
    }

    public void lock() {
        //c.lock();
    }
    boolean locked;
    public void loop(float power, boolean lock) {
        if (!locked) {
            if (lock) {
                lock();
                locked = true;
            }
            c.setPower(power);
        }
    }
}

