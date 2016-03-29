package com.apps.primael.dd_tan;

import android.app.Activity;
import android.os.Bundle;

public class Display extends Activity
{
    CustomView customView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        customView = new CustomView(this);

        setContentView(customView);
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
