package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

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

    public MotorControls motorControls;
    public ArmControls armControls;
    public HangClawControls hangClawControls;
    public HorizontalSlideControls horizontalSlideControls;
    public PickupSlideControls pickupSlideControls;
    Telemetry telemetry;
    // TODO: PickupSlideControls

    public RobotController(HardwareMap hardwareMap, Telemetry _telemetry){


        telemetry = _telemetry;

        moveMotorUpLeft = new DebugMotor("moveMotorUpLeft", telemetry, null);//DCMotor(hardwareMap.dcMotor.get("motor 1"), null);
        moveMotorUpRight = new DebugMotor("moveMotorUpRight", telemetry, null);//DCMotor(hardwareMap.dcMotor.get("motor 2"), null);
        moveMotorLowLeft = new DebugMotor("moveMotorLowLeft", telemetry, null);//DCMotor(hardwareMap.dcMotor.get("motor 3"), null);
        moveMotorLowRight = new DebugMotor("moveMotorLowRight", telemetry, null);//DCMotor(hardwareMap.dcMotor.get("motor 4"), null);
        slideLeft = new DebugMotor("slideLeft", telemetry, null);//DCMotor(hardwareMap.get(DcMotor.class, "sl" ), null);
        slideRight = new DebugMotor("slideRight", telemetry, null);//DCMotor(hardwareMap.get(DcMotor.class, "sr" ), null);
        hangClaw = new DebugMotor("hangClaw", telemetry, null);//ContinuousServo(hardwareMap.servo.get("servo 1"));//DebugMotor("cl", telemetry, null);
        pickUpClawLeft = new DebugMotor("pickUpClawLeft", telemetry, null);//ContinuousServo(hardwareMap.servo.get("servo 2"));//DebugMotor("cr", telemetry, null);
        pickUpClawRight = new DebugMotor("puckUpClawRight", telemetry, null);//ContinuousServo(hardwareMap.servo.get("servo 3"));//DebugMotor("cr", telemetry, null);
        horizontalSlide = new DebugMotor("horizontalSlide", telemetry, null);//ContinuousServo(hardwareMap.servo.get("servo 4"));//DebugMotor("cr", telemetry, null);

        motorControls = new MotorControls(moveMotorUpLeft, moveMotorUpRight, moveMotorLowLeft, moveMotorLowRight, telemetry);
        armControls = new ArmControls(slideLeft, slideRight, telemetry);
        hangClawControls = new HangClawControls(hangClaw, telemetry);
        horizontalSlideControls = new HorizontalSlideControls(horizontalSlide, telemetry);
        pickupSlideControls = new PickupSlideControls(pickUpClawLeft, pickUpClawRight, telemetry);
    }

    public void init() {
        motorControls.init();
        armControls.init();
    }

    public void loop(Vec2Rot moveDirection, float verticalArmControlPower, int hangClawControlPower, int horizontalSlideControlPower, int pickUpSlideControlPower, float dt) {
        motorControls.loop(moveDirection);
        armControls.loop(verticalArmControlPower, dt);
        hangClawControls.loop(hangClawControlPower);
        horizontalSlideControls.loop(horizontalSlideControlPower);
        pickupSlideControls.loop(pickUpSlideControlPower);
    }
}
