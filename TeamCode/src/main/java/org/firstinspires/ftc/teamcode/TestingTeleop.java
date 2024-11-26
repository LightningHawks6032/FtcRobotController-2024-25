package org.firstinspires.ftc.teamcode;
//System.out.println("skibidi rizz"); :)
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "TeleOp")
public class TestingTeleop extends OpMode {
    DCMotor motorupleft;
    DCMotor motorupright;
    DCMotor motorlowleft;
    DCMotor motorlowright;
    DCMotor sl;
    DCMotor sr;
    DCMotor motor;
    MotorControls m;
    GamepadController g1, g2;
    ArmControls a;
    ElapsedTime time;
    @Override
    public void init() {
        motorupleft = new DCMotor(hardwareMap.dcMotor.get("motor 1"), null);
        motorupright = new DCMotor(hardwareMap.dcMotor.get("motor 2"), null);
        motorlowleft = new DCMotor(hardwareMap.dcMotor.get("motor 3"), null);
        motorlowright = new DCMotor(hardwareMap.dcMotor.get("motor 4"), null);
        sl = new DCMotor(hardwareMap.get(DcMotor.class, "sl" ), null);
        sr = new DCMotor(hardwareMap.get(DcMotor.class, "sr" ), null);
        g1 = new GamepadController(gamepad1);
        g2 = new GamepadController(gamepad2);
        m = new MotorControls(motorupleft, motorupright, motorlowleft, motorlowright, telemetry);
        a = new ArmControls(sr, sl, telemetry);
        time = new ElapsedTime();
        time.reset();
    }

    @Override
    public void loop() {
        m.loop(new Vec2Rot(g1.leftStick(), g1.rightStick().x));
        if (g2.pressedB()){
            a.goDown();
        }
        else if (g2.pressedA())
        {
            a.goUp();
        }
        a.loop(g2.leftStickY(), (float) time.seconds());
        time.reset();
    }
}
