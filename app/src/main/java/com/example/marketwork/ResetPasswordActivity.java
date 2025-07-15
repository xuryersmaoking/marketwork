package com.example.marketwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText etPhoneNumber;
    private Button btnSendCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnSendCode = findViewById(R.id.btnSendCode);

        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = etPhoneNumber.getText().toString();

                if (validatePhoneNumber(phoneNumber)) {
                    // 这里可以添加发送验证码的逻辑
                    Toast.makeText(ResetPasswordActivity.this, "Verification code sent", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ResetPasswordActivity.this, VerifyCodeActivity.class);
                    intent.putExtra("phoneNumber", phoneNumber);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.isEmpty()) {
            etPhoneNumber.setError("Phone number is required");
            return false;
        }
        // 可以在这里添加更多的验证逻辑，例如检查手机号码格式
        return true;
    }
}



