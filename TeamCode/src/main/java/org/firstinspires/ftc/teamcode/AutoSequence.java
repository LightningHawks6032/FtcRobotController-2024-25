package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.scheduling.ActionInput;

import java.util.ArrayList;


// Sequence of actions robot takes during auto
public class AutoSequence {
    Telemetry telemetry;
    public AutoSequence(Telemetry _telemetry) {
        telemetry = _telemetry;
    }



    public static class Action {
        public ActionInput input;
        public float duration;

        public Action() {

            input = new ActionInput();
            duration = 0;
        }
    }

    ArrayList<Action> actions;

    int idx;

    ElapsedTime timer;

    public boolean done;
    public void init(ArrayList<Action> _actions) {
        actions = _actions;
        timer = new ElapsedTime();
        timer.reset();
        idx = 0;
        done = false;
    }

    public Action getCurrentAction() {
        return actions.get(idx);
    }

    public void loop() {
        if(!done) {
            if (timer.time() >= actions.get(idx).duration) {
                idx++;
                done = idx >= actions.size();
                timer.reset();
            }
        }
    }
}
