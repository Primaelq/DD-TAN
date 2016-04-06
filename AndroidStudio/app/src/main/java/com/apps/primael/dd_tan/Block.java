package com.apps.primael.dd_tan;

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
    int currentRow = 0;
    Rect rect;
    Rect rectI;


    public Block(int x, int y, int startCount, int row)
    {
        marginSide = x;
        marginTop = y;
        count = startCount;
        currentRow = row;
        rect = new Rect(marginSide, marginTop, marginSide+size, marginTop+size);
        rectI = new Rect(marginSide+padding, marginTop+padding, marginSide+size-padding, marginTop+size-padding);

    }

    public void goDown()
    {
        marginTop = 200 * currentRow;
        currentRow++;
        rect.set(marginSide, marginTop, marginSide+size, marginTop+size);
        rectI.set(marginSide+padding, marginTop+padding, marginSide+size-padding, marginTop+size-padding);
    }
}
