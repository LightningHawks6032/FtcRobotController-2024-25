package org.firstinspires.ftc.teamcode;

public interface IMotor {
    void setPower(float power);
    float getPower();
    void setTorque(float torque, float currentVelocity);
    int getPosition();
    void setVelocity(float velocity);
    float getVelocity();
}
