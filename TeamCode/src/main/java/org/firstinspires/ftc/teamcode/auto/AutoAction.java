package org.firstinspires.ftc.teamcode.auto;

import org.firstinspires.ftc.teamcode.controllers.RobotController;

public abstract class AutoAction{
    abstract float getDuration();

    abstract void loop(RobotController robot, float elapsed);
}
