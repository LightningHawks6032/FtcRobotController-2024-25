package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.controllers.ClawControls;
import org.firstinspires.ftc.teamcode.controllers.FloorClawRotationControls;
import org.firstinspires.ftc.teamcode.hardware.ContinuousServo;
import org.firstinspires.ftc.teamcode.hardware.IMotor;

@TeleOp(group = "Test")
public class FloorClawTest extends OpMode {


    public ClawControls floorClawControls;
    public FloorClawRotationControls floorClawRotationControls;
    IMotor floorClaw, floorClawRotationLeft, floorClawRotationRight;
    IMotor floorClawSpin;
    @Override
    public void init() {
        floorClaw = new ContinuousServo(hardwareMap.servo.get("fc"));
        Servo left = hardwareMap.servo.get("fcr");
        left.setDirection(Servo.Direction.FORWARD);
        Servo right = hardwareMap.servo.get("fcl");
        right.setDirection(Servo.Direction.REVERSE);
        floorClawRotationLeft = new ContinuousServo(left);
        floorClawRotationRight = new ContinuousServo(right);
        //floorClawSpin = new ContinuousServo(hardwareMap.servo.get("floorclawspin"));

        floorClawControls = new ClawControls(floorClaw, 0.5f, 1f, telemetry);
        floorClawRotationControls = new FloorClawRotationControls(floorClawRotationLeft, floorClawRotationRight, telemetry);

    }

    @Override
    @Deprecated
    public void loop() {
        boolean floorClawControlPower = gamepad2.dpad_right;
        //floorClawControls.loop(floorClawControlPower);
        //floorClawRotationControls.loop(gamepad2.a);
    }
}
