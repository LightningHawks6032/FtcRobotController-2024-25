package org.firstinspires.ftc.teamcode.util;

import java.lang.reflect.InvocationTargetException;

public class CubicBezier {
    protected Vec2 p0,p1,p2,p3;
    public Velocity vel;
    public Acceleration acc;
    /**
     * @param t in [0, 1]
     * @return point on curve at t
     */
    public Vec2 at(float t) {
        float oneMinusT = (1-t);
        return p0.scale((float) Math.pow(oneMinusT, 3))
                .add(p1.scale((float) (3 * t * Math.pow(oneMinusT, 2))))
                .add(p2.scale((float) (3 * t * Math.pow(oneMinusT, 2))))
                .add(p3.scale((float)Math.pow(t, 3)));
    }
    public class Velocity {
        public Vec2 at(float t) {
            float oneMinusT = (1-t);
            return (p1.sub(p0)).scale(3*oneMinusT*oneMinusT)
                    .add(
                            (p2.sub(p1)).scale(6*oneMinusT*t)
                    )
                    .add(
                            (p3.sub(p2)).scale(3*t*t)
                    );
        }
    }
    public class Acceleration {
        public Vec2 at(float t) {
            float oneMinusT = 1-t;
            return (p2.sub(p1.scale(2)).add(p0)).scale(6)
                    .add(
                            (p3.sub(p2.scale(2)).add(p1)).scale(6*t)
                    );
        }
    }
    public CubicBezier() {
        vel = new Velocity();
        acc = new Acceleration();
    }
    public static class Builder  {
        CubicBezier curve;
        public Builder() {
            curve = new CubicBezier();
            curve.p0 = new Vec2(0,0);
            curve.p1 = new Vec2(0,0);
            curve.p2 = new Vec2(0,0);
            curve.p3 = new Vec2(0,0);
        }
        public Builder p0(Vec2 _p) {
            curve.p0 = _p;
            return this;
        }
        public Builder p1(Vec2 _p) {
            curve.p1 = _p;
            return this;
        }
        public Builder p2(Vec2 _p) {
            curve.p2 = _p;
            return this;
        }
        public Builder p3(Vec2 _p) {
            curve.p3 = _p;
            return this;
        }
        public CubicBezier get() {return curve;}
    }
}
