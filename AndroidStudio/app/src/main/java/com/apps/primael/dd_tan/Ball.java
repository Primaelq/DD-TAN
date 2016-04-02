package com.apps.primael.dd_tan;

import android.graphics.RectF;
import android.view.SurfaceHolder;

public class Ball
{

    RectF rectF;
    int left, top;
    final int size = 100;
    int [] velocity = {5, 10};

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

    public  void checkFall(SurfaceHolder holder)
    {
        if(this.top + this.velocity[1] < holder.getSurfaceFrame().height())
        {
            this.move();
        }
        else
        {
            this.left = 100;
            this.top = 100;

            CustomView.frames = 0;

            Main.turnStarted = false;
        }
    }
}
