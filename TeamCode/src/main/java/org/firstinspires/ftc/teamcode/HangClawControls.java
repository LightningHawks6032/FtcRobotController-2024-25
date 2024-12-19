package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class HangClawControls {
    IMotor cl, cr;
    Telemetry telemetry;

    public HangClawControls(IMotor _cl, IMotor _cr, Telemetry _telemetry) {
        cl = _cl;
        cr = _cr;
        telemetry = _telemetry;
    }

    public void loop(int power) {
        float p = Math.signum(power);
        cl.setPower(p);
        cr.setPower(-p);
    }
}
