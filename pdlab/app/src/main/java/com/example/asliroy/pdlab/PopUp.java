package com.example.asliroy.pdlab;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class PopUp extends Activity{

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        TextView textView=(TextView)findViewById(R.id.t2);
         textView.setText(getIntent().getStringExtra("mnls"));

         getWindow().setLayout((int)(width*.8),(int)(height*.6));
    }


}
