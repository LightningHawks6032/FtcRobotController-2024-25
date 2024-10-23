package org.firstinspires.ftc.teamcode;

public class MotorSpec {
    float noLoadSpeed;
    float stallTorque;

    public MotorSpec(float _noLoadSpeed_RPM, float _stallTorque_KgCm) {
        noLoadSpeed = _noLoadSpeed_RPM;
        stallTorque = _stallTorque_KgCm;
    }
}
