package com.example.karan.myapplication.activities;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.karan.myapplication.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName,etPass;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = (EditText) findViewById(R.id.editTextName);
        etPass = (EditText) findViewById(R.id.editTextPass);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnSubmit: {

                if ((etPass.getText().toString().trim().equals("1")) && (etName.getText().toString().trim().equals("1"))) {
                    v.startAnimation(AnimationUtils.loadAnimation(this, R.anim.click));
                    Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, RecyclerActivity.class));
                    finish();
                } else {
                    v.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.shake));
                    Toast.makeText(MainActivity.this, "Enter valid details", Toast.LENGTH_SHORT).show();
                    etName.setText("");
                    etPass.setText("");
                }
            }
            break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}


