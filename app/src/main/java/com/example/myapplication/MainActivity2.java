package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_PB = "pb";
    final String ATTRIBUTE_NAME_LL = "ll";
    private static final int ID_LL_LOAD = R.id.llLoad;
    private static final int ID_PB_LOAD = R.id.pbLoad;
    ListView lvSimple;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button = findViewById(R.id.button4);

        // Set a click listener for the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start MainActivity3
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });
        int load[] = { 41, 48, 22, 35, 30, 67, 51, 88 };

        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(load.length);
        Map<String, Object> m;
        for (int i = 0; i < load.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, "Day " + (i + 1) + ". Load: " + load[i] + "%");
            m.put(ATTRIBUTE_NAME_PB, load[i]);
            m.put(ATTRIBUTE_NAME_LL, load[i]);
            data.add(m);
        }

        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_PB, ATTRIBUTE_NAME_LL };
        int[] to = { R.id.tvLoad, R.id.pbLoad, R.id.llLoad };

        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.item2, from, to);
        sAdapter.setViewBinder(new MyViewBinder());

        lvSimple = findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);
    }

    class MyViewBinder implements SimpleAdapter.ViewBinder {

        @Override
        public boolean setViewValue(View view, Object data, String textRepresentation) {
            int i = 0;

            if (view.getId() == R.id.llLoad) {
                i = ((Integer) data).intValue();
                if (i < 40) {
                    view.setBackgroundColor(getResources().getColor(R.color.Green));
                } else if (i < 70) {
                    view.setBackgroundColor(getResources().getColor(R.color.Orange));
                } else {
                    view.setBackgroundColor(getResources().getColor(R.color.Red));
                }
                return true;
            }

            if (view.getId() == R.id.pbLoad) {
                i = ((Integer) data).intValue();
                ((ProgressBar) view).setProgress(i);
                return true;
            }

            // Handle other cases here if needed

            return false;
        }
    }
}