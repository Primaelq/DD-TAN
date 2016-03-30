package com.apps.primael.dd_tan;

import android.graphics.RectF;

public class Ball {

    RectF rectF;
    int left, top;
    final int size = 100;
    int [] velocity = {1, 1};

    public Ball ()
    {
        left = 200;
        top = 200;
        rectF = new RectF ();
        rectF.set(left, top, left+size, top+size);
    }

    public void setVelocity (int dX, int dY)
    {
        velocity [0] = dX;
        velocity [1] = dY;
    }

    public void move ()
    {
        left += velocity[0];
        top += velocity[1];
        rectF.set(left, top, left+size, top+size);
    }
}
