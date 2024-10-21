package org.firstinspires.ftc.teamcode;
//System.out.println("skibidi rizz"); :)
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.function.Function;

@TeleOp(name = "Testing")
public class TestingTeleop extends OpMode {
    DcMotor motorupleft;
    DcMotor motorupright;
    DcMotor motorlowleft;
    DcMotor motorlowright;
    //DcMotor slideleft;
    //DcMotor slideright;
    DcMotor motor;
    MotorControls m;
    Function<Float, Float>[] funcs = new Function[3];
    int currFunc = 0;
    @Override
    public void init() {
        motorupleft = hardwareMap.dcMotor.get("motor 1");
        motorupright = hardwareMap.dcMotor.get("motor 2");
        motorlowleft = hardwareMap.dcMotor.get("motor 3");
        motorlowright = hardwareMap.dcMotor.get("motor 4");
        m = new MotorControls(motorupleft, motorupright, motorlowleft, motorlowright, new Controller(gamepad1));
        funcs[0] = (Float a) -> {
            if (Math.abs(a) <= 0.2f) {
                return 0f;
            }
            return 0.5f * a;
        };
        funcs[1] = (Float a) -> {
            if (Math.abs(a) <= 0.2f) {
                return 0f;
            }
            return Math.signum(a) * (float) (0.54 * Math.log10(Math.abs(a)) + 0.47);
        };
        funcs[2] = (Float a) -> {
            if (Math.abs(a) <= 0.2f) {
                return 0f;
            }
            return Math.signum(a) * (float) (0.048 * Math.pow(10, Math.abs(a)) + 0.025);
        };
    }

    @Override
    public void loop() {
        m.loop();
        if (gamepad1.right_bumper) {
            currFunc = (currFunc + 1) % 3;
        }
    }
}
