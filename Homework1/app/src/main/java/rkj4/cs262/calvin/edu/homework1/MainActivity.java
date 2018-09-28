package rkj4.cs262.calvin.edu.homework1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends Activity implements OnItemSelectedListener{
    // user-inputted number
    EditText num1, num2;
    // calculate button
    Button calculate;
    // result box
    TextView result;
    // operator dropdown
    Spinner operatorSelector;
    // operators
    String[] operators={"+","-","*","/"};
    // selected operator
    String selected;
    // create variables for calculations (used integers because the instructions said to)
    int value1, value2, answer;

    private OnClickListener myClickListener = new OnClickListener()
    {
        public void onClick(View v) {
            // convert user-inputted numbers to a string
            value1=Integer.parseInt(num1.getText().toString());
            value2=Integer.parseInt(num2.getText().toString());
            // if addition is selected
            if (selected.equals("+")){answer=value1+value2; result.setText(String.valueOf(answer));}
            // or if subtraction is selected
            else if (selected.equals("-")){answer=value1-value2; result.setText(String.valueOf(answer));}
            // or if multiplication is selected
            else if (selected.equals("*")){answer=value1*value2; result.setText(String.valueOf(answer));}
            // or if division is selected
            else if (selected.equals("/")){answer=value1/value2; result.setText(String.valueOf(answer));}
            // or if, somehow, nothing is selected
            else {result.setText("Please select an operator");}
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // display the answer
        result = (TextView)findViewById(R.id.resultBox);

        // make the calculate button actually do something
        calculate = (Button)findViewById(R.id.Button01);
        calculate.setText("Calculate");
        calculate.setOnClickListener(myClickListener);

        // get the user-inputted numbers
        num1 = (EditText)findViewById(R.id.value1);
        num1.setText("");
        num2 = (EditText)findViewById(R.id.value2);
        num2.setText("");

        // select the operation
        operatorSelector = (Spinner)findViewById(R.id.Spinner01);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, operators);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        operatorSelector.setAdapter(adapter);
        operatorSelector.setOnItemSelectedListener(this);
    }

    public void onItemSelected (AdapterView<?> p,View v,int position,long id) {
        selected=operators[position];
    }

    // display an error message if, for some reason, no operator is selected
    public void onNothingSelected(AdapterView<?> p) {
        result.setText("Please select an operator");
    }
}
