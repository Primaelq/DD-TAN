package com.apps.primael.dd_tan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CustomView extends SurfaceView implements Runnable
{
    Thread thread;
    boolean ready = true;
    SurfaceHolder holder;
    Paint paint;

    int x = 0;
    int y = 0;

    public CustomView(Context context)
    {
        super(context);
        holder = getHolder();

        setWillNotDraw(false);

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        x = holder.getSurfaceFrame().width() / 2;
    }

    public void run()
    {
        while(ready)
        {
            if (!holder.getSurface().isValid())
            {
                continue;
            }

            Canvas canvas = holder.lockCanvas();
            canvas.drawARGB(255, 255, 128, 0);

            canvas.drawCircle( x, y, 50, paint);

            if(y < holder.getSurfaceFrame().height())
            {
                y++;
            }

            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause()
    {
        ready = false;

        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        thread = null;
    }

    public void resume()
    {
        ready = true;
        thread = new Thread(this);
        thread.start();
    }
}
