package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.IMotor;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;

/// Controls the bar mounted on the vertical slides
//Bring backward
public class PickupSlideControls {
    public class ExtendSlideAction extends ActionSequencer.ButtonAction {
        @Override
        public void loop(RobotController robot, ActionSequencer.ButtonAction.Data data) {
            if (data.pressed) {
                goUp();
                claw.ensureClosed();
            }
        }
    }
    //Bring forward
    public class RetractSlideAction extends ActionSequencer.ButtonAction {
        @Override
        public void loop(RobotController robot, ActionSequencer.ButtonAction.Data data) {
            telemetry.addData("pickup slide pos", cr.getPosition() + ", " + cr.getPosition());
            if (data.pressed) {
                goDown();
                claw.ensureOpen();
            }
        }
    }

    public ExtendSlideAction extendSlide;
    public RetractSlideAction retractSlide;

    IMotor cr, cl;
    Telemetry telemetry;
    public boolean up;
    ClawControls claw;


    public PickupSlideControls(IMotor _cl, IMotor _cr, ClawControls _claw, Telemetry _telemetry) {
        cr = _cr;
        cl = _cl;
        telemetry = _telemetry;
        claw = _claw;
        up = true;
        extendSlide = new ExtendSlideAction();
        retractSlide = new RetractSlideAction();
    }
    void setPower(float p) {
        cr.setPower(p);
        cl.setPower(p);
    }
    void goDown() {
        setPower(0.17f);
        up = false;
    }
    void goUp() {
        setPower(0.85f);
        up = true;

    }
}
