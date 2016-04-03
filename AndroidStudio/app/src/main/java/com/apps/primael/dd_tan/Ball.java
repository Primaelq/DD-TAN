package com.apps.primael.dd_tan;

import android.graphics.RectF;
import android.view.SurfaceHolder;

public class Ball
{

    RectF rectF;
    int left, top;
    final int size = 50;
    int [] velocity = {5, 3};

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

    public  void checkSides(SurfaceHolder holder)
    {
        if(this.left + this.size + this.velocity[0] > holder.getSurfaceFrame().right)
        {
            this.setVelocity(-this.velocity[0], this.velocity[1]);
        }
        if(this.left - this.velocity[0] == holder.getSurfaceFrame().left)
        {
            this.setVelocity(-this.velocity[0], this.velocity[1]);
            this.left += 2*velocity[0];
        }

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
