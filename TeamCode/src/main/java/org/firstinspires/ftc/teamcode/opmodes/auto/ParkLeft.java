package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.auto.AutoSequence;
import org.firstinspires.ftc.teamcode.auto.ConstantAutoAction;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;
import org.firstinspires.ftc.teamcode.util.Vec2;

@Autonomous(name = "Park Left", group="Comp")
public class ParkLeft extends OpMode {
    AutoSequence seq;
    RobotController robot;

    @Override
    public void init() {
        robot = new RobotController(hardwareMap, telemetry);
        robot.init();
        robot.outtakeClawControls.ensureClosed();
        seq = new AutoSequence.Builder(robot, telemetry)
                .actions(
                        new ConstantAutoAction.Builder<ActionSequencer.StickAction.Data>()
                                .action(robot.motorControls.getMoveAction())
                                .dataFunc(i -> new ActionSequencer.StickAction.DataBuilder().stick(new Vec2(1, 0)).get())
                                .duration(1.5f)
                                .get()
                )
                .get();
    }

    @Override
    public void start() {
        seq.start();
    }

    @Override
    public void loop() {
        seq.loop();
    }
}
