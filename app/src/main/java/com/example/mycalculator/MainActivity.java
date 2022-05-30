package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn_plus,btn_minus,btn_clear,btn_divides,btn_dot, btn_times, btn_equal;
    TextView text_display;

    //this is to evaluate the math expression
    ScriptEngine engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        engine = new ScriptEngineManager().getEngineByName("rhino");

        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn_plus = (Button) findViewById(R.id.plus);
        btn_minus = (Button) findViewById(R.id.minus);
        btn_clear = (Button) findViewById(R.id.clear);
        btn_divides = (Button) findViewById(R.id.divide);
        btn_dot = (Button) findViewById(R.id.dot);
        btn_times = (Button) findViewById(R.id.times);
        btn_equal = (Button) findViewById(R.id.equal);

        text_display = (TextView) findViewById(R.id.text_display);
        btn_clear = (Button) findViewById(R.id.clear);

        setClickListeners();

    }

    private void setClickListeners(){
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_times.setOnClickListener(this);
        btn_divides.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn0:
                addNumber("0");
                break;
            case R.id.btn1:
                addNumber("1");
                break;
            case R.id.btn2:
                addNumber("2");
                break;
            case R.id.btn3:
                addNumber("3");
                break;
            case R.id.btn4:
                addNumber("4");
                break;
            case R.id.btn5:
                addNumber("5");
                break;
            case R.id.btn6:
                addNumber("6");
                break;
            case R.id.btn7:
                addNumber("7");
                break;
            case R.id.btn8:
                addNumber("8");
                break;
            case R.id.btn9:
                addNumber("9");
                break;
            case R.id.clear:
                clear_display();
                break;
            case R.id.plus:
                addNumber("+");
                break;
            case R.id.dot:
                addNumber(".");
                break;
            case R.id.equal:
                String result = null;
                String error = "Error";
                try {
                    result = evaluate(text_display.getText().toString());
                    text_display.setText(result);
                } catch (Exception e) {
                    text_display.setText(error);
                }
                text_display.setText(result);
                break;

            case R.id.minus:
                addNumber("-");
                break;
            case R.id.times:
                addNumber("*");
                break;
            case R.id.divide:
                addNumber("/");
                break;
        }
    }

    private String evaluate(String expression) throws ScriptException {
        String result = engine.eval(expression).toString();
        BigDecimal decimal = new BigDecimal(result);
        //return decimal.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString();
        if (decimal.signum() == 0 || decimal.scale() <= 0 || decimal.stripTrailingZeros().scale() <= 0){
            return decimal.setScale(0, BigDecimal.ROUND_HALF_UP).toPlainString();
        }else{
            return decimal.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString();
        }


    }

    private void addNumber(String number){
        text_display.setText(text_display.getText() + number );
    }

    private void clear_display(){
        text_display.setText("");
    }

}