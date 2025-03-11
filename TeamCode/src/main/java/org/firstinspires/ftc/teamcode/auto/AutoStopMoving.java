package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.controllers.RobotController;

public class AutoStopMoving extends AutoAction {

    @Override
    public float getDuration() {
        return 0.1f;
    }

    @Override
    public void loop(RobotController robot, float elapsed) {
        robot.motorControls.stopMotorAction.accept(robot);
    }
}
