package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.auto.AutoActionGroup;
import org.firstinspires.ftc.teamcode.auto.AutoSequence;
import org.firstinspires.ftc.teamcode.auto.ConstantAutoAction;
import org.firstinspires.ftc.teamcode.auto.PathFollowing;
import org.firstinspires.ftc.teamcode.auto.RotateAction;
import org.firstinspires.ftc.teamcode.auto.StopMoving;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.scheduling.ActionInput;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;

import java.util.ArrayList;

@Autonomous(name = "Left", group="Comp")
public class TestingAuto extends OpMode {
    AutoSequence seq;
    RobotController robot;

    @Override
    public void init() {
        robot = new RobotController(hardwareMap, telemetry);
        robot.init();
        robot.hangClawControls.ensureClosed();
        seq = new AutoSequence.Builder(robot, telemetry)
                .actions(
                        new AutoActionGroup(
                                new PathFollowing.Builder(robot)
                                        .curve(
                                                new CubicBezier.Builder()
                                                        .p0(new Vec2(5.2f, 5.32f))
                                                        .p1(new Vec2(4.68f, 4.42f))
                                                        .p2(new Vec2(4.26f, 5.02f))
                                                        .p3(new Vec2(3.62f, 5.52f))
                                                        .get()
                                        )
                                        .cutoff(6f)
                                        .duration(3f)
                                        .get(),
                                new ConstantAutoAction.Builder<ActionSequencer.ButtonAction.Data>()
                                        .action(robot.armControls.powerUpSlide)
                                        .dataFunc(i -> {
                                            i.armControls.armLoop.accept(i);
                                            return new ActionSequencer.ButtonAction.DataBuilder().pressed(true).get();
                                        })
                                        .duration(5f)
                                        .get(),
                                new ConstantAutoAction.Builder<ActionSequencer.ButtonAction.Data>()
                                        .action(robot.pickupSlideControls.retractSlide)
                                        .dataFunc(i -> new ActionSequencer.ButtonAction.DataBuilder().pressed(true).get())
                                        .duration(0.1f)
                                        .get()
                        ),
                        new StopMoving(),
                        new RotateAction.Builder(robot)
                                .direction(-1)
                                .duration(0.8f)
                                .get(),
                        new StopMoving(),
                        new ConstantAutoAction.Builder<ActionSequencer.StickAction.Data>()
                                .action(robot.motorControls.getMoveAction())
                                .dataFunc(i -> new ActionSequencer.StickAction.DataBuilder().stick(new Vec2(0, -1)).get())
                                .duration(0.2f)
                                .get(),
                        new StopMoving()
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
}
