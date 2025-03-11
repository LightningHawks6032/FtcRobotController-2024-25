package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.util.PID;
import org.firstinspires.ftc.teamcode.hardware.DCMotor;

@Autonomous(name = "PID Movement Test", group="Test")
public class PIDMovementTest extends OpMode {
    PID.Linear velPID, posPID;

    DCMotor motor;
    float t = 0;
    float targetPos = 0f;
    float velPIDOutput=0f, posPIDOutput=0f;
    @Override
    public void init() {
        motor =  new DCMotor(hardwareMap.dcMotor.get("br"), true, null);
        posPID = new PID.Linear(new PID.LinearCoefficients(0.1f, 0.01f,0.05f));
        velPID = new PID.Linear(new PID.LinearCoefficients(0.5f, 0.05f,0.1f));
        motor.encoder.resetEncoder();
    }

    @Override
    public void loop() {
        telemetry.addData("Power", motor.getPower());
        telemetry.addData("Velocity", motor.getVelocity());
        telemetry.addData("Position", motor.getPosition());
        telemetry.addData("T", t);
        telemetry.addData("Target position", targetPos);
        telemetry.addData("Vel PID output", velPIDOutput);
        telemetry.addData("Pos PID output", posPIDOutput);
        if (gamepad1.a) {return;}
        t += gamepad1.left_stick_x * 0.01f;
        targetPos = (float) Math.sin(t*Math.PI) * 1000;



        posPIDOutput = posPID.loop(motor.getPosition(), targetPos);
        velPIDOutput = velPID.loop(motor.getVelocity(), posPIDOutput);

        motor.setPower(posPIDOutput);

    }
}
