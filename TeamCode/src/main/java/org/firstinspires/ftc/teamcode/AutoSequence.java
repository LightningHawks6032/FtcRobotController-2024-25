package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.controllers.RobotController;

import java.util.List;

// Sequence of actions robot takes during auto
public class AutoSequence {
    public enum ActionType {
        MOVE,
        HANG_SLIDE,
        HORIZONTAL_SLIDE,
        HANG_CLAW,
        PICKUP_CLAW,
    }

    public interface IAction {
        ActionType type = null;

        boolean waitForNext = false;
        void init(RobotController _this);
        void loop(RobotController _this, double _dt);
        boolean isDone(RobotController _this, double _dt);
    }

    public static class MoveHorizontalSlide implements IAction {

        ActionType type = ActionType.HANG_SLIDE;
        PID.LinearCoefficients velCoeff = new PID.LinearCoefficients(1,1,1);
        PID.Linear velPID = new PID.Linear(velCoeff);
        PID.LinearCoefficients posCoeff = new PID.LinearCoefficients(1,1,1);
        PID.Linear posPID = new PID.Linear(velCoeff);
        float acc = 0;
        public MoveHorizontalSlide () {

        }

        int target;

        @Override
        public void init(RobotController _this) {

        }

        @Override
        public void loop(RobotController _this, double _dt) {

        }

        @Override
        public boolean isDone(RobotController _this, double _dt) {
            return false;
        }
    }

    List<IAction> actionList;

    public AutoSequence (List<IAction> _actionList) {
        actionList = _actionList;
    }
}
