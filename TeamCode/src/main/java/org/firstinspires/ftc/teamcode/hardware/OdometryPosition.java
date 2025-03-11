package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.util.Vec2Rot;

public class OdometryPosition {
    public Vec2Rot pos;

    DcMotorEx lod, rod, pod;
    int previousLod, previousRod, previousPod;
    int forwardOffset;
    float trackWidth = 1.2f;

    public OdometryPosition(DcMotorEx _l, DcMotorEx _r, DcMotorEx _p) {
        lod = _l;
        rod = _r;
        pod = _p;
        pos = new Vec2Rot(0,0,0);
    }

    public void loop() {
        int dxl = lod.getCurrentPosition() - previousLod;
        int dxr = rod.getCurrentPosition() - previousRod;
        int centerPos = pod.getCurrentPosition() - previousPod;

        float phi = (dxl - dxr) / trackWidth;

        int midPos = (dxl + dxr) / 2;
        float perpPos = centerPos - forwardOffset * trackWidth;

        float dx = (float)(midPos * Math.cos(pos.r) - perpPos * Math.sin(pos.r));
        float dy = (float)(midPos * Math.sin(pos.r) + perpPos*Math.cos(pos.r));

        pos.x += dx;
        pos.y += dy;
        pos.r += phi;

        previousRod = rod.getCurrentPosition();
        previousLod = lod.getCurrentPosition();
        previousPod = pod.getCurrentPosition();
    }
}
