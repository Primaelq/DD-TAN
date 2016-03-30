package com.apps.primael.dd_tan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CustomView extends SurfaceView implements Runnable
{
    Thread thread;
    boolean ready = false;
    SurfaceHolder holder;
    Paint paint;

    int x = 0;
    int y = 0;

    int frames = 0;

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

            for(int i = 0; i < Main.blocks.size(); i++)
            {
                Block b = Main.blocks.get(i);

                if(i != 0 && frames == 0)
                {
                    b.marginSide += Main.blocks.get(i - 1).marginSide + Main.blocks.get(i - 1).size;
                    Log.d("Debug", "" + b.marginSide);
                }

                Rect r = new Rect(b.marginSide, b.marginTop, b.size + b.marginSide, b.size + b.marginTop);
                canvas.drawRect(r, paint);
            }

            canvas.drawCircle( x, y, 50, paint);

            if(y < holder.getSurfaceFrame().height())
            {
                y += 10;
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
