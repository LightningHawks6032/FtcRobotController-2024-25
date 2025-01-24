package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class FloorClawRotationControls {
    IMotor c;
    Telemetry telemetry;
    boolean isOpen;

    public FloorClawRotationControls(IMotor _c, Telemetry _telemetry) {
        c = _c;
        telemetry = _telemetry;
        isOpen = false;
    }

    void open() {
        c.setPower(0f);
        System.out.println("Opening");
        isOpen = true;
    }
    void close() {
        c.setPower(1);
        isOpen = false;
    }


    public void loop(int power) {
        /*float p = (1 + Math.signum(power)) / 2f;
        c.setPower(p);*/
        telemetry.addData("BYE", power);
        /*if (power > 0 && !isOpen) {open();}
        else if (power < 0 && isOpen) {close();}*/
        c.setPower((power + 1)/2f);
    }
}
