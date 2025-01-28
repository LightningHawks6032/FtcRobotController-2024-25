package org.firstinspires.ftc.teamcode;
//System.out.println("skibidi rizz"); :)
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controllers.GamepadController;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;

@TeleOp(name = "TeleOp", group="Comp")
public class TestingTeleop extends OpMode {
    GamepadController g1, g2;
    RobotController robot;

    @Override
    public void init() {
        g1 = new GamepadController(gamepad1);
        g2 = new GamepadController(gamepad2);

        robot = new RobotController(hardwareMap, telemetry);
        robot.init();

        ActionSequencer.StickAction.Data a1 = new ActionSequencer.StickAction.DataBuilder().stick(new Vec2(1, 0)).get();
    }

    @Override
    public void loop() {
        robot.loop(new Vec2Rot(g1.leftStick(), g1.rightStick().x), g1.leftTrigger(),  (g2.pressedB() ? -1 : 0)  + (g2.pressedA() ? 1 : 0),(g1.pressedX() ? -1 : 0)  + (g1.pressedY() ? 1 : 0), (int)Math.signum(g2.leftStickY()), g2.horizontalDPad() == 1, (int)Math.signum(g2.rightStickY()), g2.horizontalDPad() == -1);
    }
}
