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

    /* List global variables to be used by app */
    TextView calc_input, mem_buffer, calc_history;
    String default_input = "0";
    Double answer, operand, subtotal, memory;
    Boolean add, sub, mul, div, displayreset;

    /* Toast object providing greater config. */
    private Toast msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initialize global variables to default values */
        answer = 0.0;
        operand = 0.0;
        subtotal = 0.0;
        memory = 0.0;

        add = false;
        sub = false;
        mul = false;
        div = false;
        displayreset = false;

        /* Pass each button to the first listener method */
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
        showButtonPress(findViewById(R.id.b_Msave));
        showButtonPress(findViewById(R.id.b_Back));
        showButtonPress(findViewById(R.id.b_Sign));

        /* Assign views to global objects */
        calc_input = (TextView) findViewById(R.id.calc_input);
        mem_buffer = (TextView) findViewById(R.id.mem_buffer);
        calc_history = (TextView) findViewById(R.id.calc_history);
    }

    /***********************************************************
    * bool_Handler
    * Control method that handles the boolean variables which
    * identify what the operator was in the last operation.
    * 0 = Addition, 1 = Subtraction, 2 = Multiplication,
    *  3 = Division, -1 = no previous operations.
    ***********************************************************/
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
    /***********************************************************
     * buttonListener
     * Listener method with code to handle every single button
     * press of the application.
     ***********************************************************/
    private View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // Assign views to objects.
            Button b = (Button)v;
            String c = calc_input.getText().toString();
            String a = b.getText().toString();

            // switch to determine which button was pressed.
            // for cases 0-9, send input and button value to handler.
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
                // Case to handle the press of / button.
                case R.id.b_Div:
                    // Call check of previous calculation
                    if(!checkPreviousCalc()) { // no previous calculation
                        try {
                            historyHandler(c, '/'); // append to calc history
                            operand = Double.parseDouble(c); // get current input
                            // If block to ensure div by zero doesn't crash app.
                            if (subtotal == 0.0) {
                                answer = operand;
                                subtotal = answer;
                                calc_input.setText(default_input);
                                bool_Handler(3); // set previous calc to div
                            } else if (operand == 0.0) {
                                clearOperands(); // clear associated global variables
                                calc_input.setText("Undefined"); // user feedback
                                bool_Handler(-1); // clear previous operator/calc
                            } else {
                                subtotal = operand;
                                answer = answer / subtotal;
                                calc_input.setText(default_input);
                                bool_Handler(3); // set previous operator type to div
                            }
                        } catch (NumberFormatException ex) {
                            msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    } else { // previous calculation.
                        historyHandler(c, '/'); // append to calc history
                        displayAnswer(); // show running total
                        bool_Handler(3); // set previous operator type to div
                    }
                    break;
                case R.id.b_Sub:
                    // Call check of previous calculation
                    if(!checkPreviousCalc()) { // no previous calc
                        try {
                            operand = Double.parseDouble(c); // get input
                            historyHandler(c, '-'); // append history
                            answer = operand - answer; // update answer
                            calc_input.setText(default_input);
                            bool_Handler(1); // set previous operator type to sub
                        } catch (NumberFormatException ex) {
                            msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    } else { // previous calculation
                        historyHandler(c, '-'); // append history
                        displayAnswer(); // show running total
                        bool_Handler(1); // set previous operator type to sub
                    }
                    break;
                case R.id.b_Add:
                    // Call check of previous calculation
                    if(!checkPreviousCalc()) { // no previous calc
                        try {
                            operand = Double.parseDouble(c); // get input
                            historyHandler(c, '+'); // append history
                            answer = answer + operand; // update answer
                            calc_input.setText(default_input);
                            bool_Handler(0); // set previous operator type to add
                        } catch (NumberFormatException ex) {
                            msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    } else { // previous calculation
                        bool_Handler(0); // set previous operator type to add
                        historyHandler(c, '+'); // append history
                        displayAnswer(); // show running total
                    }
                    break;
                case R.id.b_Mul:
                    // call check of previous calculation
                    if(!checkPreviousCalc()) { // no previous calc
                        try {
                            operand = Double.parseDouble(c); // get input
                            historyHandler(c, '*'); // append history
                            // following if block ensures answer is updated accurately
                            subtotal = operand * answer;
                            if (subtotal == 0.0) { // input is first operand
                                answer = operand; // current answer is first operand
                                calc_input.setText(default_input);
                                bool_Handler(2); // set previous operator type to multi
                            } else { // input is second operand
                                answer = answer * operand; // update answer
                                calc_input.setText(default_input);
                                bool_Handler(2); // set previous operator type to multi
                            }
                        } catch (NumberFormatException ex) {
                            msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    } else {
                        bool_Handler(2); // set previous operator type to multi
                        historyHandler(c, '*'); // append history
                        displayAnswer(); // show running total
                    }
                    break;
                case R.id.b_Madd: // Add current value to current memory.
                    try {
                        double temp = Double.parseDouble(c); // get current value
                        memory = memory + temp; // add it to memory value
                        mem_buffer.setText(memory.toString()); // update memory buffer
                    } catch(NumberFormatException ex){
                        msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
                        msg.show();
                    }
                    break;
                case R.id.b_Msub: // Subtract current value from current memory.
                    try {
                        double temp = Double.parseDouble(c); // get current value
                        memory = memory - temp; // subtract it from memory
                        mem_buffer.setText(memory.toString()); // update memory buffer
                    } catch(NumberFormatException ex){
                        msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
                        msg.show();
                    }
                    break;
                case R.id.b_MC: // Clear saved memory
                    memory = 0.0;
                    mem_buffer.setText(""); // Clear buffer
                    break;
                case R.id.b_MR: // Insert current memory value as input
                    calc_input.setText(memory.toString());
                    break;
                case R.id.b_Msave: // Save current input value as memory value
                    try {
                        memory = Double.parseDouble(c); // get value
                        mem_buffer.setText(memory.toString()); // update memory buffer
                    } catch(NumberFormatException ex){
                        msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
                        msg.show();
                    }
                    break;
                case R.id.b_Dec: // Adds a decimal point to input
                    if(c.indexOf('.') >= 0){ // check to see if there is already a decimal
                        break;
                    } else if(c.length() >= 15){ // check to see if input size is at max.
                        break;
                    } else { // no previous decimal, input is not maxed.
                        calc_input.setText(c + a); // add decimal to input
                    }
                    break;
                case R.id.b_Equal:
                        if (add) { // Add was previous operation
                            try {
                                operand = Double.parseDouble(c); // get current input
                                answer = answer + operand; // update answer
                                updateHistory(); // update history
                                displayAnswer(); // show answer to user
                                clearOperands(); // clear associated variables
                                bool_Handler(-1); // clear previous operand.
                            } catch (NumberFormatException ex) {
                                msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
                                msg.show();
                            }
                        } else if (sub) { // Sub was previous operation
                            try {
                                operand = Double.parseDouble(c);
                                answer = answer - operand;
                                updateHistory();
                                displayAnswer();
                                clearOperands();
                                bool_Handler(-1);
                            } catch (NumberFormatException ex) {
                                msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
                                msg.show();
                            }
                        } else if (mul) { // Multi was previous operation
                            try {
                                operand = Double.parseDouble(c);
                                answer = answer * operand;
                                updateHistory();
                                displayAnswer();
                                clearOperands();
                                bool_Handler(-1);
                            } catch (NumberFormatException ex) {
                                msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
                                msg.show();
                            }
                        } else if (div) { // div was previous operation
                            try { // check for div by zero
                                double zero_check = Double.parseDouble(c);
                                if (zero_check == 0.0) { // trying to div by zero
                                    updateHistory();
                                    calc_input.setText("Undefined");
                                    clearOperands();
                                    bool_Handler(-1);
                                } else { // not trying to div by zero
                                    answer = answer / zero_check;
                                    updateHistory();
                                    displayAnswer();
                                    clearOperands();
                                    bool_Handler(-1);
                                }
                            } catch (NumberFormatException ex) {
                                msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
                                msg.show();
                            }
                        } else { // no previous calculations
                            calc_input.setText(default_input);
                            calc_history.setText("");
                            clearOperands();
                        }
                    break;
                case R.id.b_Back:
                    // ensure there isnt a single digit negative number in input
                    if(c.length() == 2 && c.indexOf('-') >= 0) { // all clear
                        calc_input.setText(default_input); // reset input to 0
                    } else if(c.length() == 1) { // only one digit to remove
                        calc_input.setText(default_input);
                        break;
                    } else { // there is a multiple digit input present
                        if(c.length() > 1){ // get substring without last char
                            c = c.substring(0, c.length()-1);
                            calc_input.setText(c); // update input
                        }
                    }
                    break;
                case R.id.b_C: // Clear button, clears all info except memory.
                    calc_input.setText(default_input);
                    calc_history.setText("");
                    clearOperands();
                    break;
                case R.id.b_CE: // Clears current input, not history or associated values.
                    calc_input.setText(default_input);
                    break;
                case R.id.b_Sign: // Changes +/- of current input.
                    if(c.indexOf('-') >= 0){ // already negative number
                        calc_input.setText(c.substring(1)); // trim - from input
                    } else if(c.equals(default_input)){ // checks for try of negative zero
                        break;
                    } else {
                        calc_input.setText('-'+c); // append - to front of input
                    }
                    break;
            }
        }
    };

    /***********************************************************
     * checkPreviousCalc
     * Method to check if there was already a calculation in
     * progress when user attempts to do running calculations.
     * If there is a running calculation, complete the
     * calculation and return true.
     ***********************************************************/
    public boolean checkPreviousCalc(){
        String c = calc_input.getText().toString(); // get current input
        double i;
        try{
            i = Double.parseDouble(c);
            // Check for previous calculations
            if(add){ // User was doing addition
                answer = answer + i; // update answer
                bool_Handler(-1); // reset previous operator
                return true;
            } else if(sub){ // User was doing subtraction
                answer = answer - i;
                bool_Handler(-1);
                return true;
            } else if(mul){ // User was doing multiplication.
                answer = answer * i;
                bool_Handler(-1);
                return true;
            } else if(div) { // User was doing division
                if (i == 0.0) { // check for div by zero
                    calc_input.setText("Undefined"); // inform user
                    clearOperands(); // clear associated values
                    bool_Handler(-1);
                } else {
                    answer = answer / i;
                    bool_Handler(-1);
                    return true;
                }
            } else { // No previous calculations
                calc_history.setText("");
                return false;
            }
        } catch(NumberFormatException ex){
            msg = Toast.makeText(getApplicationContext(), "Invalid input.", Toast.LENGTH_SHORT);
            msg.show();
        }
        return false;
    }

    /***********************************************************
     * clearOperands
     * Method to reset calculation variables to default values.
     ***********************************************************/
    public void clearOperands(){
        answer = 0.0;
        operand = 0.0;
        subtotal = 0.0;
    }

    /***********************************************************
     * updateHistory
     * Method to show calculation history when = is pressed.
     ***********************************************************/
    public void updateHistory() {
        //get current history and input
        String f = calc_history.getText().toString();
        String g = calc_input.getText().toString();

        String display = ""; // will be the final value of history
        if(g.indexOf('-') >= 0){ // check for negative value
            display = f+'('+g+')'; // append brackets to avoid confusion with subtraction
        } else { // not a negative
            display = f+g;
        }
        calc_history.setText(display); // show updated history
    }

    /***********************************************************
     * displayAnswer
     * Method to display the answer to user, trims off trailing zeros.
     ***********************************************************/
    public void displayAnswer() {
        String s = answer.toString();
        s = s.indexOf(".") < 0 ? s : s.replaceAll("0*$", "").replaceAll("\\.$", "");
        calc_input.setText(s); // display answer to user
        displayreset = true; // additional input won't be appended to answer
    }

    /***********************************************************
     * historyHandler
     * Method to append current operation status to history buffer
     ***********************************************************/
    public void historyHandler (String input, Character operator) {

        String initial = calc_history.getText().toString();
        String complete = "";
        if(initial.length() > 30) { // buffer overflow, trim old history, append new
            complete += initial.substring(input.length() + 3);
        } else { // no buffer overflow, append history
            complete += initial;
        }
        if(input.indexOf('-') >= 0){ // check for negative value
            complete += '('+input+')'; // append brackets to value to avoid confusion with negative values
            complete += operator; // append operator
        } else { // positive value
            complete += input;
            complete += operator;
        }
        calc_history.setText(complete); // show updated info to user.
    }

    /***********************************************************
     * inputHandler
     * Method to append button press value to current input,
     * accepts two string arguments, the running input and the
     * input to be appended.
     ***********************************************************/
    public void inputHandler (String current_input, String added_input){
        // check for previous input
        if(current_input.equals(default_input) || displayreset) { // no previous input OR input can be erased.
            calc_input.setText(added_input); // set input to the current value without appending
            displayreset = false; // Tells app that additional input should be appended.
        } else if((current_input+added_input).length() > 15){ // check for input overflow
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
        } else { // no overflow, append value.
                calc_input.setText(current_input + added_input);
                displayreset = false; // redundancy for safety
        }
    }

    /***********************************************************
     * showButtonPress
     * Method that sets up TouchListeners for each button to provide
     * user feedback on each button press. Accepts a view as an argument.
     * Credit: András at stackoverflow
     * http://stackoverflow.com/questions/7175873/click-effect-on-button-in-android
     ***********************************************************/
    public void showButtonPress(View button){
        button.setOnClickListener(buttonListener); // send each arg to clickListener method
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
