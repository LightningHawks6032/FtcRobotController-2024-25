package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PID {
    public static class LinearCoefficients {
        public float P, I, D;
        public LinearCoefficients(float _P, float _I, float _D) {
            P = _P;
            I = _I;
            D = _I;
        }
    }

    public static class Linear {
        float accumulatedError;
        float previousError;
        ElapsedTime timer;

        LinearCoefficients coeff;

        public float loop(float currentX, float targetX) {
            float dt = (float) timer.time();
            float proportional = targetX - currentX;
            float integral = accumulatedError + proportional * dt;
            float derivative = (proportional - previousError) / dt;
            accumulatedError = integral;
            previousError = proportional;
            timer.reset();
            return dt * (coeff.P * proportional + coeff.I * integral + coeff.D * derivative);
        }

        public Linear(LinearCoefficients _coeff) {
            coeff = _coeff;
            accumulatedError = 0;
            previousError = 0;
            timer = new ElapsedTime();
        }
    }
}