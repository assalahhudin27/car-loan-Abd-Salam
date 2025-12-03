package com.example.individualass;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText vehiclePrice, downPayment, loanPeriod, interestRate;
    Button calcButton;
    TextView totalAmount, totalInterest, totalPayment, monthlyPayment;

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

        vehiclePrice = findViewById(R.id.price);
        downPayment = findViewById(R.id.down_payment);
        loanPeriod = findViewById(R.id.loan_period);
        interestRate = findViewById(R.id.rate);

        calcButton = findViewById(R.id.calc);

        totalAmount = findViewById(R.id.amount);
        totalInterest = findViewById(R.id.interest);
        totalPayment = findViewById(R.id.total);
        monthlyPayment = findViewById(R.id.monthly);

        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double VehiclePrice = Double.parseDouble(vehiclePrice.getText().toString());
                Double DownPayment = Double.parseDouble(downPayment.getText().toString());
                int LoanPeriod = Integer.parseInt(loanPeriod.getText().toString());
                Double InterestRate = Double.parseDouble(interestRate.getText().toString());

                Double TotalAmount = VehiclePrice - DownPayment;
                Double TotalInterest = TotalAmount * (InterestRate / 100) * LoanPeriod;
                Double TotalPayment = TotalAmount + TotalInterest;
                Double MonthlyPayment = TotalPayment / (LoanPeriod * 12);

                totalAmount.setText("Loan Amount: " + String.format("%.2f", TotalAmount));
                totalInterest.setText("Total Interest: " + String.format("%.2f", TotalInterest));
                totalPayment.setText("Total Payment: " + String.format("%.2f", TotalPayment));
                monthlyPayment.setText("Monthly Payment: " + String.format("%.2f", MonthlyPayment));
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
