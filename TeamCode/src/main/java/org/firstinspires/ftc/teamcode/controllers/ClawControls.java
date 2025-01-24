package org.firstinspires.ftc.teamcode.controllers;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Toggle;
import org.firstinspires.ftc.teamcode.hardware.IMotor;

public class ClawControls {
    IMotor c;
    float start, stop;
    Telemetry telemetry;
    Toggle toggle;
    public ClawControls(IMotor _c, float _start, float _stop, Telemetry _telemetry) {
        c =_c;
        start = _start;
        stop = _stop;
        telemetry = _telemetry;
        toggle = new Toggle(false);
    }

    void close() {
        c.setPower(stop);
    }
    void open() {
        c.setPower(start);
    }

    public void ensureClosed() {
        if (toggle.state) {
            close();
            toggle.state = true;
        }
    }

    public void openAfter(int delay_ms) {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        open();
                        toggle.state = true;
                    }
                },
                delay_ms
        );
    }

    public void loop(boolean power) {
        boolean released = toggle.released;
        toggle.loop(power);
        if (released != toggle.released) {
            if (toggle.state) {open();}
            else {close();}
        }

    }
}
