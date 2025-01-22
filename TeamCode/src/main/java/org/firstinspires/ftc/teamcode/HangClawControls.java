package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

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
        /*float p = (1 + Math.signum(power)) / 2f;
        c.setPower(p);*/
        if (power > 0 && !isOpen) {open();}
        else if (power < 0 && isOpen) {close();}
    }
}
