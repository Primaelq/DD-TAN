package com.apps.primael.dd_tan;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main extends Activity
{
    CustomView customView;

    public static List<Block> blocks;

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

        blocks.add(0, new Block(100, 100, 1));

        if(blocks.isEmpty())
        {
            Toast.makeText(Main.this, "Blocks list empty !!!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(Main.this, "Blocks list contains: " + blocks.size() + " objects", Toast.LENGTH_SHORT).show();
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
