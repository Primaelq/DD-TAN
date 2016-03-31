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

    int currentRow = 0;

    Rect render;

    public Block(int x, int y, int startCount, int row)
    {
        positionX = x;
        positionY = y;
        count = startCount;
        currentRow = row;
    }

    public void drawBlock(Canvas canvas, Block b, Paint paint, int frames, int i)
    {
        if(i != 0 && frames == 0)
        {
            b.marginSide += Main.blocks.get(i - 1).marginSide + Main.blocks.get(i - 1).size;
        }

        render = new Rect(b.marginSide, b.marginTop, b.size + b.marginSide, b.size + b.marginTop);
        canvas.drawRect(render, paint);

        paint.setColor(Color.BLACK);
        render = new Rect(b.marginSide + b.padding, b.marginTop + b.padding, b.size + b.marginSide - b.padding, b.size + b.marginTop - b.padding);
        canvas.drawRect(render, paint);

        paint.setColor(Color.rgb(255, 0, 127));
        paint.setTextSize(fontSize);
        canvas.drawText("" + b.count, render.centerX() - fontSize / 4, render.centerY() + fontSize / 4, paint);
    }

    public void goDown()
    {
        marginTop = 200 * Main.turns;
    }
}
