package com.example.kalkulatorpodrozy;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText distEditText = findViewById(R.id.distanceEditText);
        EditText consumptionEditText = findViewById(R.id.consumptionEditText);
        RadioGroup fuelTypeRadioGroup = findViewById(R.id.fuelRadioGroup);
        EditText passengerAmountEditText = findViewById(R.id.passengerAmountEditText);
        Button calculateBtn = findViewById(R.id.calculateBtn);
        TextView outputTextView = findViewById(R.id.outputTextView);

        calculateBtn.setOnClickListener(v-> {
            String DistanceText = distEditText.getText().toString().trim();
            String ConsumptionText = consumptionEditText.getText().toString().trim();
            String PassengerAmountText = passengerAmountEditText.getText().toString().trim();
            float Distance = parseToFloat(DistanceText);
            float Consumption = parseToFloat(ConsumptionText);
            float PassengerAmount = parseToFloat(PassengerAmountText);
            float FuelCost = -1;
            int checkedFuel = fuelTypeRadioGroup.getCheckedRadioButtonId();

            if (checkedFuel == 1) {
                FuelCost = 6.5f;
            } else if (checkedFuel == 2) {
                FuelCost = 6.7f;
            } else if (checkedFuel == 3) {
                FuelCost = 3.1f;
            }
            if (Distance > 0 && Consumption > 0 && PassengerAmount > 0 && FuelCost > 0) {
                float TotalCost = Distance*(Consumption/100)*FuelCost;
                float CostPerPerson = (Distance*(Consumption/100)*FuelCost)/PassengerAmount;


                outputTextView.setTextColor(Color.parseColor("#005300"));
                outputTextView.setText("Całkowity koszt:"+TotalCost+" zł |\n"+"Koszt na osobę:"+CostPerPerson+" zł");
            } else {
                outputTextView.setTextColor(Color.parseColor("#FF0000"));
                outputTextView.setText("Niepoprawne dane wejściowe!");
            }

        });
    }
    public float parseToFloat(String Text) {
        if (!Text.isEmpty()) {
            try {
                float Distance = Float.parseFloat(Text.replace(",", "."));
                return(Distance);
            } catch (NumberFormatException e) {
                return(-1);
            }
        }
        return(-1);
    }
}