package com.example.karan.myapplication.activities;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.karan.myapplication.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et1;
    EditText et2;
    Button button;
    Vibrator vibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button: {
                if ((et1.getText().toString().equals("1") || et1.getText().toString().equals("1 ")) &&
                        (et2.getText().toString().equals("1") || et2.getText().toString().equals("1 "))) {
                    v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.click));
                    Toast toast = Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT);
                    toast.show();
                    Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake));
                    vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibe.vibrate(100);
                    Toast toast = Toast.makeText(MainActivity.this, "Enter valid details", Toast.LENGTH_SHORT);
                    et1.setText("");
                    et2.setText("");
                    toast.show();
                }
            }
            break;
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void showDialog() {
        AlertDialog alertbox = new AlertDialog.Builder(this)
                .setMessage("Exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        return;
                    }
                })
                .show();
    }
}


