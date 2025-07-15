package com.example.marketwork;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 直接跳转到登录页面
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish(); // 结束主活动以防止用户返回
    }
}



