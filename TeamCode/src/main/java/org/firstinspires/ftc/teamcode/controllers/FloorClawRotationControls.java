package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Toggle;
import org.firstinspires.ftc.teamcode.hardware.IMotor;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;
import org.firstinspires.ftc.teamcode.scheduling.IAction;

/// Controls the claw used for picking samples up from the floor. Creates a [ClawAction] object on initialization
public class FloorClawRotationControls {
    public class ClawAction extends ActionSequencer.ButtonAction{
        public void loop(RobotController robot, ActionSequencer.ButtonAction.Data data) {
            if (toggle.state) {
                open();
                robot.floorClawControls.ensureClosed();
            }
            else {
                close();
            }
            robot.floorClawSpinControls.zeroRot.loop(robot, new ActionSequencer.ButtonAction.DataBuilder().pressed(true).get());

        }
    }


    //C: 1, 0.15
    IMotor c, d;
    Telemetry telemetry;
    float start=1f;
    float stop=0.15f;
    Toggle toggle;
    public IAction<ActionSequencer.ButtonAction.Data> clawAction;

    public FloorClawRotationControls(IMotor _c, IMotor _d, Telemetry _telemetry) {
        c = _c;
        d = _d;
        telemetry = _telemetry;
        toggle = new Toggle(false);

        clawAction = new ActionSequencer.ExecuteIf.Builder<ActionSequencer.ButtonAction.Data>()
                .action(new ClawAction())
                .predicate(data -> {
                    boolean released = toggle.released;
                    toggle.loop(data.pressed);
                    return released != toggle.released;
                })
                .get();
    }
    void close() {
        c.setPower(stop);
        d.setPower(stop);
    }
    void open() {
        c.setPower(start);
        d.setPower(start);
    }

    public void loop(boolean power) {
        telemetry.addData("power", power);
        telemetry.addData("d pos", d.getPosition());

        boolean released = toggle.released;
        toggle.loop(power);
        if (released != toggle.released) {
            if (toggle.state) {open();}
            else {close();}
        }
    }
}
