package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.auto.AutoSequence;
import org.firstinspires.ftc.teamcode.auto.PathFollowing;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.scheduling.ActionInput;

import java.util.ArrayList;

@Autonomous(name = "Auto", group="Comp")
public class TestingAuto extends OpMode {
    AutoSequence seq;
    RobotController robot;

    @Override
    public void init() {
        robot = new RobotController(hardwareMap, telemetry);
        robot.init();
        seq = new AutoSequence.Builder(robot, telemetry)
                .actions(
                        new PathFollowing.Builder()
                                .curve(
                                new CubicBezier.Builder()
                                        .p0(new Vec2(1.38f, 5f))
                                        .p1(new Vec2(1.64f, 4.5f))
                                        .p2(new Vec2(0.3f, 5.3f))
                                        .p3(new Vec2(2.4f, 5.68f))
                                        .get()
                                )
                                .get()
                )
                .get();
    }

    @Override
    public void loop() {
        seq.loop();
    }

    @Override
    public void start() {
        seq.start();

    }
    /*RobotController robot;
    ElapsedTime timer;
    AutoSequence seq;
    ArrayList<AutoSequence.Action> actions;

    @Override
    public void init() {
        robot = new RobotController(hardwareMap, telemetry);
        robot.init();
        seq = new AutoSequence(telemetry);

        AutoSequence.Action a1 = new AutoSequence.Action();
        a1.input = new ActionInput();
        a1.input.moveDirection = new Vec2Rot(1, 0, 0);
        a1.duration = 1.5f;

        actions = new ArrayList<>();
        actions.add(a1);
    }


    @Override
    public void start() {
        seq.init(actions);
    }

    @Override
    public void loop() {
        if (!seq.done) {
            seq.loop();
            robot.loop(seq.getCurrentAction().input);
        }
    }*/
}
