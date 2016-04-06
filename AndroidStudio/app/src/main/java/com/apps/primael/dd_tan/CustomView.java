package com.apps.primael.dd_tan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import java.util.ArrayList;
import java.util.List;

public class CustomView extends SurfaceView implements Runnable
{
    Thread thread;
    // Game is paused at the start
    boolean paused = true;
    boolean ready = false;
    SurfaceHolder holder;
    Paint paintBricks, paintBalls;
    public static Ball ball;
    public static List<Block> blocks;
    public static int turns = 0;
    public static boolean turnStarted = false;

    public static int frames = 0;

    public CustomView(Context context)
    {
        super(context);
        holder = getHolder();

        setWillNotDraw(false);
        blocks = new ArrayList<>();

        paintBricks = new Paint();
        paintBricks.setStyle(Paint.Style.FILL);
        paintBricks.setColor(Color.rgb(255, 0, 127));
        paintBalls = new Paint();
        paintBalls.setStyle(Paint.Style.FILL);
        paintBalls.setColor(Color.rgb(255, 255, 127));

        ball = new Ball();
    }

    public void generateBlocks() {
        int n = 4;//random.nextInt(6);

        for (int i = 0; i < n; i++) {
            Log.d("generateblocks", "" + i);
            blocks.add(blocks.size(), new Block(25 + 175 * i, 25, turns, 1));
        }
        Log.d("blocks", "" + blocks.size());
    }

    @Override
    public void run()
    {
        while (ready) {
            if (holder.getSurface().isValid() && !paused)
            {
                update();
                draw();
            }
        }
    }

    public void update()
    {
        if (!turnStarted) {
            Log.d("starting", ""+turns);
            turns++;
            turnStarted = true;
            ball.startBall(holder);
            for (int i = 0; i < blocks.size(); i++) {
                Block b = blocks.get(i);
                b.goDown();
            }
            generateBlocks();
        }
        // Detect ball fall
        turnStarted = ball.checkSides();

    }

    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
    }

    public void draw()
    {
        Canvas canvas = holder.lockCanvas();
        canvas.drawARGB(255, 0, 0, 0);

        // Draw blocks
        for(int i = 0; i < blocks.size(); i++)
        {
            Block b = blocks.get(i);
            /*if(i != 0 && frames == 0 && b.currentRow == blocks.get(i - 1).currentRow)
            {
                b.marginSide += blocks.get(i - 1).marginSide + blocks.get(i - 1).size;
            }*/
            canvas.drawRect(b.rect, paintBricks); //new Rect(b.marginSide, b.marginTop, b.size + b.marginSide, b.size + b.marginTop), paintBricks);
            paintBricks.setColor(Color.BLACK);
            //Rect r = new Rect(b.marginSide + b.padding, b.marginTop + b.padding, b.size + b.marginSide - b.padding, b.size + b.marginTop - b.padding);
            canvas.drawRect(b.rectI, paintBricks);
            paintBricks.setColor(Color.rgb(255, 0, 127));
            paintBricks.setTextSize(50);
            canvas.drawText("" + b.count, b.rectI.centerX() - 50 / 4, b.rectI.centerY() + 50 / 4, paintBricks);
            //b.drawBlock(canvas, b, paintBricks, frames, i);
        }
        if (ball != null)
        {
            // Draw ball
            canvas.drawRoundRect(ball.rectF, 100, 100, paintBalls);
        }
        holder.unlockCanvasAndPost(canvas);
        frames++;
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
       // thread = null;
    }

    public void resume()
    {
        ready = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            // Player has touched the screen
            case MotionEvent.ACTION_DOWN:

                paused = false;
                break;

            // Player has removed finger from screen
            case MotionEvent.ACTION_UP:
                Log.d("motion", ""+motionEvent.getRawX());
                ball.startBall(holder);
                break;
        }
        return true;
    }
}
