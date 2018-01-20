package com.example.arianroid.algorithmproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int[] nums;
    int low, high;
    private EditText etOne, etTwo, etThree, etFour, etFive, etSix, etSeven;
    private Button btnSubmit;
    private TextView txtShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etOne = (EditText) findViewById(R.id.etOne);
        etTwo = (EditText) findViewById(R.id.etTwo);
        etThree = (EditText) findViewById(R.id.etThree);
        etFour = (EditText) findViewById(R.id.etFour);
        etFive = (EditText) findViewById(R.id.etFive);
        etSix = (EditText) findViewById(R.id.etSix);
        etSeven = (EditText) findViewById(R.id.etSeven);
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
                    initArray();
                    new MyAsyncTask().execute();
                } else {
                    Toast.makeText(MainActivity.this, "لطفا آرایه را کامل  کنید !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private class  MyAsyncTask extends AsyncTask<Void,int[],Void>{

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            quickSort(nums, low, high);
            return null;
        }

        @Override
        protected void onProgressUpdate(int[]... values) {
            super.onProgressUpdate(values);

            updateDisplayWithHandler(values[0]);

        }
        private  void quickSort(int[] arr, int low, int high) {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //updateDisplayWithHandler(arr);
            if (arr == null || arr.length == 0) {
                return;
            }
            if (low >= high) {
                return;
            }
            //pick the pivot
            int middle = low + (high - low) / 2;
            int pivot = arr[middle];

            //make left < pivot and right > pivot
            int i = low, j = high;
            while (i <= j) {
                while (arr[i] < pivot)
                    i++;
                while (arr[j] > pivot)
                    j--;
                if (i <= j) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    i++;
                    j--;
                }
            }
            if (low < j) {
                onProgressUpdate(arr);
                quickSort(arr, low, j);
            }
            if (high > i) {
                onProgressUpdate(arr);
                quickSort(arr, i, high);
            }

        }

    }

    private synchronized void updateDisplayWithHandler(final int[] arr) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtShow.append(Arrays.toString(arr) + "\n ");
            }
        });
    }


    private void initArray() {
        int item1 = Integer.parseInt(etOne.getText().toString());
        int item2 = Integer.parseInt(etTwo.getText().toString());
        int item3 = Integer.parseInt(etThree.getText().toString());
        int item4 = Integer.parseInt(etFour.getText().toString());
        int item5 = Integer.parseInt(etFive.getText().toString());
        int item6 = Integer.parseInt(etSix.getText().toString());
        int item7 = Integer.parseInt(etSeven.getText().toString());
        nums = new int[]{item1, item2, item3, item4, item5, item6, item7};

        updateDisplay(Arrays.toString(nums));
        low = 0;
        high = nums.length - 1;

    }

    private void updateDisplay(String message) {
        txtShow.append(message + "\n");
    }

    private boolean checkForm() {
        boolean isvalid = true;
        isvalid = !etOne.getText().toString().isEmpty();
        isvalid = !etTwo.getText().toString().isEmpty();
        isvalid = !etThree.getText().toString().isEmpty();
        isvalid = !etFour.getText().toString().isEmpty();
        isvalid = !etFive.getText().toString().isEmpty();
        return isvalid;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_factorial:
                startActivity(new Intent(this, Factorial.class));
                break;
            case R.id.greatest:
                startActivity(new Intent(this, Greatest.class));
                break;

        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
