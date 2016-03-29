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

            canvas.drawCircle(holder.getSurfaceFrame().width() / 2, holder.getSurfaceFrame().height() / 2, 50, paint);

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
