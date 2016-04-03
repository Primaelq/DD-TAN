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
    boolean ready = false;
    SurfaceHolder holder;
    Paint paintBricks, paintBalls;

    Ball ball = Main.ball;

    public static int frames = 0;

    public CustomView(Context context)
    {
        super(context);
        holder = getHolder();

        setWillNotDraw(false);

        paintBricks = new Paint();
        paintBricks.setStyle(Paint.Style.FILL);
        paintBricks.setColor(Color.rgb(255, 0, 127));
        paintBalls = new Paint();
        paintBalls.setStyle(Paint.Style.FILL);
        paintBalls.setColor(Color.rgb(255, 255, 127));
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

            // Draw blocks
            for(int i = 0; i < Main.blocks.size(); i++)
            {
                Block b = Main.blocks.get(i);

                b.drawBlock(canvas, b, paintBricks, frames, i);
            }


            if (ball != null) {
                // Draw ball
                canvas.drawRoundRect(ball.rectF, 100, 100, paintBalls);

                // Detect ball fall
                ball.checkSides(holder);
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
