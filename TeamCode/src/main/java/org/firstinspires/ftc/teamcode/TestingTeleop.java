package org.firstinspires.ftc.teamcode;
//System.out.println("skibidi rizz"); :)
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

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
        void zeroAllMotors() {
            powerAllMotors(0);
        }

        //positive power -> forward, negative power -> backward
        void moveForward() {
            ul.setDirection(DcMotor.Direction.FORWARD);
            ur.setDirection(DcMotor.Direction.REVERSE);
            ll.setDirection(DcMotor.Direction.REVERSE);
            lr.setDirection(DcMotor.Direction.FORWARD);
        }
        //positive power -> right, negative power -> backward
        void moveSide(double power) {
            if (power == 0) {return;}
            if (power > 0) {
                ul.setDirection(DcMotor.Direction.REVERSE);
                ur.setDirection(DcMotor.Direction.REVERSE);
                ll.setDirection(DcMotor.Direction.REVERSE);
                lr.setDirection(DcMotor.Direction.REVERSE);
            }
            else {
                ul.setDirection(DcMotor.Direction.REVERSE);
                ur.setDirection(DcMotor.Direction.REVERSE);
                ll.setDirection(DcMotor.Direction.REVERSE);
                lr.setDirection(DcMotor.Direction.REVERSE);
            }
        }

        //positive power -> clockwise, negative power -> counter clockwise
        void rotate(double power) {
            if (power == 0) {return;}
            if (power > 0) {
                ul.setDirection(DcMotor.Direction.REVERSE);
                ur.setDirection(DcMotor.Direction.REVERSE);
                ll.setDirection(DcMotor.Direction.FORWARD);
                lr.setDirection(DcMotor.Direction.FORWARD);
            }
            /*else {
                ul.setDirection(DcMotor.Direction.REVERSE);
                ur.setDirection(DcMotor.Direction.REVERSE);
                ll.setDirection(DcMotor.Direction.FORWARD);
                lr.setDirection(DcMotor.Direction.FORWARD);
            }*/
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
             // TODO: Change direction when needed
            if (yaxis != 0 ) {
                moveForward();
                powerAllMotors(yaxis * 0.5);
            }
            else if (xaxis != 0) {
                moveSide(xaxis);
                powerAllMotors(xaxis * 0.5);
            }
            else if (raxis != 0) {
                rotate(raxis);
                powerAllMotors(raxis * 0.5);
            }
            else {
                zeroAllMotors();
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

    MotorControls m;
    @Override
    public void init() {

        motorupleft = hardwareMap.dcMotor.get("motor 1");
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
        //slideleft.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void loop() {
        m.loop(new ProcessData());

        /*motorupleft.setPower(gamepad1.left_stick_y);
        motorupright.setPower(gamepad1.right_stick_y);
        motorlowleft.setPower(gamepad1.left_stick_y);
        motorlowright.setPower(gamepad1.right_stick_y);
        //slideright.setPower(gamepad1.right_trigger);
        //slideleft.setPower(gamepad1.left_trigger);

        if(gamepad1.right_bumper)
        {
            motorupleft.setPower(0.5);
            motorupright.setPower(0.5);
            motorlowleft.setPower(0.5);
            motorlowleft.setPower(0.5);
            motorupleft.setDirection(DcMotor.Direction.FORWARD);
            motorlowleft.setDirection(DcMotor.Direction.REVERSE);
            motorupright.setDirection(DcMotor.Direction.REVERSE);
            motorlowright.setDirection(DcMotor.Direction.FORWARD);

        }
        if(gamepad1.left_bumper)
        {
            motorupleft.setPower(0.5);
            motorupright.setPower(0.5);
            motorlowleft.setPower(0.5);
            motorlowleft.setPower(0.5);
            motorupleft.setDirection(DcMotor.Direction.REVERSE);
            motorlowleft.setDirection(DcMotor.Direction.FORWARD);
            motorupright.setDirection(DcMotor.Direction.FORWARD);
            motorlowright.setDirection(DcMotor.Direction.REVERSE);

        }*/
    }

}
