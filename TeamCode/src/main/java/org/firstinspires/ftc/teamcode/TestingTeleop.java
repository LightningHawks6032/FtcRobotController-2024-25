package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Testing")
public class TestingTeleop extends OpMode {

    DcMotor motorupleft;
    DcMotor motorupright;
    DcMotor motorlowleft;
    DcMotor motorlowright;
    //DcMotor slideleft;
    //DcMotor slideright;


    @Override
    public void init() {

        motorupleft = hardwareMap.dcMotor.get("motor 1");
        motorupright = hardwareMap.dcMotor.get("motor 2");
        motorlowleft = hardwareMap.dcMotor.get("motor 3");
        motorlowright = hardwareMap.dcMotor.get("motor 4");

        //slideleft = hardwareMap.dcMotor.get("leftslide");
        //slideright = hardwareMap.dcMotor.get("rightslide");

        motorupleft.setDirection(DcMotor.Direction.FORWARD);
        motorlowleft.setDirection(DcMotor.Direction.FORWARD);
        motorupright.setDirection(DcMotor.Direction.REVERSE);
        motorlowright.setDirection(DcMotor.Direction.REVERSE);
        //slideleft.setDirection(DcMotor.Direction.FORWARD);


    }

    @Override
    public void loop() {

        motorupleft.setPower(gamepad1.left_stick_y);
        motorupright.setPower(gamepad1.right_stick_y);
        motorlowleft.setPower(gamepad1.left_stick_y);
        motorlowright.setPower(gamepad1.right_stick_y);
        //slideright.setPower(gamepad1.right_trigger);
        //slideleft.setPower(gamepad1.left_trigger);

        if(gamepad1.right_bumper)
        {
            motorupleft.setPower(0.5);
            motorupright.setPower(0.5);
            motorlowleft.setPower(0.5);
            motorlowleft.setPower(0.5);
            motorupleft.setDirection(DcMotor.Direction.FORWARD);
            motorlowleft.setDirection(DcMotor.Direction.REVERSE);
            motorupright.setDirection(DcMotor.Direction.REVERSE);
            motorlowright.setDirection(DcMotor.Direction.FORWARD);

        }
        else {
            motorupleft.setPower(0);
            motorupright.setPower(0);
            motorlowleft.setPower(0);
            motorlowleft.setPower(0);
        }
        if(gamepad1.left_bumper)
        {
            motorupleft.setPower(0.5);
            motorupright.setPower(0.5);
            motorlowleft.setPower(0.5);
            motorlowleft.setPower(0.5);
            motorupleft.setDirection(DcMotor.Direction.REVERSE);
            motorlowleft.setDirection(DcMotor.Direction.FORWARD);
            motorupright.setDirection(DcMotor.Direction.FORWARD);
            motorlowright.setDirection(DcMotor.Direction.REVERSE);

        }
        else
        {
            motorupleft.setPower(0);
            motorupright.setPower(0);
            motorlowleft.setPower(0);
            motorlowleft.setPower(0);
        }
        }

    }
