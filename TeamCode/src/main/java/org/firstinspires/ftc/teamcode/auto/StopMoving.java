package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.Vec2;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.scheduling.ActionSequencer;

public class StopMoving extends AutoAction {

    @Override
    float getDuration() {
        return 0.1f;
    }

    @Override
    void loop(RobotController robot, float elapsed) {
        robot.motorControls.stopMotorAction.accept(robot);
    }
}
