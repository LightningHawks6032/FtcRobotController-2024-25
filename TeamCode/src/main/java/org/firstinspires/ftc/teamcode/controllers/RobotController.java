package org.firstinspires.ftc.teamcode.controllers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.AutoSequence;
import org.firstinspires.ftc.teamcode.Tests;
import org.firstinspires.ftc.teamcode.Vec2Rot;
import org.firstinspires.ftc.teamcode.hardware.ContinuousServo;
import org.firstinspires.ftc.teamcode.hardware.DCMotor;
import org.firstinspires.ftc.teamcode.hardware.IMotor;

public class RobotController {
    IMotor moveMotorUpLeft;
    IMotor moveMotorUpRight;
    IMotor moveMotorLowLeft;
    IMotor moveMotorLowRight;
    DCMotor slideRight;
    DCMotor slideLeft;
    IMotor hangClaw;
    IMotor pickUpClawLeft;
    IMotor pickUpClawRight;
    IMotor horizontalSlide;
    IMotor floorClaw, floorClawRotation;

    public MotorControls motorControls;
    public ArmControls armControls;
    public ClawControls hangClawControls;
    public HorizontalSlideControls horizontalSlideControls;
    public PickupSlideControls pickupSlideControls;
    public ClawControls floorClawControls;
    public FloorClawRotationControls floorClawRotationControls;
    Telemetry telemetry;
    public RobotController(HardwareMap hardwareMap, Telemetry _telemetry){


        telemetry = _telemetry;

        moveMotorUpLeft = new DCMotor(hardwareMap.dcMotor.get("tl"), null, DcMotorSimple.Direction.REVERSE);
        moveMotorUpRight = new DCMotor(hardwareMap.dcMotor.get("br"), null, DcMotorSimple.Direction.REVERSE);
        moveMotorLowLeft = new DCMotor(hardwareMap.dcMotor.get("bl"), null, DcMotorSimple.Direction.FORWARD);
        moveMotorLowRight = new DCMotor(hardwareMap.dcMotor.get("tr"), null, DcMotorSimple.Direction.REVERSE);


        System.out.println( hardwareMap.servo.toString());
        slideLeft = new DCMotor(hardwareMap.get(DcMotor.class, "sl" ), null, DcMotorSimple.Direction.FORWARD);
        slideRight = new DCMotor(hardwareMap.get(DcMotor.class, "sr" ), null, DcMotorSimple.Direction.REVERSE);
        hangClaw = new ContinuousServo(hardwareMap.servo.get("hangClaw"));
        Servo pcl = hardwareMap.servo.get("pcl");
        pcl.setDirection(Servo.Direction.REVERSE);
        Servo pcr = hardwareMap.servo.get("pcr");
        pcr.setDirection(Servo.Direction.REVERSE);
        pickUpClawLeft = new ContinuousServo(pcl);
        pickUpClawRight = new ContinuousServo(pcr);
        horizontalSlide = new DCMotor(hardwareMap.dcMotor.get("hs"), null, DcMotorSimple.Direction.FORWARD);

        floorClaw = new ContinuousServo(hardwareMap.servo.get("fc"));
        floorClawRotation = new ContinuousServo(hardwareMap.servo.get("fcr"));

        motorControls = new MotorControls(moveMotorUpLeft, moveMotorUpRight, moveMotorLowLeft, moveMotorLowRight, telemetry);
        armControls = new ArmControls(slideLeft, slideRight, telemetry);
        hangClawControls = new ClawControls(hangClaw, -0.1f, 0.35f, telemetry);
        horizontalSlideControls = new HorizontalSlideControls(horizontalSlide, telemetry);
        pickupSlideControls = new PickupSlideControls(pickUpClawLeft, pickUpClawRight, hangClawControls, telemetry);

        floorClawControls = new ClawControls(floorClaw, 0.5f, 0.65f, telemetry);
        floorClawRotationControls = new FloorClawRotationControls(floorClawRotation, telemetry);

    }

    public void init() {
        motorControls.init();
    }

    public void loop(Vec2Rot moveDirection, float slowMode, float verticalArmControlPower, int horizontalSlideControlPower, int pickUpSlideControlPower, boolean floorClawControlPower, int floorClawRotationControlPower, boolean hangClawPower) {
        motorControls.loop(moveDirection, slowMode);
        armControls.loop(verticalArmControlPower);
        horizontalSlideControls.loop(horizontalSlideControlPower);
        pickupSlideControls.loop(pickUpSlideControlPower, hangClawPower);
        floorClawControls.loop(floorClawControlPower);
        floorClawRotationControls.loop(floorClawRotationControlPower);
    }

    public void loop(AutoSequence.ActionInput input) {
        loop(input.moveDirection, input.slowMode, input.verticalArmControlPower, input.horizontalSlideControlPower, input.pickUpSlideControlPower, input.floorClawControlPower, input.floorClawRotationControlPower, input.hangClawPower);
    }
}
