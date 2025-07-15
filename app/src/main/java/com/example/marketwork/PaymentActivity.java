package com.example.marketwork;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PaymentActivity extends AppCompatActivity {

    private TextView tvAddressDetails;
    private TextView tvTotalAmount;
    private Button btnConfirmPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        tvAddressDetails = findViewById(R.id.tvAddressDetails);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        btnConfirmPayment = findViewById(R.id.btnConfirmPayment);

        // 接收从 AddressListActivity 传递过来的地址详情和总金额
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String addressDetails = extras.getString("address_details");
            double totalAmount = extras.getDouble("total_amount");

            tvAddressDetails.setText("送货地址: " + addressDetails);
            tvTotalAmount.setText("总价: $" + String.format("%.2f", totalAmount));
        }

        btnConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPaymentConfirmationDialog();
            }
        });
    }

    private void showPaymentConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_payment_confirmation, null);

        ImageView ivQRCode = dialogView.findViewById(R.id.ivQRCode);

        // 设置二维码图片（假设二维码图片放在 drawable 文件夹中）
        ivQRCode.setImageResource(R.drawable.qr_code); // 确保你有一个名为 qr_code.png 的图片文件

        builder.setView(dialogView)
                .setTitle("确认支付")
                .setPositiveButton("完成支付", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}












