package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.auto.AutoAction;
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

    public class MoveSlideToPosition extends ActionSequencer.TriggerAction {
        @Override
        public void loop(RobotController robot, ActionSequencer.TriggerAction.Data data) {
            if (currentState != STATE.POW) {
                currentState = STATE.POS;
                target = (int)data.trigger;
            }
        }
    }


    DCMotor sr, sl;
    Telemetry telemetry;
    Toggle lockToggle;
    //11566
    int TOP=3500,BOT=0, OFFSET=100;
    int MAXPOS = 4000,MINPOS=0;
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
    public MoveSlideToPosition moveSlideToPosition;


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
        moveSlideToPosition = new MoveSlideToPosition();
    }

    public void moveTo(int pos) {
        float pow = Math.abs(sr.getPosition() - pos) < OFFSET ? 0 : Math.signum(sr.getPosition() - pos);
        moveWithPower(pow);
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
}
