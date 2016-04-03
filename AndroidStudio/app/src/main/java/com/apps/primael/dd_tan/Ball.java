package com.apps.primael.dd_tan;

import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;

public class Ball
{

    RectF rectF;
    int left, top;
    final int size = 50;
    int [] velocity = {5, -3};

    public Ball ()
    {
        left = 200;
        top = 10000;

        rectF = new RectF ();
        rectF.set(this.left, this.top, this.left+this.size, this.top+this.size);
    }

    public void startBall(int top)
    {
        setVelocity(5, -3);
        this.top = top - 200;
        Log.d("startBall", ""+this.top);
        left = 200;
        rectF.set(left, this.top, left+size, this.top+size);
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
        if (this.top <= 0)
        {
            setVelocity(velocity[0], -velocity[1]);
            this.top = 20;
        }
        if(this.top + this.velocity[1] < holder.getSurfaceFrame().height()-180)
        {
            this.move();
        }
        else
        {
            this.left = 100;
            this.top = Main.bottom - 200;
            //setVelocity(velocity[0], -velocity[1]);
            CustomView.frames = 0;

            Main.turnStarted = false;
        }
    }
}
