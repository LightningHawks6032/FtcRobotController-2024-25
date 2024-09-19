package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Testing")
public class Testing extends OpMode {

    DcMotor motorupleft;
    DcMotor motorupright;
    DcMotor motorlowleft;
    DcMotor motorlowright;
    @Override
    public void init() {

        motorupleft = hardwareMap.dcMotor.get("motor 1");
        motorupright = hardwareMap.dcMotor.get("motor 2");
        motorlowleft = hardwareMap.dcMotor.get("motor 3");
        motorlowright = hardwareMap.dcMotor.get("motor 4");

        motorupleft.setDirection(DcMotor.Direction.FORWARD);
        motorlowleft.setDirection(DcMotor.Direction.FORWARD);
        motorupright.setDirection(DcMotor.Direction.REVERSE);
        motorlowright.setDirection(DcMotor.Direction.REVERSE);


    }

    @Override
    public void loop() {

        motorupleft.setPower(gamepad1.left_stick_y);
        motorupright.setPower(gamepad1.right_stick_y);
        motorlowleft.setPower(gamepad1.left_stick_y);
        motorlowright.setPower(gamepad1.right_stick_y);

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

    }



}
