package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.DCMotor.GOBILDA_5000_0002_0001;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class DebugMotor implements IMotor {

    float currentPower;
    Telemetry telemetry;
    String id;
    MotorSpec spec;
    public DebugMotor(String _id, Telemetry _telemetry, MotorSpec _spec) {
        currentPower = 0;
        telemetry = _telemetry;
        id = _id;
        spec = _spec == null ? GOBILDA_5000_0002_0001 : _spec;
    }

    @Override
    public void setPower(float power) {
        currentPower = power;
        telemetry.addData(id + " Power", currentPower);
    }

    @Override
    public float getPower() {
        return currentPower;
    }

    @Override
    public void setTorque(float torque_KgCm, float currentVelocity_RPM) {
        currentPower = torque_KgCm / spec.stallTorque + currentVelocity_RPM / spec.noLoadSpeed;
        telemetry.addData(id + " Torque", currentPower);
    }

    @Override
    public int getPosition() {
        return 0; // TODO: Fix this :)
    }

    @Override
    public void setVelocity(float velocity) {

    }

}
