package com.chungjessica.lab03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView topLeft, topRight;
    Button bottomLeft, bottomRight;
    SharedPreferences sharedPreferences;

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
