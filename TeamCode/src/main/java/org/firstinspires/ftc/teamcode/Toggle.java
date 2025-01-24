package org.firstinspires.ftc.teamcode;

public class Toggle {
    public boolean state;
    public boolean released;
    public Toggle(boolean _state) {
        state = _state;
        released = true;
    }

    public void loop(boolean input) {

        if (input && released) {
            state = !state;
            released = false;
        }
        else if (!input) {
            released = true;
        }
    }
}
