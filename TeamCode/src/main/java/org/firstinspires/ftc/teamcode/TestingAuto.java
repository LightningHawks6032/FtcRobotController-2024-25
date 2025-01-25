package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.controllers.RobotController;

import java.util.ArrayList;

@Autonomous
public class TestingAuto extends OpMode {
    RobotController robot;
    ElapsedTime timer;
    AutoSequence seq;


    @Override
    public void init() {
        robot = new RobotController(hardwareMap, telemetry);
        robot.init();
        seq = new AutoSequence();

        AutoSequence.Action a1 = new AutoSequence.Action();
        a1.input = new AutoSequence.ActionInput();
        a1.input.moveDirection = new Vec2Rot(1, 0, 0);
        a1.duration = 1;

        ArrayList<AutoSequence.Action> actions = new ArrayList<>();
        actions.add(a1);

        seq.init(actions);
    }

    @Override
    public void loop() {
        System.out.println(time);
        seq.loop();

        robot.loop(seq.getCurrentAction().input);
    }
}
