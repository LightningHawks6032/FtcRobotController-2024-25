package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Toggle;
import org.firstinspires.ftc.teamcode.hardware.DCMotor;
import org.firstinspires.ftc.teamcode.hardware.IMotor;

/// Controls vertical slide (for hanging)
public class ArmControls {

    DCMotor sr, sl;
    Telemetry telemetry;
    Toggle lockToggle;
    //11566
    int TOP=5000,BOT=0, OFFSET=40;
    int target;
    public ArmControls(DCMotor _sr, DCMotor _sl, Telemetry _telemetry) {
        sr = _sr;
        sl = _sl;
        telemetry = _telemetry;
        lockToggle = new Toggle(false);
        target = 0;
    }

    public void moveTo(int pos) {
        sr.encoder.setPosition(pos);
        sl.encoder.setPosition(pos);
    }

    void moveWithPower(float power) {
        sr.setPower(power);
        sl.setPower(power);

    }



    public void loop(float power, int vDPad, boolean lock) {
        telemetry.addData("Pos", sr.getPosition() + ", " + sl.getPosition());
        //moveWithPower(power);
        lockToggle.loop(lock);
        if (lockToggle.state) {
            sr.encoder.lock();
            sl.encoder.lock();
        }
        else {
            sr.encoder.unlock();
            sl.encoder.unlock();
        }
        if (sr.encoder.runningToPosition) {
            sr.encoder.runningToPosition = Math.abs(sr.getPosition() - target) > OFFSET;
            sl.encoder.runningToPosition = Math.abs(sl.getPosition() - target) > OFFSET;
            if ((Math.abs(sr.getPosition() - target) < OFFSET)) {
                sr.encoder.unlock();
                sl.encoder.unlock();
            }
        }
        if (!lockToggle.state) {
            if (vDPad != 0 || sr.encoder.runningToPosition) {
                if (vDPad == 1) {
                    sr.encoder.setPosition(TOP);
                    sl.encoder.setPosition(TOP);
                    sl.setPower(0.5f);
                    sr.setPower(0.5f);

                    target = TOP;
                }
                else if (vDPad == -1) {
                    sr.encoder.setPosition(BOT);
                    sl.encoder.setPosition(BOT);
                    sl.setPower(0.5f);
                    sr.setPower(0.5f);

                    target = BOT;
                }
            }
            else {
            moveWithPower(power);
            }
        }
    }
}
