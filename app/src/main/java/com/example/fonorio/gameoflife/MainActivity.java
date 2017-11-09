package com.example.fonorio.gameoflife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public final String defaultScreen = "XXXXXXXX\n" +
            "00000000\n" +
            "XXXXXXXX\n" +
            "00000000\n" +
            "XXXXXXXX\n" +
            "00000000\n" +
            "XXXXXXXX\n" +
            "00000000\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mainDisplay = (TextView) findViewById(R.id.tvMainDisplay);
        mainDisplay.setText(defaultScreen);
    }
}
