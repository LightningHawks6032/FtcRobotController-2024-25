package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.Toggle;
import org.firstinspires.ftc.teamcode.hardware.DCMotor;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;

import java.util.function.Consumer;

/// Controls vertical slide
public class ArmControls {
    public class RaiseSlide extends ActionSequencer.ButtonAction {
        @Override
        public void loop(RobotController robot, ActionSequencer.ButtonAction.Data data){
            if (currentState != STATE.POW && data.pressed) {
                currentState = STATE.POS;
                target = TOP;
            }
        }
    }
    public class LowerSlide extends ActionSequencer.ButtonAction {
        @Override
        public void loop(RobotController robot, ActionSequencer.ButtonAction.Data data){
            if (currentState != STATE.POW && data.pressed) {
                currentState = STATE.POS;
                target = BOT;
            }
        }
    }
    public class PowerUpSlide extends ActionSequencer.ButtonAction {
        @Override
        public void loop(RobotController robot, ActionSequencer.ButtonAction.Data data){
            if (currentState != STATE.POS) {
                currentPower = Math.signum(currentPower + (data.pressed&&sr.getPosition() < MAXPOS? 1:0));
            }
        }
    }
    public class PowerDownSlide extends ActionSequencer.ButtonAction {
        @Override
        public void loop(RobotController robot, ActionSequencer.ButtonAction.Data data){
            if (currentState != STATE.POS) {
                currentPower = Math.signum(currentPower - (data.pressed&&sr.getPosition() > MINPOS ? 1:0));
            }
        }
    }


    DCMotor sr, sl;
    Telemetry telemetry;
    Toggle lockToggle;
    //11566
    int TOP=11000,BOT=0, OFFSET=100;
    int MAXPOS = 11000,MINPOS=0;
    int target;
    STATE currentState;
    float currentPower = 0f;
    public Consumer<RobotController> armLoop = (RobotController i) -> {
        telemetry.addData("Slide Power", currentPower);
        telemetry.addData("Slide state", currentState.name());
        telemetry.addData("Slide Pos", sr.getPosition() + ", " + sl.getPosition());
        if (currentState == STATE.POS) {
            if (Math.abs(sr.getPosition() - target) < OFFSET/* && Math.abs(sl.getPosition() - target) < OFFSET*/) {
                currentState = STATE.IDL;
            }
            else {
                moveTo(target);
            }
        }
        else {
            moveWithPower(-currentPower);
        }
        currentPower = 0;
    };
    public LowerSlide lowerSlide;
    public RaiseSlide raiseSlide;
    public PowerDownSlide powerDownSlide;
    public PowerUpSlide powerUpSlide;


    public ArmControls(DCMotor _sr, DCMotor _sl, Telemetry _telemetry) {
        sr = _sr;
        sl = _sl;
        telemetry = _telemetry;
        lockToggle = new Toggle(false);
        target = 0;

        sl.encoder.resetEncoder();
        sr.encoder.resetEncoder();

        currentState = STATE.IDL;

        lowerSlide = new LowerSlide();
        raiseSlide = new RaiseSlide();
        powerDownSlide = new PowerDownSlide();
        powerUpSlide = new PowerUpSlide();
    }

    public void moveTo(int pos) {
        float pow = Math.abs(sr.getPosition() - pos) < OFFSET ? 0 : Math.signum(sr.getPosition() - pos);
        sl.setPower(-pow/*Math.abs(sl.getPosition() - pos) < OFFSET ? 0 : -0.5f*Math.signum(sl.getPosition() - pos)*/);
        sr.setPower(pow);
    }

    void moveWithPower(float power) {
        sr.setPower(power);
        sl.setPower(-power);

    }
    enum STATE  {
        POS,
        POW,
        IDL
    }


    public void loop(float power, int vDPad, boolean lock) {
        telemetry.addData("Pos", sr.getPosition() + ", " + sl.getPosition());
        telemetry.addData("Target position", target);
        telemetry.addData("Power", power);
        telemetry.addData("Arm State", currentState.name());

        if (currentState != STATE.POW && vDPad != 0) {
            currentState = STATE.POS;
            target = vDPad > 0 ? TOP : BOT;
        }
        else if (currentState != STATE.POS && power != 0) {
            currentState = STATE.POW;
        }
        else if (currentState != STATE.POS){
            currentState = STATE.IDL;
        }

        if (currentState == STATE.POS) {
            if (Math.abs(sr.getPosition() - target) < OFFSET/* && Math.abs(sl.getPosition() - target) < OFFSET*/) {
                currentState = STATE.IDL;
            }
            else {
                moveTo(target);
            }
        }
        else if (currentState == STATE.POW) {
            moveWithPower(power);
        }
        else {
            moveWithPower(0);
        }
    }
}
