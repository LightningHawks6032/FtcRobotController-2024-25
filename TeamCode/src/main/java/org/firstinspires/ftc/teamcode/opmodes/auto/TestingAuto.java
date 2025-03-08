package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.auto.AutoActionGroup;
import org.firstinspires.ftc.teamcode.auto.AutoSequence;
import org.firstinspires.ftc.teamcode.auto.ConstantAutoAction;
import org.firstinspires.ftc.teamcode.auto.AutoPathFollowing;
import org.firstinspires.ftc.teamcode.auto.AutoRotateAction;
import org.firstinspires.ftc.teamcode.auto.AutoStopMoving;
import org.firstinspires.ftc.teamcode.controllers.MotorControls;
import org.firstinspires.ftc.teamcode.controllers.PickupSlideControls;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;
import org.firstinspires.ftc.teamcode.util.CubicBezier;
import org.firstinspires.ftc.teamcode.util.Vec2;

@Autonomous(name = "Left", group="Comp")
public class TestingAuto extends OpMode {
    AutoSequence seq;
    RobotController robot;

    @Override
    public void init() {
        robot = new RobotController(hardwareMap, telemetry);
        robot.init();
        robot.outtakeClawControls.ensureClosed();
        //robot.outtakeSlideControls.up = false;
        robot.outtakeSlideControls.retractSlide.loop(robot, new ActionSequencer.ButtonAction.DataBuilder().pressed(true).get());
        seq = new AutoSequence.Builder(robot, telemetry)
                .actions(
                                new AutoPathFollowing.Builder(robot)
                                        .curve(
                                                new CubicBezier.Builder()
                                                        .p0(new Vec2(2.74f, 1.73f))
                                                        .p1(new Vec2(2.89f, 1.49f))
                                                        .p2(new Vec2(2.47f, 1.06f))
                                                        .p3(new Vec2(2.47f, 0.51f))
                                                        .get()
                                        )
                                        .cutoff(2f)
                                        .duration(2f)
                                        .get(),
                                new AutoStopMoving(),
                                new AutoActionGroup(
                                    new ConstantAutoAction.Builder<ActionSequencer.TriggerAction.Data>()
                                            .action(robot.armControls.moveSlideToPosition)
                                            .dataFunc(i -> {
                                                i.armControls.armLoop.accept(i);
                                                return new ActionSequencer.TriggerAction.DataBuilder().trigger(3000).get();
                                            })
                                            .duration(1.4f)
                                            .get(),
                                    new ConstantAutoAction.Builder<ActionSequencer.ButtonAction.Data>()
                                            .action(robot.outtakeSlideControls.retractSlide)
                                            .dataFunc(i -> new ActionSequencer.ButtonAction.DataBuilder().pressed(true).get())
                                            .duration(0.1f)
                                            .get()
                                ),
                                new ConstantAutoAction.Builder<ActionSequencer.ButtonAction.Data>()
                                        .action(robot.armControls.lowerSlide)
                                        .dataFunc(i -> {
                                            i.armControls.armLoop.accept(i);
                                            return new ActionSequencer.ButtonAction.DataBuilder().pressed(true).get();
                                        })
                                        .duration(3f)
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
}
