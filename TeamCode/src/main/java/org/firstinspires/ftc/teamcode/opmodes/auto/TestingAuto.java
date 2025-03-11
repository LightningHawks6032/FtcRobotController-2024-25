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
        robot.outtakeSlideControls.extendSlide.loop(robot, new ActionSequencer.ButtonAction.DataBuilder().pressed(true).get());
        robot.outtakeClawControls.open();
        //robot.outtakeSlideControls.up = false;
        seq = new AutoSequence.Builder(robot, telemetry)
                .actions(
                                new ConstantAutoAction.Builder<ActionSequencer.ButtonAction.Data>()
                                    .action(robot.outtakeSlideControls.retractSlide)
                                    .dataFunc(i -> new ActionSequencer.ButtonAction.DataBuilder().pressed(true).get())
                                    .duration(0.1f)
                                    .get(),
                                new AutoPathFollowing.Builder(robot)
                                        .curve(
                                                new CubicBezier.Builder()
                                                        .p0(new Vec2(2.74f, 1.73f))
                                                        .p1(new Vec2(2.89f, 1.49f))
                                                        .p2(new Vec2(2.47f, 1.06f))
                                                        .p3(new Vec2(2.47f, 0.51f))
                                                        .get()
                                        )
                                        .cutoff(2.5f)
                                        .duration(2.5f)
                                        .speed(0.8f)
                                        .get(),
                        new ConstantAutoAction.Builder<ActionSequencer.ButtonAction.Data>()
                                .action(robot.armControls.powerUpSlide)
                                .dataFunc(i -> {
                                    i.armControls.armLoop.accept(i);
                                    return new ActionSequencer.ButtonAction.DataBuilder().pressed(true).get();
                                })
                                .duration(0.3f)
                                .get(),
                                new ConstantAutoAction.Builder<ActionSequencer.ButtonAction.Data>()
                                        .action(robot.armControls.raiseSlide)
                                        .dataFunc(i -> {
                                            i.armControls.armLoop.accept(i);
                                            return new ActionSequencer.ButtonAction.DataBuilder().pressed(true).get();
                                        })
                                        .duration(3f)
                                        .get(),
                        new AutoStopMoving()

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
