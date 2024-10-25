package org.firstinspires.ftc.teamcode;

public class PID {
    public static class LinearCoefficients {
        public float P, I, D;
    }

    public static class Linear {
        float accumulatedError;
        float previousError;

        LinearCoefficients coeff;

        public float loop(float currentX, float targetX, float dt) {
            float proportional = targetX - currentX;
            float integral = accumulatedError + proportional * dt;
            float derivative = (proportional - previousError) / dt;
            accumulatedError = integral;
            previousError = proportional;
            return dt * (coeff.P * proportional + coeff.I * integral + coeff.D * derivative);
        }

        public Linear(LinearCoefficients _coeff) {
            coeff = _coeff;
            accumulatedError = 0;
            previousError = 0;
        }
    }
}