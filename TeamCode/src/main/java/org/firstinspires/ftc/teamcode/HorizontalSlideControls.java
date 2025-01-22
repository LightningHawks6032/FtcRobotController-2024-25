package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class HorizontalSlideControls {
    IMotor c;
    Telemetry telemetry;

    public HorizontalSlideControls(IMotor _c, Telemetry _telemetry) {
        c = _c;
        telemetry = _telemetry;
    }

    public void loop(int power) {
        float p = (1 + Math.signum(power)) / 2f;
        c.setPower(power/2f);
    }
}
