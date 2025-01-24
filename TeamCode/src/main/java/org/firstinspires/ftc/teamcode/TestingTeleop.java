package org.firstinspires.ftc.teamcode;
//System.out.println("skibidi rizz"); :)
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.controllers.ClawControls;
import org.firstinspires.ftc.teamcode.controllers.ContinuousMotorControls;
import org.firstinspires.ftc.teamcode.controllers.GamepadController;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.hardware.ContinuousServo;
import org.firstinspires.ftc.teamcode.hardware.IMotor;

@TeleOp(name = "TeleOp")
public class TestingTeleop extends OpMode {
    GamepadController g1, g2;
    RobotController robot;

    @Override
    public void init() {
        g1 = new GamepadController(gamepad1);
        g2 = new GamepadController(gamepad2);

        robot = new RobotController(hardwareMap, telemetry);
        robot.init();
    }

    @Override
    public void loop() {
        robot.loop(new Vec2Rot(g1.leftStick(), g1.rightStick().x), g1.leftTrigger(),  (g2.pressedA() ? -1 : 0)  + (g2.pressedB() ? 1 : 0),(g1.pressedX() ? -1 : 0)  + (g1.pressedY() ? 1 : 0), (int)Math.signum(g2.leftStickY()), g2.horizontalDPad() == 1, (int)Math.signum(g2.rightStickY()), g2.horizontalDPad() == -1);
    }
}
