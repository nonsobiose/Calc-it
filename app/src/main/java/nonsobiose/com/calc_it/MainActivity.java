package nonsobiose.com.calc_it;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.udojava.evalex.Expression;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    int counter = 0;
    ArrayList<String> inputs = new ArrayList<>(21);


    // Declares the Display View Objects
    TextView inputField;
    TextView outPutField;

    // Declares the cleaners button objects
    Button buttonClear;
    Button buttonDelete;

    // Declares the Operands objects
    Button buttonOne;
    Button buttonTwo;
    Button buttonThree;
    Button buttonFour;
    Button buttonFive;
    Button buttonSix;
    Button buttonSeven;
    Button buttonEight;
    Button buttonNine;
    Button buttonZero;
    Button buttonZeroZero;
    Button buttonPoint;

    // Declares the Arithmetic Operator Objects
    Button buttonDivide;
    Button buttonTimes;
    Button buttonPlusMinus;
    Button buttonEquals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializes all the Calculators objects

        // Cleaners (Clear | Delete)
        buttonClear = (Button) findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(this);

        buttonDelete = (Button) findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(this);

        // Displays (Input | Output)
        inputField = (TextView) findViewById(R.id.input_field);

        outPutField = (TextView) findViewById(R.id.output_field);

        //Input Buttons
        buttonOne = (Button) findViewById(R.id.button_one);
        buttonOne.setOnClickListener(this);

        buttonTwo = (Button) findViewById(R.id.button_two);
        buttonTwo.setOnClickListener(this);

        buttonThree = (Button) findViewById(R.id.button_three);
        buttonThree.setOnClickListener(this);

        buttonFour = (Button) findViewById(R.id.button_four);
        buttonFour.setOnClickListener(this);

        buttonFive = (Button) findViewById(R.id.button_five);
        buttonFive.setOnClickListener(this);

        buttonSix = (Button) findViewById(R.id.button_six);
        buttonSix.setOnClickListener(this);

        buttonSeven = (Button) findViewById(R.id.button_seven);
        buttonSeven.setOnClickListener(this);

        buttonEight = (Button) findViewById(R.id.button_eight);
        buttonEight.setOnClickListener(this);

        buttonNine = (Button) findViewById(R.id.button_nine);
        buttonNine.setOnClickListener(this);

        buttonZero = (Button) findViewById(R.id.button_zero);
        buttonZero.setOnClickListener(this);

        buttonZeroZero = (Button) findViewById(R.id.button_zero_zero);
        buttonZeroZero.setOnClickListener(this);

        buttonPoint = (Button) findViewById(R.id.button_point);
        buttonPoint.setOnClickListener(this);

        // Arithmetic Operators (Divide | Times | Plus/Minus | Equals)
        buttonDivide = (Button) findViewById(R.id.button_divide);
        buttonDivide.setOnClickListener(this);

        buttonTimes = (Button) findViewById(R.id.button_times);
        buttonTimes.setOnClickListener(this);

        buttonPlusMinus = (Button) findViewById(R.id.button_plus_minus);
        buttonPlusMinus.setOnClickListener(this);

        buttonEquals = (Button) findViewById(R.id.button_equals);
        buttonEquals.setOnClickListener(this);

        buttonPlusMinus.setOnLongClickListener(this);
    }

    // Checks for the clicked view and passes the corresponding value to the inputOperation() Method
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button_one:
                inputOperation("1");
                break;
            case R.id.button_two:
                inputOperation("2");
                break;
            case R.id.button_three:
                inputOperation("3");
                break;
            case R.id.button_four:
                inputOperation("4");
                break;
            case R.id.button_five:
                inputOperation("5");
                break;
            case R.id.button_six:
                inputOperation("6");
                break;
            case R.id.button_seven:
                inputOperation("7");
                break;
            case R.id.button_eight:
                inputOperation("8");
                break;
            case R.id.button_nine:
                inputOperation("9");
                break;
            case R.id.button_zero:
                inputOperation("0");
                break;
            case R.id.button_zero_zero:
                inputOperation("00");
                break;
            case R.id.button_point:
                operatorOperation(".");
                break;
            case R.id.button_equals:
                equals();
                break;
            case R.id.button_clear:
                clear();
                break;
            case R.id.button_delete:
                delete();
                break;
            case R.id.button_divide:
                operatorOperation("/");
                break;
            case R.id.button_times:
                operatorOperation("*");
                break;
            case R.id.button_plus_minus:
                operatorOperation("+");
                break;
        }
    }

    /**
     * Used to perform a certain number of operations when an operand(Number) is inputted
     * FIRST, it checks to see if the number of inputted values is less than 21 (21 is the maximum number of input allowed)
     * SECOND, it appends the input to a the inputs textview for display
     * THIRD, a counter used to keep count of the number of inputted values is incremented
     * FINALLY, the input is added to an arraylist of inputs ( this is later converted to a stringbuilder and passed as an expression for arithmetic operation)
     * @param input
     */
    private void inputOperation(String input) {
        if (inputs.size() < 21) {
            inputField.append(input);
            counter++;
            inputs.add(input);
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.max_input_length), Toast.LENGTH_SHORT).show();
            Vibrator v = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(100);
        }
    }

    /**
     * Used to perform a certain number of operations when an operand(Number) is inputted
     * FIRST, it checks to see if the number of inputted values is less than 21 (21 is the maximum number of input allowed)
     * SECOND, checks to see if arraylist of input is empty(cant have an operator if theres no value(number) in the stored inputs)
     * THIRD, it appends the input to a the inputs textview for display
     * FOURTH, a counter used to keep count of the number of inputted values is incremented
     * FIFTH, the input is added to an arraylist of inputs ( this is later converted to a stringbuilder and passed as an expression for arithmetic operation)
     * IF the current operator is equal to the previous operator, the current one deleted(not added, cant have identical operators together)
     * @param input
     */
    private void operatorOperation(String input) {
        if (inputs.size() < 21) {
            if (!inputs.isEmpty()) {
                inputField.append(input);
                counter++;
                inputs.add(input);
                String currentOperatorInput = inputs.get(counter - 1);
                String previousOperatorInput = inputs.get(counter - 2);
                if (currentOperatorInput.equals(previousOperatorInput)) {
                    delete();
                }
            }
        }

    }

    //Clears the entire output display screen
    private void clear() {
        inputField.setText("");
        counter = 0;
        inputs.clear();
    }

    // Deletes the most recently inputted value
    private void delete() {
        if (inputs.size() != 0) {
            inputs.remove(counter - 1);
            counter--;
            inputField.setText("");
            for (String i : inputs) {
                inputField.append(i);
            }
        }
    }

    // Performs the arithmetic operation looping through the items of the inputs arraylist and appending them using a stringbuilder.
    // Then converts the stringbuilder to a string passes it as an arguement to the Expression Constructor, for arithmetic operation
    private void equals() {

        StringBuilder result = null;
        if (!inputs.isEmpty()) {
            result = new StringBuilder();
            for (String i : inputs) {
                result.append(i);
            }
            //
            Expression exp = new Expression(result.toString());
            try {
                BigDecimal output = exp.eval();
                if (output.toString().length() <= 8) {
                    outPutField.setTextSize(80);
                    outPutField.setText(String.valueOf(output));
                } else {
                    outPutField.setTextSize(40);
                    outPutField.setText(String.valueOf(output));
                }
            } catch (Expression.ExpressionException e) {
                Toast.makeText(getApplicationContext(), getString(R.string.math_error), Toast.LENGTH_SHORT).show();
            }

        }
    }

    //Brings out the subtraction symbol
    @Override
    public boolean onLongClick(View view) {
        if (inputs.size() < 21) {
            operatorOperation("-");
            return true;
        }
        return false;
    }
}

