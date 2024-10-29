package org.firstinspires.ftc.teamcode;
//System.out.println("skibidi rizz"); :)
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
@TeleOp(name = "Teleop")
public class TestingTeleop extends OpMode {
    DCMotor motorupleft;
    DCMotor motorupright;
    DCMotor motorlowleft;
    DCMotor motorlowright;
    //DcMotor slideleft;
    //DcMotor slideright;
    DCMotor motor;
    MotorControls m;
    GamepadController c;
    @Override
    public void init() {
        motorupleft = new DCMotor(hardwareMap.dcMotor.get("motor 1"), null);
        motorupright = new DCMotor(hardwareMap.dcMotor.get("motor 2"), null);
        motorlowleft = new DCMotor(hardwareMap.dcMotor.get("motor 3"), null);
        motorlowright = new DCMotor(hardwareMap.dcMotor.get("motor 4"), null);
        c = new GamepadController(gamepad1);
        m = new MotorControls(motorupleft, motorupright, motorlowleft, motorlowright, telemetry);
    }

    @Override
    public void loop() {
        m.loop(new Vec2Rot(c.leftStick(), c.rightStick().x));
    }
}
