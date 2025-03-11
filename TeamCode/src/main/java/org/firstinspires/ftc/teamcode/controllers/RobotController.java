package org.firstinspires.ftc.teamcode.controllers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
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
    IMotor floorClaw, floorClawRotationLeft, floorClawRotationRight;
    IMotor floorClawSpin;

    public MotorControls motorControls;
    public ArmControls armControls;
    public ClawControls outtakeClawControls;
    public HorizontalSlideControls horizontalSlideControls;
    public PickupSlideControls outtakeSlideControls;
    public ClawControls intakeClawControls;
    public FloorClawRotationControls intakeClawRotationControls;
    public FloorClawSpinControls intakeClawSpinControls;
    public Telemetry telemetry;
    public RobotController(HardwareMap hardwareMap, Telemetry _telemetry){


        telemetry = _telemetry;

        moveMotorUpLeft = new DCMotor(hardwareMap.dcMotor.get("tl"), null, DcMotorSimple.Direction.REVERSE);
        moveMotorUpRight = new DCMotor(hardwareMap.dcMotor.get("br"), null, DcMotorSimple.Direction.REVERSE);
        moveMotorLowLeft = new DCMotor(hardwareMap.dcMotor.get("bl"), null, DcMotorSimple.Direction.FORWARD);
        moveMotorLowRight = new DCMotor(hardwareMap.dcMotor.get("tr"), null, DcMotorSimple.Direction.REVERSE);


        System.out.println( hardwareMap.servo.toString());
        DcMotor slideLeftTemp = hardwareMap.get(DcMotor.class, "sl" );
        DcMotor slideRightTemp = hardwareMap.get(DcMotor.class, "sr" );
        slideLeftTemp.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideRightTemp.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slideLeft = new DCMotor(slideLeftTemp, null, DcMotorSimple.Direction.FORWARD);
        slideRight = new DCMotor(slideRightTemp, null, DcMotorSimple.Direction.REVERSE);
        hangClaw = new ContinuousServo(hardwareMap.servo.get("hangClaw"));
        Servo pcl = hardwareMap.servo.get("pcl");
        pcl.setDirection(Servo.Direction.FORWARD);
        Servo pcr = hardwareMap.servo.get("pcr");
        pcr.setDirection(Servo.Direction.REVERSE);
        pickUpClawLeft = new ContinuousServo(pcl);
        pickUpClawRight = new ContinuousServo(pcr);
        DcMotor horizontalSlideMotor = hardwareMap.dcMotor.get("hs");
        horizontalSlideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        horizontalSlide = new DCMotor(horizontalSlideMotor, null, DcMotorSimple.Direction.FORWARD);

        floorClaw = new ContinuousServo(hardwareMap.servo.get("fc"));

        Servo floorClawRotationLeft1 = hardwareMap.servo.get("fcr");
        floorClawRotationLeft1.setDirection(Servo.Direction.FORWARD);
        Servo floorClawRotationRight1 = hardwareMap.servo.get("fcl");
        floorClawRotationRight1.setDirection(Servo.Direction.REVERSE);

        floorClawRotationLeft = new ContinuousServo(floorClawRotationLeft1);
        floorClawRotationRight = new ContinuousServo(floorClawRotationRight1);
        floorClawSpin = new ContinuousServo(hardwareMap.servo.get("floorclawspin"));

        motorControls = new MotorControls(moveMotorUpLeft, moveMotorUpRight, moveMotorLowLeft, moveMotorLowRight, telemetry);
        armControls = new ArmControls(slideLeft, slideRight, telemetry);
        outtakeClawControls = new ClawControls(hangClaw, 0.7f, 0.42f, telemetry);
        horizontalSlideControls = new HorizontalSlideControls(horizontalSlide, telemetry);
        outtakeSlideControls = new PickupSlideControls(pickUpClawLeft, pickUpClawRight, outtakeClawControls, telemetry);

        intakeClawControls = new ClawControls(floorClaw, 0.5f, 1f, telemetry);
        intakeClawRotationControls = new FloorClawRotationControls(floorClawRotationLeft, floorClawRotationRight, telemetry);
        intakeClawSpinControls = new FloorClawSpinControls(floorClawSpin, telemetry);
    }

    public void init() {
        motorControls.init();
    }
}
