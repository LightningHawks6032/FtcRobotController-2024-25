package org.firstinspires.ftc.teamcode;

public class Vec2Rot extends Vec2{
    public float r;
    public Vec2Rot(float _x, float _y, float _r) {
        super(_x, _y);
        r =_r;
    }
    public Vec2 toVec2() {
        return new Vec2(x, y);
    }

}
