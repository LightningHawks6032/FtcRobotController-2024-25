package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ArmControls {
    enum STATE {
        IDLE, // Not moving
        TARGET, // Moving to [targetPosition]
        MOVING // User controlled stick movement
    }

    static int TOP_POSITION = 5000;
    static int BOTTOM_POSITION = -100;
    static int ARRIVAL_THRESHOLD = 10;

    IMotor sr, sl;
    STATE state;
    int targetPosition = 0;
    Telemetry telemetry;

    PID.Linear posPID = new PID.Linear(new PID.LinearCoefficients(0.52f, 0.51f, 0.815f));

    public ArmControls(IMotor _sr, IMotor _sl, Telemetry _telemetry) {
        sr = _sr;
        sl = _sl;
        telemetry = _telemetry;
    }

    public void init() {
        state = STATE.IDLE;
    }

    public void goDown() {
        targetPosition = BOTTOM_POSITION;
        state = STATE.TARGET;
    }

    public void goUp() {
        targetPosition = TOP_POSITION;
        state = STATE.TARGET;
    }

    public void setTargetPosition(int _targetPos) {
        targetPosition = _targetPos;
        state = STATE.TARGET;
    }

    void moveToTargetPosition(float _dt_Second) {
        if (state != STATE.TARGET) {return;}
        float vel = posPID.loop(sl.getPosition(), targetPosition, _dt_Second);
        sl.setVelocity(vel);
        sr.setVelocity(vel);
    }

    void moveWithPower(float power) {
        state = STATE.MOVING;
        sr.setPower(power);
        sl.setPower(power);
    }

    void zeroMotors() {
        sl.setPower(0);
        sr.setPower(0);
    }

    public void loop(float power, float _dt_Second) {
        if (power != 0 && _dt_Second != 0) {
            moveWithPower(power);
        }
        else if (state == STATE.TARGET) {
            moveToTargetPosition(_dt_Second);
            if (Math.abs(sl.getPosition() - targetPosition) <= ARRIVAL_THRESHOLD) {
                state = STATE.IDLE;
            }
        }
        else {
            zeroMotors();
        }
        /*if(gamepad2.b && sr.getCurrentPosition() <= 5000)
        {
            sl.setTargetPosition(5000);
            sr.setTargetPosition(5000);
            sl.setPower(0.9);
            sr.setPower(0.9);

        }else if(gamepad2.a && sr.getCurrentPosition() >= 0 )
        {
            sl.setTargetPosition(0);
            sr.setTargetPosition(0);
            sl.setPower(-0.9);
            sr.setPower(-0.9);
        }
        else
        {
            sl.setPower(0);
            sr.setPower(0);
        }
        if(gamepad2.left_stick_y > 0 )
        {
            sl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            sr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            while( sr.getCurrentPosition() <= 5000)
            {
                sl.setPower(gamepad2.left_stick_y * 0.75);
                sr.setPower(gamepad2.left_stick_y * 0.75);
            }
        }else if(gamepad2.left_stick_y < 0 )
        {
            sl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            sr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            while(sr.getCurrentPosition() >= -100)
            {
                sl.setPower(gamepad2.left_stick_y * 0.75);
                sr.setPower(gamepad2.left_stick_y * 0.75);
            }
        }
        else
        {
            sl.setPower(0);
            sr.setPower(0);
        }*/
    }

}
