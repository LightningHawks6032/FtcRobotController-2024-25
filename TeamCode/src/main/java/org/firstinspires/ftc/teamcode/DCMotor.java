package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class DCMotor implements IMotor{
    DcMotor motor;
    MotorSpec spec;

    public static MotorSpec GOBILDA_5000_0002_0001 = new MotorSpec(6000, 1.47f);

    public DCMotor(DcMotor _motor, MotorSpec _spec) {
        motor = _motor;
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        spec = _spec == null ? GOBILDA_5000_0002_0001 : _spec;
    }

    @Override
    public void setPower(float power) {
        motor.setPower(power);
    }

    @Override
    public float getPower() {
        return (float)motor.getPower();
    }

    @Override
    public void setTorque(float torque_KgCm, float currentVelocity_RPM) {
        setPower(torque_KgCm / spec.stallTorque + currentVelocity_RPM / spec.noLoadSpeed);
    }
}

