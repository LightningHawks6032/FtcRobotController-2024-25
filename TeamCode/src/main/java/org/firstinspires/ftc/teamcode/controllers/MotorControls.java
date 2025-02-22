package org.firstinspires.ftc.teamcode.controllers;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.Vec2;
import org.firstinspires.ftc.teamcode.util.Vec2Rot;
import org.firstinspires.ftc.teamcode.hardware.IMotor;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;

import java.util.function.Consumer;

/// Controls movement of the drivetrain
public final class MotorControls {


    public class MoveAction extends ActionSequencer.StickAction {

        @Override
        public void loop(RobotController robot, ActionSequencer.StickAction.Data data) {
            robot.telemetry.addData("Motor State", currentState.name());
            robot.telemetry.addData("Move input", data.stick.x + ", " + data.stick.y);
            if (currentState != STATE.ROTATING) {
                if (data.stick.nonzero()) {
                    move(data.stick.scale(MAXSPEED).scale(slowFactor));
                    currentState = STATE.MOVING;
                }
                else {
                    currentState = STATE.IDLE;
                    zeroAllMotors();
                }
                setPower(currentPower);
            }
        }
    }

    public class RotateAction extends ActionSequencer.StickAction {

        @Override
        public void loop(RobotController robot, ActionSequencer.StickAction.Data data) {
            if (currentState != STATE.MOVING) {
                if (data.stick.x != 0) {
                    rotate(data.stick.x * MAXSPEED * slowFactor);
                    currentState = STATE.ROTATING;
                }
                else {
                    currentState = STATE.IDLE;
                    zeroAllMotors();
                }
                setPower(currentPower);
            }
        }
    }

    public class SlowModeAction extends ActionSequencer.ButtonAction {
        @Override
        public void loop(RobotController robot, ActionSequencer.ButtonAction.Data data) {
            if (data.pressed) {
                slowFactor = 0.3f;
            }
            else {slowFactor = 1f;}
        }
    }
    public MoveAction getMoveAction() {
        return moveAction;
    }

    public RotateAction getRotateAction() {
        return rotateAction;
    }

    public SlowModeAction getSlowModeAction() {
        return slowModeAction;
    }


    MoveAction moveAction;
    RotateAction rotateAction;
    SlowModeAction slowModeAction;


    /// States the motors can be in
    enum STATE {
        IDLE, // Not moving
        MOVING, // Moving using 360 degree range of motion
        ROTATING, // Rotating on self axis
    }
    static class MotorPower {
        // up left, up right, low left, low right
        public float ul, ur, ll, lr;
        // returns whether all of the powers are zero
        public boolean isZero() {return ul == 0 && ur == 0 && ll == 0 && lr == 0;}
        public MotorPower(float _ul, float _ur, float _ll, float _lr) {
            ul = _ul;
            ur = _ur;
            ll = _ll;
            lr = _lr;
        }

        public MotorPower() {
            this(0, 0, 0, 0);
        }

        @NonNull
        @Override
        public String toString() {
            return "ul: " + ul + ", ur: " + ur + ", ll: " + ll + ", lr: " + lr;
        }

    }

    IMotor ul, ur, ll, lr; //up left, up right, low left, low right

    /// Current power that will be applied to the motors
    MotorPower currentPower;
    /// Current state of motion
    STATE currentState;
    Telemetry telemetry;
    public Consumer<RobotController> stopMotorAction = (i) -> {
        currentState = STATE.IDLE;
        zeroAllMotors();
        setPower(currentPower);
    };

    float slowFactor = 1f;
    final float MAXSPEED = 0.75f;

    /// Sets the current power to 0 for all motors
    void zeroAllMotors() {
        currentPower = new MotorPower();
    }

    /// Applies power to motors given a [MotorPower] object
    void setPower(MotorPower p) {
        ul.setPower(p.ul);
        ur.setPower(p.ur);
        ll.setPower(p.ll);
        lr.setPower(p.lr);
    }

    /// Sets the current power to move in the direction of given direction
    void move(Vec2 dir) {
        // Linear combination of wheels at pi/4 angle
        float c1 = -dir.x - dir.y;
        float c2 = -dir.x + dir.y;
        currentPower = new MotorPower(-c2, c1, c1, -c2);
    }

    /// Sets the current power to rotate about the robot's self axis
    void rotate(float power) {
        currentPower = new MotorPower(power, -power, power, -power);
    }

    public MotorControls(IMotor _ul, IMotor _ur, IMotor _ll, IMotor _lr, Telemetry _telemetry) {
        ul = _ul;
        ur = _ur;
        ll = _ll;
        lr = _lr;

        telemetry = _telemetry;
    }

    public void loop(Vec2Rot input, float slow) {
        // Gets the direction the player is holding the left stick
        Vec2 dir = input.toVec2();
        // Gets the power the player is holding the right stick on it's horizontal axis
        float raxis = input.r;

        // If current state is either moving or idle, then the movement direction may be modified
        if (dir.nonzero() && currentState != STATE.ROTATING) {
            move(dir.scale(0.75f-(slow*0.5f)));
            currentState = STATE.MOVING;
        }
        // If current state is either rotating or idle, then the rotation power may be modified
        else if (raxis != 0 && currentState != STATE.MOVING) {
            rotate(raxis * (0.75f-(slow*0.5f)));
            currentState = STATE.ROTATING;
        }
        // If the player is neither moving nor rotating, then the motors should be stopped
        else {
            currentState = STATE.IDLE;
            zeroAllMotors();
        }

        telemetry.addData("Motor Power", currentPower);
        telemetry.addData("Left Stick", "x: " + dir.x + ", y: " + dir.y);
        telemetry.addData("State", currentState.name());
        // Applies the current power to the motors given it isn't zero
        setPower(currentPower);
    }

    public void init() {
        currentState = STATE.IDLE;
        zeroAllMotors();

        moveAction = new MoveAction();
        rotateAction = new RotateAction();
        slowModeAction = new SlowModeAction();
    }
}
