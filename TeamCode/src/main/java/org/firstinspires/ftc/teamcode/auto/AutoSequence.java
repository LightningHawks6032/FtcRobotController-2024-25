package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.scheduling.ActionInput;

import java.util.ArrayList;


/// Sequence of actions robot takes during auto
public class AutoSequence {
    protected Telemetry telemetry;
    protected RobotController robot;
    protected AutoAction[] actions;
    int idx = 0;
    protected ElapsedTime timer;

    public void loop() {
        if (idx >= actions.length) {return;}
        else if (actions[idx].getDuration() >= timer.time()) {
            idx++;
            timer.reset();
        }
        else {
            actions[idx].loop(robot, (float) timer.time());
        }
    }
    public void start() {
        timer.reset();
    }
    public static class Builder {
        AutoSequence seq;
        public Builder(RobotController robot, Telemetry telemetry) {
            seq = new AutoSequence();
            seq.robot = robot;
            seq.telemetry = telemetry;
            seq.timer = new ElapsedTime();
            seq.actions = new AutoAction[0];
        }

        public final Builder actions(AutoAction... _actions) {
            seq.actions = _actions;
            return this;
        }

        public AutoSequence get() {
            return seq;
        }
    }

}
