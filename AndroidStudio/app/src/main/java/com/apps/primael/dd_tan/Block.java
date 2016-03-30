package com.apps.primael.dd_tan;

public class Block
{
    int count = 0;
    int positionX = 0;
    int positionY = 0;

    public Block(int x, int y, int startCount)
    {
        positionX = x;
        positionY = y;
        count = startCount;
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
