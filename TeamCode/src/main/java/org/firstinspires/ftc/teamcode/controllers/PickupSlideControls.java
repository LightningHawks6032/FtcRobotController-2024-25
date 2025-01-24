package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.IMotor;

public class PickupSlideControls {
    IMotor cr, cl;
    Telemetry telemetry;
    boolean up;

    ClawControls claw;

    public PickupSlideControls(IMotor _cl, IMotor _cr, ClawControls _claw, Telemetry _telemetry) {
        cr = _cr;
        cl = _cl;
        telemetry = _telemetry;
        claw = _claw;
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

    public void loop (int slidePower, boolean clawPower) {
        if (slidePower < 0 && !up) {
            goUp();
            claw.ensureClosed();
        }
        else if (slidePower > 0 && up) {
            goDown();
            claw.ensureClosed();
            claw.openAfter(2500);
        }
        claw.loop(clawPower);
    }

}
