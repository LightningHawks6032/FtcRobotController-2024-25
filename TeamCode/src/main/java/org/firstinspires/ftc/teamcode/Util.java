package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Util {
    public static IMotor tryGetMotorElseDebug(HardwareMap hardwareMap, String deviceName, String id, Telemetry telemetry, MotorSpec spec) {
        if (hardwareMap.dcMotor.contains(deviceName)) {
            return new DCMotor(hardwareMap.dcMotor.get(deviceName), spec, DcMotorSimple.Direction.FORWARD);
        }
        return new DebugMotor(id, telemetry, spec);
    }
}
