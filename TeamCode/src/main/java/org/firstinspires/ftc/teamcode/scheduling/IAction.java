package org.firstinspires.ftc.teamcode.scheduling;

import org.firstinspires.ftc.teamcode.controllers.RobotController;

public interface IAction <DataType>{
    void loop(RobotController robot, DataType data);
}
