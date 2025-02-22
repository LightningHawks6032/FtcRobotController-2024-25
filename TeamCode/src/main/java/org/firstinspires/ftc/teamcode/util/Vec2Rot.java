package org.firstinspires.ftc.teamcode.util;

public class Vec2Rot extends Vec2 {
    public float r;
    public Vec2Rot(float _x, float _y, float _r) {
        super(_x, _y);
        r =_r;
    }

    public Vec2Rot(Vec2 _v, float _r) {
        super(_v.x, _v.y);
        r = _r;
    }

    public Vec2 toVec2() {
        return new Vec2(x, y);
    }

}
