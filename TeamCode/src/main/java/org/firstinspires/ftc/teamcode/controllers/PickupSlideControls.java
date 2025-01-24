package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.IMotor;

public class PickupSlideControls {
    IMotor cr, cl;
    Telemetry telemetry;
    boolean up;

    public PickupSlideControls(IMotor _cl, IMotor _cr, Telemetry _telemetry) {
        cr = _cr;
        cl = _cl;
        telemetry = _telemetry;
        up = false;
    }
    void setPower(float p) {
        cr.setPower(p);
        cl.setPower(p);
    }
    void goDown() {
        setPower(0.3f);

        up = false;
    }
    void goUp() {
        setPower(1f);
        up = true;

    }

    public void loop (int power) {
        if (power < 0 && !up) {goUp();}
        else if (power > 0 && up) {goDown();}
    }

}
