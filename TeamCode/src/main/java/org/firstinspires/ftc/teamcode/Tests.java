package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.IMotor;

public class Tests {
    public static class MotorTest {
        IMotor ul, ur, lr, ll;
        Telemetry telemetry;
        public MotorTest(IMotor _ul, IMotor _ur, IMotor _lr, IMotor _ll, Telemetry _telemetry) {
            ul=_ul;
            ur=_ur;
            lr=_lr;
            ll=_ll;
            telemetry=_telemetry;
        }

        public void ul(float power) {
            telemetry.addData("ul power", power);
            ul.setPower(power);
        }
        public void ur(float power) {
            telemetry.addData("ur power", power);
            ur.setPower(power);
        }
        public void lr(float power) {
            telemetry.addData("lr power", power);
            lr.setPower(power);
        }
        public void ll(float power) {
            telemetry.addData("ll power", power);
            ll.setPower(power);
        }
        public void loop(boolean a, boolean b, boolean x, boolean y) {
            ll(a?1:0);
            lr(b?1:0);
            ul(x?1:0);
            ur(y?1:0);
        }
    }
}
