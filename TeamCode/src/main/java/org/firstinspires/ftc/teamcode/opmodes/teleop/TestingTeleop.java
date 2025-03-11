package org.firstinspires.ftc.teamcode.opmodes.teleop;
//System.out.println("skibidi rizz"); :)
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.auto.AutoSequence;
import org.firstinspires.ftc.teamcode.auto.ConstantAutoAction;
import org.firstinspires.ftc.teamcode.controllers.GamepadController;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;
import org.firstinspires.ftc.teamcode.scheduling.AutoSequenceAction;
import org.firstinspires.ftc.teamcode.scheduling.InputResponseManager;
import org.firstinspires.ftc.teamcode.util.Vec2Rot;

@TeleOp(name = "TeleOp", group="Comp")
public class TestingTeleop extends OpMode {
    GamepadController g1, g2;
    RobotController robot;
    InputResponseManager jaydenControls;
    InputResponseManager ryanControls;

    @Override
    public void init() {
        g1 = new GamepadController(gamepad1);
        g2 = new GamepadController(gamepad2);

        robot = new RobotController(hardwareMap, telemetry);
        robot.init();

        robot.intakeClawControls.ensureClosed();
        //robot.outtakeClawControls.open();
        //robot.outtakeClawControls.toggle.state = false;
        //robot.outtakeClawControls.close();

        jaydenControls = new InputResponseManager.Builder(g1, robot)
                .leftStickAction(robot.motorControls.getMoveAction())
                .rightStickAction(robot.motorControls.getRotateAction())
                .leftBumperAction(robot.motorControls.getSlowModeAction())
                .get();

        ryanControls = new InputResponseManager.Builder(g2, robot)
                .AAction(robot.intakeClawControls.clawAction)
                .BAction(robot.intakeClawRotationControls.clawAction)
                .XAction(
                        robot.armControls.lowerSlide
                        //robot.outtakeSlideControls.extendSlide
                )
                .YAction(
                        //robot.outtakeClawControls.clawAction,
                        //robot.intakeClawControls.clawAction,
                        //robot.outtakeSlideControls.retractSlide,
                                        new AutoSequenceAction(new AutoSequence.Builder(robot, telemetry)
                                                .actions(
                                                        new ConstantAutoAction.Builder<ActionSequencer.ButtonAction.Data>()
                                                                .action(robot.intakeClawControls.clawAction)
                                                                .dataFunc(i -> {
                                                                    i.intakeClawControls.ensureClosed();
                                                                    return new ActionSequencer.ButtonAction.DataBuilder().pressed(false).get();
                                                                })
                                                                .duration(1.5f)
                                                                .get(),
                                                        new ConstantAutoAction.Builder<ActionSequencer.ButtonAction.Data>()
                                                                .action(robot.outtakeSlideControls.retractSlide)
                                                                .dataFunc(i -> new ActionSequencer.ButtonAction.DataBuilder().pressed(true).get())
                                                                .duration(0.1f)
                                                                .get(),
                                                        new ConstantAutoAction.Builder<ActionSequencer.ButtonAction.Data>()
                                                                .action(robot.armControls.raiseSlide)
                                                                .dataFunc(i -> {
                                                                    i.armControls.armLoop.accept(i);
                                                                    return new ActionSequencer.ButtonAction.DataBuilder().pressed(true).get();
                                                                })
                                                                .duration(3f)
                                                                .get()
                                                        //open intake claw, extend outtake, raise slides
                                                )
                                                .get()
                                        )
                )
                .rightStickAction(
                        new ActionSequencer.AxialStickAction.Builder()
                                .vertical(robot.horizontalSlideControls.slideAction)
                                .get()
                )
                .leftStickAction(
                        new ActionSequencer.CardinalStickAction.Builder()
                                .left(robot.intakeClawSpinControls.rotClockwise)
                                .right(robot.intakeClawSpinControls.rotCounterClockwise)
                                .get()
                )
                .DPadAction(
                        new ActionSequencer.CardinalStickAction.Builder()
                                .left(
                                        new ActionSequencer.SwitchButtonAction.Builder()
                                                .first(robot.outtakeSlideControls.retractSlide)
                                                .second(robot.outtakeSlideControls.extendSlide)
                                                .get()
                                )
                                .right(robot.outtakeClawControls.clawAction)
                                .get()
                )
                .leftBumperAction(robot.armControls.powerDownSlide)
                .rightBumperAction(robot.armControls.powerUpSlide)
                .loops(
                        robot.intakeClawSpinControls.intakeClawSpinLoop,
                        robot.armControls.armLoop
                )
                .get();
    }

    @Override
    public void loop() {
        robot.loop(new Vec2Rot(g1.leftStick(), g1.rightStick().x), g1.leftTrigger(),  (g2.pressedB() ? -1 : 0)  + (g2.pressedA() ? 1 : 0),(g1.pressedX() ? -1 : 0)  + (g1.pressedY() ? 1 : 0), (int)Math.signum(g2.leftStickY()), g2.horizontalDPad() == 1, (int)Math.signum(g2.rightStickY()), g2.horizontalDPad() == -1, g2.verticalDPad(), g2.pressedY());
        jaydenControls.loop();
        ryanControls.loop();//g2InputResponse.loop();
    }
}
