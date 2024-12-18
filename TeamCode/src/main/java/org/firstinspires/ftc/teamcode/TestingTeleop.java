package org.firstinspires.ftc.teamcode;
//System.out.println("skibidi rizz"); :)
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "TeleOp")
public class TestingTeleop extends OpMode {
    IMotor motorupleft;
    IMotor motorupright;
    IMotor motorlowleft;
    IMotor motorlowright;
    IMotor sl;
    IMotor sr;
    IMotor cl;
    IMotor cr;
    MotorControls m;
    GamepadController g1, g2;
    ArmControls a;
    HangClawControls h;
    ElapsedTime time;
    @Override
    public void init() {
        motorupleft = new DCMotor(hardwareMap.dcMotor.get("motor 1"), null);
        motorupright = new DCMotor(hardwareMap.dcMotor.get("motor 2"), null);
        motorlowleft = new DCMotor(hardwareMap.dcMotor.get("motor 3"), null);
        motorlowright = new DCMotor(hardwareMap.dcMotor.get("motor 4"), null);
        sl = new DCMotor(hardwareMap.get(DcMotor.class, "sl" ), null);
        sr = new DCMotor(hardwareMap.get(DcMotor.class, "sr" ), null);
        cl = new ContinuousServo(hardwareMap.servo.get("servo 1"));//DebugMotor("cl", telemetry, null);
        cr = new ContinuousServo(hardwareMap.servo.get("servo 2"));//DebugMotor("cr", telemetry, null);
        g1 = new GamepadController(gamepad1);
        g2 = new GamepadController(gamepad2);
        m = new MotorControls(motorupleft, motorupright, motorlowleft, motorlowright, telemetry);
        a = new ArmControls(sr, sl, telemetry);
        h = new HangClawControls(cl, cr, telemetry);
        time = new ElapsedTime();
        time.reset();
        m.init();
        a.init();
    }

    @Override
    public void loop() {
        m.loop(new Vec2Rot(g1.leftStick(), g1.rightStick().x));
        h.loop((g2.pressedA() ? -1 : 0)  + (g2.pressedB() ? 1 : 0));
        a.loop(g2.leftStickY(), (float) time.seconds());
        time.reset();
    }
}
