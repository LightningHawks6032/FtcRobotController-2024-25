package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

//Exposes motor functionality
public final class MotorControls implements IProcess {
    enum STATES {
        IDLE,
        MOVING,
        ROTATING,
    }

    DcMotor ul, ur, ll, lr; // up left, up right, low left, low right
    Controller controller;
    Vec2Rot currentDir;
    void powerAllMotors(double power) {
        ul.setPower(power);
        ur.setPower(power);
        ll.setPower(power);
        lr.setPower(power);
    }

    void zeroAllMotors() {
        powerAllMotors(0);
    }

    void setPower(float ulp, float urp, float llp, float lrp) {
        ul.setPower(ulp);
        ur.setPower(urp);
        ll.setPower(llp);
        lr.setPower(lrp);
    }

    void move(Vec2 dir) {
        // Linear combination of wheels at pi/4 angle
        float c1 = 0.5f * (dir.x + dir.y);
        float c2 = 0.5f * (dir.x - dir.y);
        setPower(-c2, c1, -c1, c2);
    }
    //positive power -> clockwise, negative power -> counter clockwise
    void rotate(double power) {
        if (power == 0) {
            return;
        }
        ul.setDirection(DcMotor.Direction.REVERSE);
        ur.setDirection(DcMotor.Direction.REVERSE);
        ll.setDirection(DcMotor.Direction.FORWARD);
        lr.setDirection(DcMotor.Direction.FORWARD);
    }

    public MotorControls(DcMotor _ul, DcMotor _ur, DcMotor _ll, DcMotor _lr, Controller _gamepad) {
        ul = _ul;
        ur = _ur;
        ll = _ll;
        lr = _lr;
        controller = _gamepad;
    }

    @Override
    public void loop() {
        Vec2 dir = controller.leftStick();
        float raxis = controller.rightStickX();
        if (dir.nonzero()) {
            move(dir.scale(0.5f));
        }
        else if (raxis != 0) {
            rotate(raxis);
            powerAllMotors(raxis * 0.5);
        } else {
            zeroAllMotors();
        }
    }

    @Override
    public void init() {
        currentDir = new Vec2Rot(0, 0, 0);
    }
}
