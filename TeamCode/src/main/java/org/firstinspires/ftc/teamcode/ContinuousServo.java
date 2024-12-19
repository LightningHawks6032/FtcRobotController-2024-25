package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.Servo;

public class ContinuousServo implements IMotor {

    public ContinuousServo(Servo _s) {
        servo = _s;
    }
    Servo servo;
    @Override
    public void setPower(float power) {
        servo.setPosition((power+1)/2);
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
        return 0;
    }

    @Override
    public void setVelocity(float velocity) {

    }

    @Override
    public float getVelocity() {
        return 0;
    }
}