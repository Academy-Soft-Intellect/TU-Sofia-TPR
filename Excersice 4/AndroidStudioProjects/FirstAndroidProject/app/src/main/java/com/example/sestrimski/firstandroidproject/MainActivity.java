package com.example.sestrimski.firstandroidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.calculateButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView result = (TextView) findViewById(R.id.resultTextbox);
                EditText t1 = (EditText) findViewById(R.id.firstNumber);
                EditText t2 = (EditText) findViewById(R.id.secondNumber);
                int d1 = Integer.parseInt(t1.getText().toString());
                int d2 = Integer.parseInt(t2.getText().toString());
                Integer currentResult = calculate(d1, d2);
                result.setText(currentResult.toString());

            }
        });
    }

    public static int calculate(int number, int divider) {

        int curent_number = 1;

        do {
            int result = number * curent_number++;

            if (result % divider == 0) {
                return result;
            }

        } while(true);

    }
}
