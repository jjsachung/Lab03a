package com.chungjessica.lab03a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView topLeft, topRight;
    Button bottomLeft, bottomRight;
    SharedPreferences sharedPreferences;
    SeekBar fontSize;
    ConstraintLayout layout;

    SharedPreferences sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topLeft = findViewById(R.id.topleft_textview);
        topRight = findViewById(R.id.topright_textview);
        bottomLeft = findViewById(R.id.bottomleft_button);
        bottomRight = findViewById(R.id.bottomright_button);
        topLeft.setText("0");
        topRight.setText("0");
        bottomLeft.setText("0");
        bottomRight.setText("0");
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        layout = findViewById(R.id.activity_main_layout);
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear().apply();
                topLeft.setText("0");
                topRight.setText("0");
                bottomLeft.setText("0");
                bottomRight.setText("0");
                return false;
            }
        });
        fontSize = findViewById(R.id.sizeseekbar);
        fontSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                topLeft.setTextSize(i);
                topRight.setTextSize(i);
                bottomLeft.setTextSize(i);
                bottomRight.setTextSize(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Snackbar.make(layout, getString(R.string.snackbar_label, seekBar.getProgress()),
                        Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    protected void onResume(){

        super.onResume();
        topLeft.setText(sh.getString("topleft", "0"));
        topRight.setText(sh.getString("topright", "0"));
        bottomLeft.setText(sh.getString("bottomleft", "0"));
        bottomRight.setText(sh.getString("bottomright", "0"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("topleft", topLeft.getText().toString());
        myEdit.putString("topright", topRight.getText().toString());
        myEdit.putString("bottomleft", bottomLeft.getText().toString());
        myEdit.putString("bottomright", bottomRight.getText().toString());
        myEdit.apply();
        //test
    }

    public void increment(View view) {
        TextView v = (TextView) view;
        int count = Integer.parseInt(v.getText().toString()) + 1;
        v.setText(getString(R.string.number, count));

    }

}
