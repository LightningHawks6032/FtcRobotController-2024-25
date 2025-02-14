package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Toggle;

@TeleOp(name="ToggleTest", group="Test")
public class ToggleTest extends OpMode{
    Toggle toggle;
    public void init() {
        toggle = new Toggle(false);

    }
    public void loop() {
        toggle.loop(gamepad1.a);
        telemetry.addData("State", toggle.state);
        telemetry.addData("Released", toggle.released);
        telemetry.addData("Button", gamepad1.a);
    }
}
