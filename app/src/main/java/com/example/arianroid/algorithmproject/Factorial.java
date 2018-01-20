package com.example.arianroid.algorithmproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Factorial extends AppCompatActivity {

    private EditText etOne;
    private Button btnSubmit;
    private TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.factorial_mail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etOne = (EditText) findViewById(R.id.etOne);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        txtShow = (TextView) findViewById(R.id.txtShow);
        txtShow.setMovementMethod(new ScrollingMovementMethod());

        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtShow.setText("");
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkForm()) {
                    int number = Integer.parseInt(etOne.getText().toString());
                    int fact = factorial(Integer.parseInt(etOne.getText().toString()));
                    updateDisplay("Factorial of " + number + "  is :  " + fact);
                } else {
                    Toast.makeText(Factorial.this, "فیلد عددی نمیتواند خالی باشد.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private int factorial(int b) {

        if (b == 1)
            return 1;
        return b * factorial(b - 1);
    }


    private void updateDisplay(String message) {
        txtShow.append(message + "\n");
    }

    private boolean checkForm() {
        boolean isvalid = true;
        isvalid = !etOne.getText().toString().isEmpty();
        return isvalid;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
