package org.firstinspires.ftc.teamcode.util;

import org.firstinspires.ftc.teamcode.hardware.IMotor;

public class PIDMotor {
    IMotor motor;
    PID.Linear velPID, posPID;
    public PIDMotor(IMotor _motor, PID.Linear pos, PID.Linear vel) {
        motor = _motor;
        velPID = vel;
        posPID = pos;
    }
    public void setPosition(float pos) {
        float targetVel = posPID.loop(motor.getPosition(), pos);

        /*
         * A field is 12x12
         * 1 unit in this field is 2 ft
         * 96mm wheel circumference = 383.6 ticks
         * 1 field unit * (2ft/1 field unit) * (308.4mm/1ft) * (383.6 ticks / 96mm) = 2464.63 ticks
         */

        //float acc = velPID.loop(motor.getVelocity(), targetVel);
        motor.setVelocity(/*motor.getVelocity() + acc*/targetVel);
    }
}
