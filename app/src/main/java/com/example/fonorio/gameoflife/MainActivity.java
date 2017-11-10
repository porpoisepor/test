package com.example.fonorio.gameoflife;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String defaultScreen = "XXXXXXXX\n" +
            "00000000\n" +
            "XXXXXXXX\n" +
            "00000000\n" +
            "XXXXXXXX\n" +
            "00000000\n" +
            "XXXXXXXX\n" +
            "00000000\n";
    private final int[] firstGen = {
            0,0,0,0,0,0,0,0,0,0,
            0,1,1,1,0,0,0,0,0,0,
            0,0,0,1,0,0,0,0,0,0,
            0,0,1,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
    };
    CellularAutomata cellularAutomaton = new CellularAutomata(10,10);
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            cellularAutomaton.updateCurrentGen();
            TextView mainDisplay = (TextView) findViewById(R.id.tvMainDisplay);
            mainDisplay.setText(cellularAutomaton.getStringRepresentation());
            handler.postDelayed(this, 100);
        }
    };

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView mainDisplay = (TextView) findViewById(R.id.tvMainDisplay);
        mainDisplay.setText(defaultScreen);

        cellularAutomaton.setCells(firstGen);

        handler.postDelayed(runnable, 100);

    }
}
