package com.example.t00040181.calculator;

import android.content.Intent;
import android.os.Handler;
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
    String default_input = "0";

    private Toast msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showButtonPress(findViewById(R.id.b_0));
        showButtonPress(findViewById(R.id.b_1));
        showButtonPress(findViewById(R.id.b_2));
        showButtonPress(findViewById(R.id.b_3));
        showButtonPress(findViewById(R.id.b_4));
        showButtonPress(findViewById(R.id.b_5));
        showButtonPress(findViewById(R.id.b_6));
        showButtonPress(findViewById(R.id.b_7));
        showButtonPress(findViewById(R.id.b_8));
        showButtonPress(findViewById(R.id.b_9));
        showButtonPress(findViewById(R.id.b_C));
        showButtonPress(findViewById(R.id.b_CE));
        showButtonPress(findViewById(R.id.b_Add));
        showButtonPress(findViewById(R.id.b_Sub));
        showButtonPress(findViewById(R.id.b_Div));
        showButtonPress(findViewById(R.id.b_Mul));
        showButtonPress(findViewById(R.id.b_Dec));
        showButtonPress(findViewById(R.id.b_Equal));
        showButtonPress(findViewById(R.id.b_MC));
        showButtonPress(findViewById(R.id.b_Madd));
        showButtonPress(findViewById(R.id.b_Msub));
        showButtonPress(findViewById(R.id.b_MR));
        showButtonPress(findViewById(R.id.b_Back));
        showButtonPress(findViewById(R.id.b_Sign));

        calc_input = (TextView) findViewById(R.id.calc_input);
    }
    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button)v;
            showButtonPress(b);
            String c = calc_input.getText().toString();
            String a = b.getText().toString();

            switch(b.getId()){
                case R.id.b_0:
                    inputHandler(b, c, a);
                    break;
                case R.id.b_1:
                    inputHandler(b, c, a);
                    break;
                case R.id.b_2:
                    inputHandler(b, c, a);
                    break;
                case R.id.b_3:
                    inputHandler(b, c, a);
                    break;
                case R.id.b_4:
                    inputHandler(b, c, a);
                    break;
                case R.id.b_5:
                    inputHandler(b, c, a);
                    break;
                case R.id.b_6:
                    inputHandler(b, c, a);
                    break;
                case R.id.b_7:
                    inputHandler(b, c, a);
                    break;
                case R.id.b_8:
                    inputHandler(b, c, a);
                    break;
                case R.id.b_9:
                    inputHandler(b, c, a);
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
                    if(c.indexOf('.') >= 0){
                        break;
                    } else if(c.length() >= 15){
                        break;
                    } else {
                        calc_input.setText(c + a);
                    }
                    break;
                case R.id.b_Equal:
                    break;
                case R.id.b_Back:
                    if(c.length() == 1) {
                        calc_input.setText(default_input);
                        break;
                    } else {
                        if(c.length() > 1){
                            c = c.substring(0, c.length()-1);
                            calc_input.setText(c);
                        }
                    }
                    break;
                case R.id.b_C:
                    break;
                case R.id.b_CE:
                    calc_input.setText(default_input);
                    break;
                case R.id.b_Sign:
                    if(c.indexOf('-') >= 0){
                        calc_input.setText(c.substring(1));
                    } else if(c.equals(default_input)){
                        break;
                    } else {
                        calc_input.setText('-'+c);
                    }
                    break;
            }
        }
    };

    public void inputHandler (Button b, String current_input, String added_input){
        if(current_input.equals(default_input)) {
            calc_input.setText(added_input);
        } else {
            if((current_input+added_input).length() > 15){
                /* The following if is a "toast hack" to limit toast spam for input overflow.
                    credit: S.A. Jay, stackoverflow
                    http://stackoverflow.com/questions/2755277/android-hide-all-showed-toast-messages
                 */
                if(null == msg) {
                    msg = Toast.makeText(getApplicationContext(), "Input limit exceeded.", Toast.LENGTH_SHORT);
                    msg.show();

                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            msg = null;
                        }
                    }, 2000);
                }
            } else {
                calc_input.setText(current_input + added_input);
            }
        }
    }

    /* Method to display button presses.
        Credit: András at stackoverflow
        http://stackoverflow.com/questions/7175873/click-effect-on-button-in-android
     */
    public void showButtonPress(View button){
        button.setOnClickListener(buttonListener);
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
