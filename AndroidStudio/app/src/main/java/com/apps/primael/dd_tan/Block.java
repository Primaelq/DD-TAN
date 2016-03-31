package com.apps.primael.dd_tan;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Block
{
    int count = 0;
    int positionX = 0;
    int positionY = 0;
    int size = 150;
    int marginSide = 25;
    int marginTop = 25;
    int padding = 15;
    int fontSize = 50;

    public Block(int x, int y, int startCount)
    {
        positionX = x;
        positionY = y;
        count = startCount;
    }

    public void drawBlock(Canvas canvas, Block b, Paint paint, int frames, int i)
    {
        if(i != 0 && frames == 0)
        {
            b.marginSide += Main.blocks.get(i - 1).marginSide + Main.blocks.get(i - 1).size;
        }

        Rect r = new Rect(b.marginSide, b.marginTop, b.size + b.marginSide, b.size + b.marginTop);
        canvas.drawRect(r, paint);

        paint.setColor(Color.BLACK);
        r = new Rect(b.marginSide + b.padding, b.marginTop + b.padding, b.size + b.marginSide - b.padding, b.size + b.marginTop - b.padding);
        canvas.drawRect(r, paint);

        paint.setColor(Color.rgb(255, 0, 127));
        paint.setTextSize(fontSize);
        canvas.drawText("" + b.count, r.centerX() - fontSize / 4, r.centerY() + fontSize / 4, paint);
    }

    public void goDown()
    {
        positionY -= 100;
    }

    public void collided()
    {
        count --;
    }
}
