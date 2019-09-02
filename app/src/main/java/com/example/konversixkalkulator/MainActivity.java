package com.example.konversixkalkulator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText txtCelcius, txtKelvin, txtFahrenheit, txtReamur;
    Button btnKonversi, btnKalku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Konversi Suhu");

        txtCelcius = findViewById(R.id.txt_celcius);
        txtFahrenheit = findViewById(R.id.txt_fahrenheit);
        txtKelvin = findViewById(R.id.txt_kelvin);
        txtReamur = findViewById(R.id.txt_reamur);
        btnKonversi = findViewById(R.id.btn_konversi);
        btnKalku = findViewById(R.id.btn_kalkulator);

        btnKonversi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Celcius = txtCelcius.getText().toString().trim();

                double celcius = Double.parseDouble(Celcius);
                double kelvin = celcius + 273.15;
                double reamur = celcius * 0.8;
                double fahrenheit = celcius * 1.8 + 32;

                txtKelvin.setText(String.valueOf(kelvin));
                txtFahrenheit.setText(String.valueOf(fahrenheit));
                txtReamur.setText(String.valueOf(reamur));
            }
        });

        btnKalku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kalkuIntent = new Intent(MainActivity.this, Kalkulator.class);
                        startActivity(kalkuIntent);
            }
        });
    }
}
