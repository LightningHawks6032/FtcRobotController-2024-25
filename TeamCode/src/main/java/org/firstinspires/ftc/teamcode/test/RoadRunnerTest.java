package org.firstinspires.ftc.teamcode.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PID;
import org.firstinspires.ftc.teamcode.controllers.GamepadController;
import org.firstinspires.ftc.teamcode.controllers.RobotController;
import org.firstinspires.ftc.teamcode.hardware.OdometryPosition;
import org.firstinspires.ftc.teamcode.scheduling.InputResponseManager;

@Autonomous(name="Test", group = "Test")
public class RoadRunnerTest extends OpMode {
    OdometryPosition pos;
    DcMotorEx pod, lod, rod;
    RobotController robot;
    GamepadController g1;
    InputResponseManager controls;
    PID.Linear velPIDtl, posPIDtl;
    float t = 0;


    @Override
    public void init() {
        g1 = new GamepadController(gamepad1);

        //pod = (DcMotorEx)hardwareMap.get("pod");
        //rod = (DcMotorEx)hardwareMap.dcMotor.get("rod");
        //lod = (DcMotorEx)hardwareMap.dcMotor.get("lod");
        pos = new OdometryPosition(lod, rod, pod);
        robot = new RobotController(hardwareMap, telemetry);
        robot.init();
        controls = new InputResponseManager.Builder(g1, robot)
                .leftStickAction(robot.motorControls.getMoveAction())
                .get();
        posPIDtl = new PID.Linear(new PID.LinearCoefficients(0.1f, 0.01f,0.05f));
        velPIDtl = new PID.Linear(new PID.LinearCoefficients(0.5f, 0.05f,0.1f));
    }

    @Override
    public void loop() {
        //pos.loop();
        //telemetry.addData("Position", pos.pos.toString());
        //telemetry.addData("pod", pod.getCurrentPosition());
        //telemetry.addData("lod", lod.getCurrentPosition());
        //telemetry.addData("rod", rod.getCurrentPosition());
        robot.motorControls.printDebug();
    }
}
