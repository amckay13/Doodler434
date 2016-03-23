package com.example.akm.doodler434;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import java.io.*;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.provider.MediaStore;



public class Main extends AppCompatActivity implements AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener {
    DoodleView doodle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doodle_view);
        doodle = (DoodleView) findViewById(R.id.view);

        Spinner dropdown = (Spinner) findViewById(R.id.colorSpinner);
        String[] items = new String[]{"Red", "Yellow", "Green", "Blue", "Magenta"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);

        final SeekBar seekOpq = (SeekBar) findViewById(R.id.seekBar);
        seekOpq.setMax(100);
        seekOpq.setProgress(20);

        seekOpq.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        doodle.setSize(progress);
    }
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {}

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {}

    public void OnButtonClick(View view) {
        doodle.clearDoodle();
    }

   /* public void onSave(View view) {
        doodle.setDrawingCacheEnabled(true);
        String imgSaved = MediaStore.Images.Media.insertImage(
                getContentResolver(), doodle.getDrawingCache(),
                UUID.randomUUID().toString()+".png", "drawing");
        if(imgSaved!=null){
            Toast.makeText(getApplicationContext(),
                    "Drawing saved to Gallery!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "Oops! Image could not be saved.", Toast.LENGTH_SHORT).show();
        }
    }*/

    public void changeOpacity(View view) {
        doodle.changeOpacity();
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.RED);
        switch (position) {
            case 0:
                doodle.setColorR();
                break;
            case 1:
                doodle.setColorY();
                ((TextView) parent.getChildAt(0)).setTextColor(Color.YELLOW);
                break;
            case 2:
                doodle.setColorG();
                ((TextView) parent.getChildAt(0)).setTextColor(Color.GREEN);
                break;
            case 3:
                doodle.setColorB();
                ((TextView) parent.getChildAt(0)).setTextColor(Color.BLUE);
                break;
            case 4:
                doodle.setColorM();
                ((TextView) parent.getChildAt(0)).setTextColor(Color.MAGENTA);
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        //do nothing
    }
}
