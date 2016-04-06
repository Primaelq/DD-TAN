package com.apps.primael.dd_tan;

import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;

public class Ball
{

    RectF rectF;
    int viewTop, viewLeft, viewRight;
    int left, top;
    final int size = 50;
    int [] velocity = {15, -9};

    public Ball ()
    {
        left = 200;
        top = 1500;

        rectF = new RectF ();
        rectF.set(left, top, left+size, top+size);
    }

    public void startBall(SurfaceHolder holder)
    {
        viewTop = holder.getSurfaceFrame().height();
        viewLeft = holder.getSurfaceFrame().left;
        viewRight = holder.getSurfaceFrame().right;
        Log.d("custom", ""+ holder);
        setVelocity(holder.getSurfaceFrame().width() / 50, -viewTop / 100);
        top = viewTop - 10*size;
        left = holder.getSurfaceFrame().centerY();
        rectF.set(left, top, left + size, top + size);
        Log.d("startBall", "" + viewTop);
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

    public boolean checkSides()
    {
        if(top + size + velocity[1] >= viewTop)
        {
            return false;
        }

        if(left + size + velocity[0] > viewRight)
        {
            setVelocity(-velocity[0], velocity[1]);
        }
        else
        {
            if(left - velocity[0] <= viewLeft)
            {
                setVelocity(-velocity[0], velocity[1]);
                left += 2*velocity[0];
            }
        }
        if (top <= 0)
        {
            setVelocity(velocity[0], -velocity[1]);
            top = 20;
        }

        move();
        return true;
    }
}
