package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.Servo;

public class ContinuousServo implements IMotor {

    public ContinuousServo(Servo _s) {
        servo = _s;
    }
    Servo servo;
    @Override
    public void setPower(float power) {
        servo.setPosition(power);
    }

    @Override
    public float getPower() {
        return (float)servo.getPosition();
    }

    @Override
    public void setTorque(float torque, float currentVelocity) {

    }

    @Override
    public int getPosition() {
        return (int)(servo.getPosition() * 100);
    }

    @Override
    public void setVelocity(float velocity) {

    }

    @Override
    public float getVelocity() {
        return 0;
    }
}