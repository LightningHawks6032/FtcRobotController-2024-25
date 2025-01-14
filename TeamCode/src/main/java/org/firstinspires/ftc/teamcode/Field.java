package org.firstinspires.ftc.teamcode;

public class Field {
    public static class Rect {
        public float x,y,w,h;
        public Rect(float _x, float _y, float _w, float _h){
            x=_x;
            y=_x;
            w=_w;
            h=_h;
        }
    }

    public Rect[] obstacles;

}
