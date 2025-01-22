package org.firstinspires.ftc.teamcode;
//System.out.println("skibidi rizz"); :)
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "TeleOp")
public class TestingTeleop extends OpMode {
    /*IMotor motorupleft;
    IMotor motorupright;
    IMotor motorlowleft;
    IMotor motorlowright;
    IMotor sl;
    IMotor sr;
    IMotor cl;
    IMotor cr;
    MotorControls m;

    ArmControls a;
    HangClawControls h;
    */
    ElapsedTime time;
    GamepadController g1, g2;
    RobotController robot;
    @Override
    public void init() {

        time = new ElapsedTime();
        g1 = new GamepadController(gamepad1);
        g2 = new GamepadController(gamepad2);

        robot = new RobotController(hardwareMap, telemetry);
        robot.init();
    }

    @Override
    public void loop() {
        robot.loop(new Vec2Rot(g1.leftStick(), g1.rightStick().x), g2.leftStickY(), (g2.pressedA() ? -1 : 0)  + (g2.pressedB() ? 1 : 0),(g2.pressedX() ? -1 : 0)  + (g2.pressedY() ? 1 : 0), (int)Math.signum(g2.rightStickY()), g2.bumper(), (int)g2.trigger(), g2.horizontalDPad(), (float) time.seconds());
        time.reset();
        //robot.motorTest.loop(g1.pressedA(), g1.pressedB(), g1.pressedX(), g1.pressedY());
    }
}
