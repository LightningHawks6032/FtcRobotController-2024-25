package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class RobotController {
    IMotor moveMotorUpLeft;
    IMotor moveMotorUpRight;
    IMotor moveMotorLowLeft;
    IMotor moveMotorLowRight;
    IMotor slideRight;
    IMotor slideLeft;
    IMotor hangClaw;
    IMotor pickUpClawLeft;
    IMotor pickUpClawRight;
    IMotor horizontalSlide;
    IMotor floorClaw, floorClawRotation;

    public MotorControls motorControls;
    public ArmControls armControls;
    public HangClawControls hangClawControls;
    public HorizontalSlideControls horizontalSlideControls;
    public PickupSlideControls pickupSlideControls;
    public FloorClawControls floorClawControls;
    public FloorClawRotationControls floorClawRotationControls;
    Telemetry telemetry;
    // TODO: PickupSlideControls
    Tests.MotorTest motorTest;
    public RobotController(HardwareMap hardwareMap, Telemetry _telemetry){


        telemetry = _telemetry;

        moveMotorUpLeft = new DCMotor(hardwareMap.dcMotor.get("tl"), null, DcMotorSimple.Direction.REVERSE);
        moveMotorUpRight = new DCMotor(hardwareMap.dcMotor.get("br"), null, DcMotorSimple.Direction.REVERSE);
        moveMotorLowLeft = new DCMotor(hardwareMap.dcMotor.get("bl"), null, DcMotorSimple.Direction.FORWARD);
        moveMotorLowRight = new DCMotor(hardwareMap.dcMotor.get("tr"), null, DcMotorSimple.Direction.REVERSE);


        System.out.println( hardwareMap.servo.toString());
        slideLeft = new DCMotor(hardwareMap.get(DcMotor.class, "sl" ), null, DcMotorSimple.Direction.FORWARD);
        slideRight = new DCMotor(hardwareMap.get(DcMotor.class, "sr" ), null, DcMotorSimple.Direction.REVERSE);
        hangClaw = new ContinuousServo(hardwareMap.servo.get("hangClaw"));//DebugMotor("cl", telemetry, null);
        Servo pcl = hardwareMap.servo.get("pcl");
        pcl.setDirection(Servo.Direction.REVERSE);
        Servo pcr = hardwareMap.servo.get("pcr");
        pcr.setDirection(Servo.Direction.REVERSE);
        pickUpClawLeft = new ContinuousServo(pcl);//DebugMotor("cr", telemetry, null);
        pickUpClawRight = new ContinuousServo(pcr);//DebugMotor("cr", telemetry, null);
        horizontalSlide = new DCMotor(hardwareMap.dcMotor.get("hs"), null, DcMotorSimple.Direction.FORWARD);

        Servo fc = hardwareMap.servo.get("fc");
        floorClaw = new ContinuousServo(fc);
        floorClawRotation = new ContinuousServo(hardwareMap.servo.get("fcr"));

        //floor claw, floor claw rotation

        motorControls = new MotorControls(moveMotorUpLeft, moveMotorUpRight, moveMotorLowLeft, moveMotorLowRight, telemetry);
        armControls = new ArmControls(slideLeft, slideRight, telemetry);
        hangClawControls = new HangClawControls(hangClaw, telemetry);
        horizontalSlideControls = new HorizontalSlideControls(horizontalSlide, telemetry);
        pickupSlideControls = new PickupSlideControls(pickUpClawLeft, pickUpClawRight, telemetry);

        floorClawControls = new FloorClawControls(floorClaw, telemetry);
        floorClawRotationControls = new FloorClawRotationControls(floorClawRotation, telemetry);

        //motorTest = new Tests.MotorTest(moveMotorUpLeft, moveMotorUpRight, moveMotorLowRight, moveMotorLowLeft, telemetry);
    }

    public void init() {
        motorControls.init();
        armControls.init();
    }

    public void loop(Vec2Rot moveDirection, boolean slowMode, float verticalArmControlPower, int hangClawControlPower, int horizontalSlideControlPower, int pickUpSlideControlPower, int floorClawControlPower, int floorClawRotationControlPower, int hangClawPower, float dt) {
        motorControls.loop(moveDirection, slowMode);
        armControls.loop(verticalArmControlPower, dt);
        hangClawControls.loop(hangClawPower);
        horizontalSlideControls.loop(horizontalSlideControlPower);
        pickupSlideControls.loop(pickUpSlideControlPower);
        floorClawControls.loop(floorClawControlPower);
        floorClawRotationControls.loop(floorClawRotationControlPower);
    }
}
