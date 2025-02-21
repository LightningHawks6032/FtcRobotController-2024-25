package org.firstinspires.ftc.teamcode;
//System.out.println("skibidi rizz"); :)
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controllers.GamepadController;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;
import org.firstinspires.ftc.teamcode.scheduling.InputResponseManager;

@TeleOp(name = "TeleOp", group="Comp")
public class TestingTeleop extends OpMode {
    GamepadController g1, g2;
    RobotController robot;
    InputResponseManager g1InputResponse, g2InputResponse;
    InputResponseManager ryanControls;

    @Override
    public void init() {
        g1 = new GamepadController(gamepad1);
        g2 = new GamepadController(gamepad2);

        robot = new RobotController(hardwareMap, telemetry);
        robot.init();

        g1InputResponse = new InputResponseManager.Builder(g1, robot)
                .leftStickAction(robot.motorControls.getMoveAction())
                .rightStickAction(robot.motorControls.getRotateAction())
                .leftBumperAction(robot.motorControls.getSlowModeAction())
                //.AAction(robot.motorControls.getSlowModeAction())
                //.BAction(robot.hangClawControls.clawAction)
                .get();

        ryanControls = new InputResponseManager.Builder(g2, robot)
                .AAction(robot.floorClawControls.clawAction)
                .BAction(robot.floorClawRotationControls.clawAction)
                .XAction(robot.armControls.lowerSlide)
                .YAction(robot.armControls.raiseSlide)
                .rightStickAction(
                        new ActionSequencer.AxialStickAction.Builder()
                                .vertical(robot.horizontalSlideControls.slideAction)
                                .get()
                )
                .leftStickAction(
                        new ActionSequencer.CardinalStickAction.Builder()
                                .left(robot.floorClawSpinControls.rotClockwise)
                                .right(robot.floorClawSpinControls.rotCounterClockwise)
                                .get()
                )
                .DPadAction(
                        new ActionSequencer.CardinalStickAction.Builder()
                                .left(
                                        new ActionSequencer.SwitchButtonAction.Builder()
                                                .first(robot.pickupSlideControls.extendSlide)
                                                .second(robot.pickupSlideControls.retractSlide)
                                                .get()
                                )
                                .right(robot.hangClawControls.clawAction)
                                .get()
                )
                .leftBumperAction(robot.armControls.powerDownSlide)
                .rightBumperAction(robot.armControls.powerUpSlide)
                .loops(
                        robot.floorClawSpinControls.floorClawSpinLoop,
                        robot.armControls.armLoop
                )
                .get();

        g2InputResponse = new InputResponseManager.Builder(g2, robot)
                .DPadAction(
                        new ActionSequencer.CardinalStickAction.Builder()
                                .up(robot.armControls.raiseSlide)
                                .down(robot.armControls.lowerSlide)

                                .left(robot.floorClawRotationControls.clawAction)
                                .right(robot.floorClawControls.clawAction)
                                .get()
                )
                .leftStickAction(
                        new ActionSequencer.CardinalStickAction.Builder()
                                .up(robot.pickupSlideControls.extendSlide)
                                .down(robot.pickupSlideControls.retractSlide)
                                .get()
                )
                .rightStickAction(
                        new ActionSequencer.AxialStickAction.Builder()
                                .vertical(robot.horizontalSlideControls.slideAction)
                                .get()
                )
                .AAction(robot.armControls.powerDownSlide)
                .BAction(robot.armControls.powerUpSlide)
                .XAction(robot.floorClawSpinControls.rotClockwise)
                .YAction(robot.floorClawSpinControls.rotCounterClockwise)

                .loops(
                        robot.armControls.armLoop,
                        robot.floorClawSpinControls.floorClawSpinLoop
                )

                .get();
    }

    @Override
    public void loop() {
        robot.loop(new Vec2Rot(g1.leftStick(), g1.rightStick().x), g1.leftTrigger(),  (g2.pressedB() ? -1 : 0)  + (g2.pressedA() ? 1 : 0),(g1.pressedX() ? -1 : 0)  + (g1.pressedY() ? 1 : 0), (int)Math.signum(g2.leftStickY()), g2.horizontalDPad() == 1, (int)Math.signum(g2.rightStickY()), g2.horizontalDPad() == -1, g2.verticalDPad(), g2.pressedY());
        g1InputResponse.loop();
        ryanControls.loop();//g2InputResponse.loop();
    }
}
