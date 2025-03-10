package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class DCMotor implements IMotor {
     public class Encoder {
        public boolean runningToPosition = false;
        public void setPosition(int position) {
            if (!usingEncoder) {System.err.println("Tried to set position on non-encoder motor" + motor.getDeviceName());}
                motor.setTargetPosition(position);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                runningToPosition = true;
        }
        public void resetEncoder() {

            if (!usingEncoder) {System.err.println("Tried to reset encoder on non-encoder motor " + motor.getDeviceName()); return;}
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        public void lock() {

            if (!usingEncoder) {System.err.println("Tried to lock non-encoder motor" + motor.getDeviceName());}
            setPosition(getPosition());
            setPower(1);
        }

        public void unlock() {

            if (!usingEncoder) {System.err.println("Tried to unlock non-encoder motor" + motor.getDeviceName());}
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            setPower(0);
        }

    }

    public Encoder encoder;
    DcMotor motor;
    MotorSpec spec;
    boolean usingEncoder;
    public static MotorSpec GOBILDA_5000_0002_0001 = new MotorSpec(6000, 1.47f, 145.6f);
    public DCMotor(DcMotor _motor, boolean _usingEncoder, MotorSpec _spec) {
        motor = _motor;
        motor.setDirection(DcMotorSimple.Direction.FORWARD);
        spec = _spec == null ? GOBILDA_5000_0002_0001 : _spec;
        usingEncoder = _usingEncoder;
        encoder = new Encoder();
    }
    public void setDirection(DcMotorSimple.Direction dir) {
        motor.setDirection((dir));
    }
    public DCMotor(DcMotor _motor, MotorSpec _spec, DcMotorSimple.Direction dir) {
        motor = _motor;
        motor.setDirection(dir);
        spec = _spec == null ? GOBILDA_5000_0002_0001 : _spec;
        usingEncoder = true;
        encoder = new Encoder();
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

    @Override
    public int getPosition() {
        return motor.getCurrentPosition();
    }

    @Override
    public void setVelocity(float velocity_ticksPerSecond) {
        setPower(velocity_ticksPerSecond * 60 / (spec.noLoadSpeed * spec.encoderResolution));
    }

    @Override
    public float getVelocity() {
        return getPower() * spec.noLoadSpeed * spec.encoderResolution / 60;
    }
}

