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

public class Greatest extends AppCompatActivity {

    private EditText etOne, etTwo;
    private Button btnSubmit;
    private TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.greatest_mail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etOne = (EditText) findViewById(R.id.etOne);
        etTwo = (EditText) findViewById(R.id.etTwo);
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

                    updateDisplay(" " + greatest(Integer.parseInt(etOne.getText().toString())
                            , Integer.parseInt(etTwo.getText().toString())));

                } else {
                    Toast.makeText(Greatest.this, "فیلد عددی نمیتواند خالی باشد.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private int greatest(int a, int b) {
        int temp = 0;
        if (a < b) {
            a = b;
            b = temp;
        }
        if (b == 0)
            return a;

        return greatest(b, (a % b));
    }

    private void updateDisplay(String message) {
        txtShow.append(message + "\n");
    }

    private boolean checkForm() {
        boolean isvalid = true;
        isvalid = !etOne.getText().toString().isEmpty();
        isvalid = !etTwo.getText().toString().isEmpty();
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
