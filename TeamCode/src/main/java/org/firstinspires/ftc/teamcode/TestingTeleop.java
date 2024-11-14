package org.firstinspires.ftc.teamcode;
//System.out.println("skibidi rizz"); :)
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "TeleOp")
public class TestingTeleop extends OpMode {
    DCMotor motorupleft;
    DCMotor motorupright;
    DCMotor motorlowleft;
    DCMotor motorlowright;
    DcMotor sl;
    DcMotor sr;
    DCMotor motor;
    MotorControls m;
    GamepadController c;
    @Override
    public void init() {
        motorupleft = new DCMotor(hardwareMap.dcMotor.get("motor 1"), null);
        motorupright = new DCMotor(hardwareMap.dcMotor.get("motor 2"), null);
        motorlowleft = new DCMotor(hardwareMap.dcMotor.get("motor 3"), null);
        motorlowright = new DCMotor(hardwareMap.dcMotor.get("motor 4"), null);
        sl = hardwareMap.get(DcMotor.class, "sl" );
        sr = hardwareMap.get(DcMotor.class, "sr" );
        sr.setDirection(DcMotor.Direction.REVERSE);
        sl.setDirection(DcMotor.Direction.REVERSE);
        sl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        int srinitialposition = sr.getCurrentPosition();
        int slinitialposition = sl.getCurrentPosition();
        c = new GamepadController(gamepad1);
        m = new MotorControls(motorupleft, motorupright, motorlowleft, motorlowright, telemetry);

    }

    @Override
    public void loop() {
        m.loop(new Vec2Rot(c.leftStick(), c.rightStick().x));
        if(gamepad2.b && sr.getCurrentPosition() <= 5000)
        {
            sl.setTargetPosition(5000);
            sr.setTargetPosition(5000);
            sl.setPower(0.9);
            sr.setPower(0.9);

        }else if(gamepad2.a && sr.getCurrentPosition() >= 0 )
        {
            sl.setTargetPosition(0);
            sr.setTargetPosition(0);
            sl.setPower(-0.9);
            sr.setPower(-0.9);
        }
        else
        {
            sl.setPower(0);
            sr.setPower(0);
        }
        if(gamepad2.left_stick_y > 0 )
        {
            sl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            sr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            while( sr.getCurrentPosition() <= 5000)
            {
                sl.setPower(gamepad2.left_stick_y * 0.75);
                sr.setPower(gamepad2.left_stick_y * 0.75);
            }
        }else if(gamepad2.left_stick_y < 0 )
        {
            sl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            sr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            while(sr.getCurrentPosition() >= -100)
            {
                sl.setPower(gamepad2.left_stick_y * 0.75);
                sr.setPower(gamepad2.left_stick_y * 0.75);
            }
        }
        else
        {
            sl.setPower(0);
            sr.setPower(0);
        }
        telemetry.addData("sl position", sl.getCurrentPosition());
        telemetry.addData("sr position", sr.getCurrentPosition());
    }
}
