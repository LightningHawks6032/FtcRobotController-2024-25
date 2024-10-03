package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.function.Function;

@TeleOp(name = "Testing")
public class TestingTeleop extends OpMode {
    //Data class that gets passed into a process
    public static class ProcessData {

    }
    //Defines a process to run on initialization and every loop call
    public interface IProcess {
        void loop(ProcessData data);
        void init(ProcessData data);
    }
    //Exposes motor functionality
    public static final class MotorControls implements IProcess{
        DcMotor ul, ur, ll, lr; // up left, up right, low left, low right
        Gamepad gamepad;
        void powerAllMotors(double power) {
            ul.setPower(power);
            ur.setPower(power);
            ll.setPower(power);
            lr.setPower(power);
        }

        //positive power -> forward, negative power -> backward
        void moveForward() {
            ul.setDirection(DcMotor.Direction.FORWARD);
            ur.setDirection(DcMotor.Direction.FORWARD);
            ll.setDirection(DcMotor.Direction.FORWARD);
            lr.setDirection(DcMotor.Direction.FORWARD);
        }
        //positive power -> right, negative power -> backward
        void moveSide(double power) {
            if (power == 0) {return;}
            if (power > 0) {
                ul.setDirection(DcMotor.Direction.FORWARD);
                ur.setDirection(DcMotor.Direction.REVERSE);
                ll.setDirection(DcMotor.Direction.REVERSE);
                lr.setDirection(DcMotor.Direction.FORWARD);
            }
            else {
                ul.setDirection(DcMotor.Direction.REVERSE);
                ur.setDirection(DcMotor.Direction.FORWARD);
                ll.setDirection(DcMotor.Direction.FORWARD);
                lr.setDirection(DcMotor.Direction.REVERSE);
            }
        }

        //positive power -> clockwise, negative power -> counter clockwise
        void rotate(double power) {
            if (power == 0) {return;}
            if (power > 0) {
                ul.setDirection(DcMotor.Direction.FORWARD);
                ur.setDirection(DcMotor.Direction.REVERSE);
                ll.setDirection(DcMotor.Direction.FORWARD);
                lr.setDirection(DcMotor.Direction.REVERSE);
            }
            else {
                ul.setDirection(DcMotor.Direction.REVERSE);
                ur.setDirection(DcMotor.Direction.FORWARD);
                ll.setDirection(DcMotor.Direction.REVERSE);
                lr.setDirection(DcMotor.Direction.FORWARD);
            }
        }

        public MotorControls(DcMotor _ul, DcMotor _ur, DcMotor _ll, DcMotor _lr, Gamepad _gamepad) {
            ul = _ul;
            ur = _ur;
            ll = _ll;
            lr = _lr;
            gamepad = _gamepad;
        }

        @Override
        public void loop(ProcessData data) {
            float yaxis = gamepad.left_stick_y;
            float xaxis = gamepad.left_stick_x;
            float raxis = gamepad.right_stick_x;

            if (yaxis != 0 ) {
                moveForward();
                powerAllMotors(yaxis * 0.5);
            }
            else if (xaxis != 0) {
                moveSide(xaxis);
                powerAllMotors(xaxis);
            }
            else if (raxis != 0) {
                rotate(raxis);
                powerAllMotors(raxis);
            }

        }

        @Override
        public void init(ProcessData data) {

        }
    }

    DcMotor motorupleft;
    DcMotor motorupright;
    DcMotor motorlowleft;
    DcMotor motorlowright;
    //DcMotor slideleft;
    //DcMotor slideright;
    DcMotor motor;
    MotorControls m;
    Function<Float, Float>[] funcs = new Function[3];
    int currFunc = 0;
    @Override
    public void init() {
        motor = hardwareMap.dcMotor.get("motor 1");
        funcs[0] = (Float a) -> {if (Math.abs(a) <= 0.2f) {return 0f;} return 0.5f * a;};
        funcs[1] = (Float a) -> {if (Math.abs(a) <= 0.2f) {return 0f;} return Math.signum(a) * (float)(0.54 * Math.log10(Math.abs(a)) + 0.47);};
        funcs[2] = (Float a) -> {if (Math.abs(a) <= 0.2f) {return 0f;} return Math.signum(a) * (float)(0.048 * Math.pow(10, Math.abs(a)) + 0.025);};
        //for (int i =0; i < hardwareMap.dcMotor.size(); i++) {
        //    System.out.println(hardwareMap.dcMotor.entrySet().toArray()[i]);
        //}
        /*motorupleft = hardwareMap.dcMotor.get("motor 1");
        motorupright = hardwareMap.dcMotor.get("motor 2");
        motorlowleft = hardwareMap.dcMotor.get("motor 3");
        motorlowright = hardwareMap.dcMotor.get("motor 4");

        //slideleft = hardwareMap.dcMotor.get("leftslide");
        //slideright = hardwareMap.dcMotor.get("rightslide");

        motorupleft.setDirection(DcMotor.Direction.FORWARD);
        motorlowleft.setDirection(DcMotor.Direction.FORWARD);
        motorupright.setDirection(DcMotor.Direction.REVERSE);
        motorlowright.setDirection(DcMotor.Direction.REVERSE);
        m = new MotorControls(motorupleft, motorupright, motorlowleft, motorlowright, gamepad1);
        //slideleft.setDirection(DcMotor.Direction.FORWARD);*/
    }

    @Override
    public void loop() {
        //m.loop(new ProcessData());
        if (gamepad1.right_bumper) {
            currFunc = (currFunc + 1) % 3;
        }
        motor.setPower(funcs[currFunc].apply(gamepad1.left_stick_y));
    }
}
