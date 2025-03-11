package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.util.Vec2Rot;
import org.firstinspires.ftc.teamcode.controllers.MotorControls;
import org.firstinspires.ftc.teamcode.hardware.DCMotor;
import org.firstinspires.ftc.teamcode.hardware.IMotor;

@TeleOp(name="DriveTrain", group="Test")
public class DriveTrain extends OpMode {
    IMotor moveMotorUpLeft;
    IMotor moveMotorUpRight;
    IMotor moveMotorLowLeft;
    IMotor moveMotorLowRight;
    MotorControls motorControls;

    @Override
    public void init() {
        moveMotorUpLeft = new DCMotor(hardwareMap.dcMotor.get("tl"), null, DcMotorSimple.Direction.FORWARD);
        moveMotorUpRight = new DCMotor(hardwareMap.dcMotor.get("br"), null, DcMotorSimple.Direction.REVERSE);
        moveMotorLowLeft = new DCMotor(hardwareMap.dcMotor.get("bl"), null, DcMotorSimple.Direction.FORWARD);
        moveMotorLowRight = new DCMotor(hardwareMap.dcMotor.get("tr"), null, DcMotorSimple.Direction.REVERSE);

        motorControls = new MotorControls(moveMotorUpLeft, moveMotorUpRight, moveMotorLowLeft, moveMotorLowRight, telemetry);
    }

    @Override
    @Deprecated
    public void loop() {
        //motorControls.loop(new Vec2Rot(gamepad1.left_stick_x * 0.5f, gamepad1.left_stick_y * 0.5f, gamepad1.right_stick_x * 0.5f), 0);
    }
}
