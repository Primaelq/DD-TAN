package com.apps.primael.dd_tan;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Activity implements Runnable
{
    CustomView customView;
    public static Ball b = new Ball();
    public static List<Block> blocks;

    public static int turns = 0;

    public static boolean turnStarted = false;

    Random random = new Random();

    Thread thread;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        customView = new CustomView(this);

        turnStarted = true;
        turns = 1;

        setContentView(customView);

        initBlocks();
        thread = new Thread(this);
        thread.start();
    }

    public void initBlocks()
    {
        blocks = new ArrayList<>();

        generateBlocks();
    }

    public void generateBlocks()
    {
        int n = 4;//random.nextInt(6);

        for(int i = 0; i < n; i++)
        {
            blocks.add(blocks.size(), new Block(100, 100, turns, 1));
        }
    }

    public void run()
    {
        while(true)
        {
            if (!turnStarted)
            {
                turns++;

                for(int i = 0; i < blocks.size(); i++)
                {
                    Block b = blocks.get(i);

                    b.goDown();
                }

                generateBlocks();
                turnStarted = true;
            }
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        customView.pause();

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

    @Override
    protected void onResume()
    {
        super.onResume();
        customView.resume();

        thread = new Thread(this);
        thread.start();
    }
}
