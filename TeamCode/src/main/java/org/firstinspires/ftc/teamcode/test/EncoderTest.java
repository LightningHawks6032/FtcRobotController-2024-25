package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.DCMotor;

@TeleOp(group = "Test")
public class EncoderTest extends OpMode
{
    DcMotor motor;

    @Override
    public void init() {
    motor = hardwareMap.dcMotor.get("br");
    motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void start() {

    }

    boolean busy;
    @Override
    public void loop() {
        telemetry.addData("Encoder", motor.getCurrentPosition());
        telemetry.addData("Busy", motor.isBusy());

        busy = busy && Math.abs(motor.getCurrentPosition() - motor.getTargetPosition()) > 20;

        if (!busy) {
        if (gamepad1.a) {

            motor.setTargetPosition(2503);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setPower(0.5f);
            busy = true;
        }
        else if (gamepad1.b) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
        else {
            motor.setPower(gamepad1.left_stick_y);
        }
    }}
}
