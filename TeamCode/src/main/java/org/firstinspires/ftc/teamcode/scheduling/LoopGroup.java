package org.firstinspires.ftc.teamcode.scheduling;

import org.firstinspires.ftc.teamcode.controllers.RobotController;

import java.util.function.Consumer;

public class LoopGroup {
    Consumer<RobotController>[] loops;

    public LoopGroup(Consumer<RobotController>[] _loops) {
        loops = _loops;
    }

    public void loop(RobotController robot) {
        if (loops == null) {System.err.println("Attempted to loop on empty LoopGroup!");return;}
        for (Consumer<RobotController> i : loops) {
            i.accept(robot);
        }
    }
}
