package org.firstinspires.ftc.teamcode.scheduling;

import org.firstinspires.ftc.teamcode.auto.AutoSequence;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.util.Toggle;

public class AutoSequenceAction extends ActionSequencer.ButtonAction{
    AutoSequence seq;
    Toggle toggle;
    @Override
    public void loop(RobotController robot, ActionSequencer.ButtonAction.Data data) {
        if (data.pressed) {seq.start();}
        toggle.loop(data.pressed);
        if (toggle.state) seq.loop();
    }
    public AutoSequenceAction(AutoSequence _seq) {
        seq = _seq;
        toggle = new Toggle(false);
    }
}
