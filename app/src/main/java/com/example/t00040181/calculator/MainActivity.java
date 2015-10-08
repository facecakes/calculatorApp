package com.example.t00040181.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import android.graphics.PorterDuff;

public class MainActivity extends AppCompatActivity {

    TextView calc_input, mem_buffer, calc_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.b_0).setOnClickListener(buttonListener);
        findViewById(R.id.b_1).setOnClickListener(buttonListener);
        findViewById(R.id.b_2).setOnClickListener(buttonListener);
        findViewById(R.id.b_3).setOnClickListener(buttonListener);
        findViewById(R.id.b_4).setOnClickListener(buttonListener);
        findViewById(R.id.b_5).setOnClickListener(buttonListener);
        findViewById(R.id.b_6).setOnClickListener(buttonListener);
        findViewById(R.id.b_7).setOnClickListener(buttonListener);
        findViewById(R.id.b_8).setOnClickListener(buttonListener);
        findViewById(R.id.b_9).setOnClickListener(buttonListener);
        findViewById(R.id.b_C).setOnClickListener(buttonListener);
        findViewById(R.id.b_CE).setOnClickListener(buttonListener);
        findViewById(R.id.b_Add).setOnClickListener(buttonListener);
        findViewById(R.id.b_Sub).setOnClickListener(buttonListener);
        findViewById(R.id.b_Div).setOnClickListener(buttonListener);
        findViewById(R.id.b_Mul).setOnClickListener(buttonListener);
        findViewById(R.id.b_Dec).setOnClickListener(buttonListener);
        findViewById(R.id.b_Equal).setOnClickListener(buttonListener);
        findViewById(R.id.b_MC).setOnClickListener(buttonListener);
        findViewById(R.id.b_Madd).setOnClickListener(buttonListener);
        findViewById(R.id.b_Msub).setOnClickListener(buttonListener);
        findViewById(R.id.b_MR).setOnClickListener(buttonListener);
        findViewById(R.id.b_Back).setOnClickListener(buttonListener);
        findViewById(R.id.b_Sign).setOnClickListener(buttonListener);
    }
    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button)v;
            calc_input = (TextView) findViewById(R.id.calc_input);
            String current_input = calc_input.getText().toString();
            String added_input = "";
            switch(b.getId()){
                case R.id.b_0:
                    added_input+=b.getTag().toString();
                    //calc_input.setText(current_input+added_input);
                    break;
                case R.id.b_1:
                    break;
                case R.id.b_2:
                    break;
                case R.id.b_3:
                    break;
                case R.id.b_4:
                    break;
                case R.id.b_5:
                    break;
                case R.id.b_6:
                    break;
                case R.id.b_7:
                    break;
                case R.id.b_8:
                    break;
                case R.id.b_9:
                    break;
                case R.id.b_Div:
                    break;
                case R.id.b_Sub:
                    break;
                case R.id.b_Add:
                    break;
                case R.id.b_Mul:
                    break;
                case R.id.b_Madd:
                    break;
                case R.id.b_Msub:
                    break;
                case R.id.b_MC:
                    break;
                case R.id.b_MR:
                    break;
                case R.id.b_Dec:
                    break;
                case R.id.b_Equal:
                    break;
                case R.id.b_Back:
                    break;
                case R.id.b_C:
                    break;
                case R.id.b_CE:
                    break;
                case R.id.b_Sign:
                    break;
            }

        }
    };
    /*private class buttonListener implements View.OnClickListener {
        public void onClick(View v){

        }
    }*/



    public static void showButtonPress(View button){
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0f47521, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
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
