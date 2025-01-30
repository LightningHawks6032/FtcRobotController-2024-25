package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.controllers.GamepadController;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;
import org.firstinspires.ftc.teamcode.scheduling.InputResponseManager;

@TeleOp(name = "Scheduling Test", group = "Test")
public class SchedulingTest extends OpMode {

    GamepadController g1;
    InputResponseManager manager;
    RobotController robot;
    static Telemetry _telemetry;

    public static class TelemetryOnStick extends ActionSequencer.StickAction {
        public void loop(RobotController robot, Data data) {
            _telemetry.addData("Stick", data.stick.y);
        }
    }

    @Override
    public void init() {
        g1 = new GamepadController(gamepad1);
        manager = new InputResponseManager.Builder(g1, robot).leftStickAction(
                new TelemetryOnStick()
        ).get();
        _telemetry = telemetry;
    }

    @Override
    public void loop() {
        manager.loop();
    }
}
