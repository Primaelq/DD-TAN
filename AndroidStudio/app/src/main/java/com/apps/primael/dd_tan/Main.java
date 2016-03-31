package com.apps.primael.dd_tan;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Activity
{
    CustomView customView;
    public static Ball b = new Ball();
    public static List<Block> blocks;

    Random random = new Random();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        customView = new CustomView(this);

        setContentView(customView);

        initBlocks();
    }

    public void initBlocks()
    {
        blocks = new ArrayList<>();

        generateBlocks(blocks);

        if(blocks.isEmpty())
        {
            Toast.makeText(Main.this, "Blocks list empty !!!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(Main.this, "Blocks list contains: " + blocks.size() + " objects", Toast.LENGTH_SHORT).show();
        }
    }

    public void generateBlocks(List<Block> list)
    {
        int n = random.nextInt(7);
        for(int i = 0; i <= n; i++)
        {
            list.add(i, new Block(100, 100, 1, 0));
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
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        customView.resume();
    }
}
