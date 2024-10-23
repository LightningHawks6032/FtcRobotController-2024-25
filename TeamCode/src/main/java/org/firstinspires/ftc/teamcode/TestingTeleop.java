package org.firstinspires.ftc.teamcode;
//System.out.println("skibidi rizz"); :)
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
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
    @Override
    public void init() {
        motorupleft = hardwareMap.dcMotor.get("motor 1");
        motorupright = hardwareMap.dcMotor.get("motor 2");
        motorlowleft = hardwareMap.dcMotor.get("motor 3");
        motorlowright = hardwareMap.dcMotor.get("motor 4");
        m = new MotorControls(motorupleft, motorupright, motorlowleft, motorlowright, new Controller(gamepad1), telemetry);
    }

    @Override
    public void loop() {
        m.loop();
    }
}
