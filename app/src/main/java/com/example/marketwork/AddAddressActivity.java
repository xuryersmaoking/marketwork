package com.example.marketwork;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddAddressActivity extends AppCompatActivity {

    private EditText etAddressDetails;
    private Button btnSaveAddress;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        etAddressDetails = findViewById(R.id.etAddressDetails);
        btnSaveAddress = findViewById(R.id.btnSaveAddress);

        dbHelper = new DatabaseHelper(this);
        int userId = 1; // 假设用户ID为1，实际应用中应从登录状态获取

        btnSaveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addressDetails = etAddressDetails.getText().toString().trim();
                if (!addressDetails.isEmpty()) {
                    boolean isAdded = dbHelper.addAddress(userId, addressDetails);
                    if (isAdded) {
                        Toast.makeText(AddAddressActivity.this, "Address added", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(AddAddressActivity.this, "Failed to add address", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(AddAddressActivity.this, "Please enter address details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}









