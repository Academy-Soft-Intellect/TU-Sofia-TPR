package com.example.sestrimski.secondandroidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private static HashMap<String, Double> list = new HashMap<String, Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.convertButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView result = (TextView) findViewById(R.id.resultTextbox);
                TextView value = (TextView) findViewById(R.id.mainValue);
                spinner = (Spinner) findViewById(R.id.spinner);
                double currentValue = Double.parseDouble(value.getText().toString());
                double rate = list.get(spinner.getSelectedItem());
                Double currentResult = convert(currentValue, rate);
                double roundedValue = (double)Math.round(currentResult * 1000d) / 1000d;
                result.setText(String.format("%.3f", currentResult));

            }
        });

        list.put("USD (US Dollar)", 1.7636);
        list.put("EUR (Euro)", 1.9569);
        list.put("GBP (British pound)", 2.52056);

        String[] arrayList = list.keySet().toArray(new String[list.size()]);

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
        (MainActivity.this, android.R.layout.simple_spinner_item, arrayList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public static double convert(double number, double currencyRate) {

        return number / currencyRate;

    }
}
