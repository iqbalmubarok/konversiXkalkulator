package com.example.konversixkalkulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Kalkulator extends AppCompatActivity {

    private int[] numberButtons = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};

    private int[] operatorButtons = {R.id.btnKali,R.id.btnBagi,R.id.btnTambah,R.id.btnKurang,};

    private TextView txtHasil;
    private boolean lastNumeric, lasDot, stateError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);
        getSupportActionBar().setTitle("Kalkulator");

        txtHasil = findViewById(R.id.txt_hasil);
        setNumericOnClickListener();
        setOperatorOnClickListener();
    }

    private void setNumericOnClickListener(){
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button button = (Button) v;
                if (stateError){
                    txtHasil.setText(button.getText());
                    stateError = false;
                }else {
                    txtHasil.append(button.getText());
                }
                lastNumeric = true;
            }
        };
        for (int id : numberButtons){
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorOnClickListener(){
        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lastNumeric && !stateError){
                    Button button = (Button) view;
                    txtHasil.append(button.getText());
                    lastNumeric = false;
                    lasDot = false;
                }
            }
        };
        for (int id : operatorButtons){
            findViewById(id).setOnClickListener(listener2);
        }
        findViewById(R.id.btnKoma).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lastNumeric && !stateError && !lasDot){
                    txtHasil.append(".");
                    lastNumeric = false;
                    lasDot = true;
                }
            }
        });
        findViewById(R.id.btn_AC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtHasil.setText("");
                lastNumeric = false;
                stateError = false;
                lasDot = false;
            }
        });
        findViewById(R.id.btnSamaDengan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEqual();
            }
        });
    }

    private void onEqual(){
        if (lastNumeric && !stateError){
            String txt = txtHasil.getText().toString();

            Expression expression = new ExpressionBuilder(txt).build();
            try {
                double result = expression.evaluate();
                txtHasil.setText(Double.toString(result));
                lasDot = true;
            }catch (ArithmeticException ex){
                txtHasil.setText("Error");
                stateError = true;
                lastNumeric = false;
            }
        }
    }
}
