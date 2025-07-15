package com.example.marketwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class VerifyCodeActivity extends AppCompatActivity {

    private EditText etVerificationCode;
    private Button btnResetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_code);

        etVerificationCode = findViewById(R.id.etVerificationCode);
        btnResetPassword = findViewById(R.id.btnResetPassword);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String verificationCode = etVerificationCode.getText().toString();

                if (validateVerificationCode(verificationCode)) {
                    // 这里可以添加实际的密码重置逻辑
                    Toast.makeText(VerifyCodeActivity.this, "Password reset successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(VerifyCodeActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean validateVerificationCode(String verificationCode) {
        if (verificationCode.isEmpty()) {
            etVerificationCode.setError("Verification code is required");
            return false;
        }
        // 可以在这里添加更多的验证逻辑，例如检查验证码长度
        return true;
    }
}



