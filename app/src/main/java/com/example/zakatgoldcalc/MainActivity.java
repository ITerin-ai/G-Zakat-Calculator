package com.example.zakatgoldcalc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etWeight, etGoldValue;
    private RadioGroup rgType;
    private RadioButton rbKeep, rbWear;
    private Button btnCalculate, btnReset;
    private TextView tvOutput, tvTotalZakatOutput;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        etWeight = findViewById(R.id.etWeight);
        etGoldValue = findViewById(R.id.etGoldValue);
        rgType = findViewById(R.id.rgType);
        rbKeep = findViewById(R.id.rbKeep);
        rbWear = findViewById(R.id.rbWear);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);
        tvOutput = findViewById(R.id.tvOutput);
        tvTotalZakatOutput = findViewById(R.id.tvTotalZakatOutput);

        btnCalculate.setOnClickListener(v -> calculateZakat());

        btnReset.setOnClickListener(v -> resetFields());

        TextView btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void calculateZakat() {
        String weightStr = etWeight.getText().toString();
        String valueStr = etGoldValue.getText().toString();

        if (weightStr.isEmpty()) {
            etWeight.setError("Weight is required");
            etWeight.requestFocus();
            return;
        }
        if (valueStr.isEmpty()) {
            etGoldValue.setError("Price is required");
            etGoldValue.requestFocus();
            return;
        }

        double weight = Double.parseDouble(weightStr);
        double goldValue = Double.parseDouble(valueStr);
        double X = rbKeep.isChecked() ? 85.0 : 200.0;
        double totalValue = weight * goldValue;

        double weightMinusX = weight - X;

        double zakatPayableValue = 0;
        if (weightMinusX > 0) {
            zakatPayableValue = weightMinusX * goldValue;
        }

        double totalZakat = zakatPayableValue * 0.025;

        String baselineResults = String.format(
                "Total Gold Value: RM %.2f\nZakat Payable Value: RM %.2f",
                totalValue,
                zakatPayableValue
        );

                String totalZakatResult = String.format("Total Zakat: RM %.2f", totalZakat);

                tvOutput.setText(baselineResults);
                tvTotalZakatOutput.setText(totalZakatResult);
    }


    private void resetFields() {
        etWeight.setText("");
        etGoldValue.setText("");
        rbKeep.setChecked(true);
        tvOutput.setText("Enter gold details above to see calculation results.");
        tvTotalZakatOutput.setText(""); // Clears the large text view
    }
}