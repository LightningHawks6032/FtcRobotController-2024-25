package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class PickupSlideControls {
    IMotor cr, cl;
    Telemetry telemetry;

    public PickupSlideControls(IMotor _cr, IMotor _cl, Telemetry _telemetry) {
        cr = _cr;
        cl = _cl;
        telemetry = _telemetry;

    }

    public void loop (int power) {
        float p = (1 + Math.signum(power)) / 2f;
        cr.setPower(p);
        cl.setPower(1-p);
    }

}
