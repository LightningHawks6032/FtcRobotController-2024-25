package org.firstinspires.ftc.teamcode.controllers;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.PID;
import org.firstinspires.ftc.teamcode.auto.AutoAction;
import org.firstinspires.ftc.teamcode.util.CubicBezier;
import org.firstinspires.ftc.teamcode.util.PIDMotor;
import org.firstinspires.ftc.teamcode.util.Vec2;
import org.firstinspires.ftc.teamcode.hardware.IMotor;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;

import java.util.function.Consumer;

/// Controls movement of the drivetrain
public final class MotorControls {


    public class MoveAction extends ActionSequencer.StickAction {

        @Override
        public void loop(RobotController robot, ActionSequencer.StickAction.Data data) {
            printDebug();
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
                slowFactor = 0.35f;
            }
            else {slowFactor = 1f;}
        }
    }
    public class PathFollowingAction extends AutoAction {
        protected float duration = 1f;
        protected CubicBezier curve;
       //PIDMotion pid;
        @Override
        public float getDuration() {return duration;}
        @Override
        public void loop(RobotController robot, float elapsed) {
            pid.move(
                    curve.vel.at(elapsed/duration)
            );
        }
    }
    public class PathFollowingActionBuilder{
        PathFollowingAction action;
        public PathFollowingActionBuilder() {
            action = new PathFollowingAction();
        }
        public PathFollowingActionBuilder duration(float dur) {
            action.duration = dur;
            return this;
        }

        public PathFollowingActionBuilder curve(CubicBezier _curve) {
            action.curve = _curve;
            return this;
        }

        public PathFollowingAction get() {
            return action;
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
    public PathFollowingActionBuilder getPathFollowingActionBuilder() {return pathFollowingActionBuilder;}

    MoveAction moveAction;
    RotateAction rotateAction;
    SlowModeAction slowModeAction;
    PathFollowingActionBuilder pathFollowingActionBuilder;

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
    public class PIDMotion {
        PIDMotor pul, pur, pll, plr;
        public PIDMotion() {
            PID.Linear pos = new PID.Linear(
                    new PID.LinearCoefficients(0.986f, 0.71f, 0.109f)
            );
            PID.Linear vel = new PID.Linear(
                    new PID.LinearCoefficients(2, 0, 0.22f)
            );
            pul = new PIDMotor(ul, pos, vel);
            pur = new PIDMotor(ur, pos, vel);
            pll = new PIDMotor(ll, pos, vel);
            plr = new PIDMotor(lr, pos, vel);
        }
        void move(Vec2 dir) {
            // Linear combination of wheels at pi/4 angle
            float c1 = -dir.x - dir.y;
            float c2 = -dir.x + dir.y;
            //currentPower = new MotorPower(-c2, c1, c1, -c2);
            //ul, ur, ll, lr
            pul.setPosition(-c2);
            pur.setPosition(c1);
            pll.setPosition(c1);
            plr.setPosition(-c2);
        }
    }
    IMotor ul, ur, ll, lr; //up left, up right, low left, low right
    PIDMotion pid;
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



    public void printDebug() {
        telemetry.addData("ul pos", ul.getPosition());
        telemetry.addData("ul vel", ul.getVelocity());
        telemetry.addData("ur pos", ur.getPosition());
        telemetry.addData("ll pos", ll.getPosition());
        telemetry.addData("lr pos", lr.getPosition());


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
        pid = new PIDMotion();
    }

    public void init() {
        currentState = STATE.IDLE;
        zeroAllMotors();

        moveAction = new MoveAction();
        rotateAction = new RotateAction();
        slowModeAction = new SlowModeAction();
        pathFollowingActionBuilder = new PathFollowingActionBuilder();
    }
}
