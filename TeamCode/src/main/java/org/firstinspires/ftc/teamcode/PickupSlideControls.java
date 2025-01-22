package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

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
        setPower(0.8f);
        up = true;

    }

    public void loop (int power) {
        /*float p = (1 + Math.signum(power)) / 2f;
        cr.setPower(p);
        cl.setPower(1-p);*/
        if (-power > 0 && !up) {goUp();}
        else if (-power < 0 && up) {goDown();}
        telemetry.addData("powr", cl.getPosition() + ", " + cr.getPosition());
    }

}
