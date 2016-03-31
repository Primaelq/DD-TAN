package com.apps.primael.dd_tan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CustomView extends SurfaceView implements Runnable
{
    Thread thread;
    boolean ready = false;
    SurfaceHolder holder;
    Paint paint;

    Ball ball = Main.b;

    int frames = 0;

    public CustomView(Context context)
    {
        super(context);
        holder = getHolder();

        setWillNotDraw(false);

        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(255, 0, 127));
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
            canvas.drawARGB(255, 0, 0, 0);

            for(int i = 0; i < Main.blocks.size(); i++)
            {
                Block b = Main.blocks.get(i);

                b.drawBlock(canvas, b, paint, frames, i);
            }

            canvas.drawRoundRect(ball.rectF, 100, 100, paint);

            if(ball.top + ball.velocity[1] < holder.getSurfaceFrame().height())
            {
                ball.move();
            }
            else
            {
                Main.turnStarted = false;

                for(int i = 0; i < Main.blocks.size(); i++)
                {
                    Block b = Main.blocks.get(i);

                    b.goDown();
                }

                ball.left = 100;
                ball.top = 100;

                Main.turns++;
            }

            holder.unlockCanvasAndPost(canvas);
            frames++;
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
