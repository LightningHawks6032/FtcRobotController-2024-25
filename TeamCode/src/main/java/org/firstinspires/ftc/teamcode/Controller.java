package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.Gamepad;

public final class Controller {
    Gamepad gamepad;

    public float leftStickY() {return gamepad.left_stick_y;}
    public float leftStickX() {return gamepad.left_stick_x;}
    public float rightStickX() {return gamepad.right_stick_x;}
    public float rightStickY() {return gamepad.right_stick_y;}

    public Vec2 leftStick() {return new Vec2(gamepad.left_stick_x, gamepad.left_stick_y);}
    public Vec2 rightStick() {return new Vec2(gamepad.right_stick_x, gamepad.right_stick_y);}


    public Controller(Gamepad _gamepad) {
        gamepad = _gamepad;
    }
}
