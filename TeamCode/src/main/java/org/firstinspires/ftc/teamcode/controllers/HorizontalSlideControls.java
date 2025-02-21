package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.hardware.IMotor;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;

/// Controls horizontal slide
public class HorizontalSlideControls {
    public class SlideAction extends ActionSequencer.TriggerAction {
        @Override
        public void loop(RobotController robot, ActionSequencer.TriggerAction.Data data) {
            c.setPower(data.trigger * 0.5f);
        }
    }
    IMotor c;
    Telemetry telemetry;
    public SlideAction slideAction;

    public HorizontalSlideControls(IMotor _c, Telemetry _telemetry) {
        c = _c;
        telemetry = _telemetry;
        slideAction = new SlideAction();
    }

    public void loop(int power) {
        c.setPower(power*0.75f);
    }
}
