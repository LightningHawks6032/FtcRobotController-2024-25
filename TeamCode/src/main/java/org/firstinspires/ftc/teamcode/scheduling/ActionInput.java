package org.firstinspires.ftc.teamcode.scheduling;

import org.firstinspires.ftc.teamcode.Vec2Rot;

import java.util.HashMap;
import java.util.Map;

public class ActionInput {
    public Vec2Rot moveDirection;
    public float slowMode;
    public float verticalArmControlPower;
    public int horizontalSlideControlPower;
    public int pickUpSlideControlPower;
    public boolean floorClawControlPower;
    public int floorClawRotationControlPower;
    public boolean hangClawPower;
    public boolean lock;

    public ActionInput() {

        moveDirection = new Vec2Rot(0, 0, 0);
        slowMode = 0;
        verticalArmControlPower = 0;
        horizontalSlideControlPower = 0;
        pickUpSlideControlPower = 0;
        floorClawControlPower = false;
        lock = false;
    }
}
