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
    Double answer, operand, subtotal;
    Boolean add, sub, mul, div;

    private Toast msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answer = 0.0;
        operand = 0.0;
        subtotal = 0.0;

        add = false;
        sub = false;
        mul = false;
        div = false;

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
        mem_buffer = (TextView) findViewById(R.id.mem_buffer);
        calc_history = (TextView) findViewById(R.id.calc_history);
    }
    public void bool_Handler (int i){
        switch (i){
            case 0:
                add = true;
                sub = false;
                mul = false;
                div = false;
                break;
            case 1:
                sub = true;
                add = false;
                mul = false;
                div = false;
                break;
            case 2:
                mul = true;
                add = false;
                sub = false;
                div = false;
                break;
            case 3:
                div = true;
                add = false;
                sub = false;
                mul = false;
                break;
            case -1:
                div = false;
                add = false;
                sub = false;
                mul = false;
                break;
        }
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
                    inputHandler(c, a);
                    break;
                case R.id.b_1:
                    inputHandler(c, a);
                    break;
                case R.id.b_2:
                    inputHandler(c, a);
                    break;
                case R.id.b_3:
                    inputHandler(c, a);
                    break;
                case R.id.b_4:
                    inputHandler(c, a);
                    break;
                case R.id.b_5:
                    inputHandler(c, a);
                    break;
                case R.id.b_6:
                    inputHandler(c, a);
                    break;
                case R.id.b_7:
                    inputHandler(c, a);
                    break;
                case R.id.b_8:
                    inputHandler(c, a);
                    break;
                case R.id.b_9:
                    inputHandler(c, a);
                    break;
                case R.id.b_Div:
                    try {
                        historyHandler(c, '/');
                        operand = Double.parseDouble(c);
                        if(subtotal == 0.0) {
                            answer = operand;
                            subtotal = answer;
                            calc_input.setText(default_input);
                            bool_Handler(3);
                        } else if(operand == 0.0){
                            clearOperands();
                            calc_input.setText("Undefined");
                            bool_Handler(-1);
                        } else {
                            subtotal = operand;
                            answer = answer / subtotal;
                            calc_input.setText(default_input);
                            bool_Handler(3);
                        }
                    } catch(NumberFormatException ex){
                        msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
                        msg.show();
                    }
                    break;
                case R.id.b_Sub:
                    try {
                        operand = Double.parseDouble(c);
                        historyHandler(c, '-');
                        answer = operand - answer;
                        calc_input.setText(default_input);
                        bool_Handler(1);
                    } catch(NumberFormatException ex){
                        msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
                        msg.show();
                    }
                    break;
                case R.id.b_Add:
                    try {
                        operand = Double.parseDouble(c);
                        historyHandler(c, '+');
                        answer = answer + operand;
                        calc_input.setText(default_input);
                        bool_Handler(0);
                    } catch(NumberFormatException ex){
                        msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
                        msg.show();
                    }
                    break;
                case R.id.b_Mul:
                    try {
                        operand = Double.parseDouble(c);
                        historyHandler(c, '*');
                        subtotal = operand * answer;
                        if (subtotal == 0.0){
                            answer = operand;
                            calc_input.setText(default_input);
                            bool_Handler(2);
                        } else {
                            answer = answer * operand;
                            calc_input.setText(default_input);
                            bool_Handler(2);
                        }
                    } catch(NumberFormatException ex){
                        msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
                        msg.show();
                    }
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
                    if(add){
                        try{
                            operand = Double.parseDouble(c);
                            answer = answer + operand;
                            updateHistory();
                            displayAnswer();
                            clearOperands();
                            bool_Handler(-1);
                        } catch(NumberFormatException ex){
                            msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    } else if(sub) {
                        try {
                            operand = Double.parseDouble(c);
                            answer = answer - operand;
                            updateHistory();
                            displayAnswer();
                            clearOperands();
                            bool_Handler(-1);
                        } catch(NumberFormatException ex){
                            msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    } else if(mul) {
                        try {
                            operand = Double.parseDouble(c);
                            answer = answer * operand;
                            updateHistory();
                            displayAnswer();
                            clearOperands();
                            bool_Handler(-1);
                        } catch(NumberFormatException ex){
                            msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    } else if(div) {
                        try {
                            double zero_check = Double.parseDouble(c);
                            if(zero_check == 0.0){
                                updateHistory();
                                calc_input.setText("Undefined");
                                clearOperands();
                                bool_Handler(-1);
                            } else {
                                answer = answer / zero_check;
                                updateHistory();
                                displayAnswer();
                                clearOperands();
                                bool_Handler(-1);
                            }
                        } catch(NumberFormatException ex){
                            msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    }
                    break;
                case R.id.b_Back:
                    if(c.length() == 2 && c.indexOf('-') >= 0) {
                        calc_input.setText(default_input);
                    } else if(c.length() == 1) {
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
                    calc_input.setText(default_input);
                    calc_history.setText("");
                    clearOperands();
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

    public void clearOperands(){
        answer = 0.0;
        operand = 0.0;
        subtotal = 0.0;
    }

    public void updateHistory() {
        String f = calc_history.getText().toString();
        String g = calc_input.getText().toString();
        String display = "";
        if(g.indexOf('-') >= 0){
            display = f+'('+g+')';
        } else {
            display = f+g;
        }
        calc_history.setText(display);
    }
    public void displayAnswer() {
        String s = answer.toString();
        s = s.indexOf(".") < 0 ? s : s.replaceAll("0*$", "").replaceAll("\\.$", "");
        calc_input.setText(s);
    }

    public void historyHandler (String input, Character operator) {
        if(!add && !sub && !mul && !div){
            calc_history.setText("");
        }

        String initial = calc_history.getText().toString();
        String complete = "";
        if(initial.length() > 30) {
            complete += initial.substring(input.length() + 3);
        } else {
            complete += initial;
        }
        if(input.indexOf('-') >= 0){
            complete += '('+input+')';
            complete += operator;
        } else {
            complete += input;
            complete += operator;
        }
        calc_history.setText(complete);
    }

    public void inputHandler (String current_input, String added_input){
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
