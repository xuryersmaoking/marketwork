package com.example.marketwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnSelectAddress;
    private TextView tvTotalAmount;
    private double totalAmount;
    private List<Product> selectedProducts;
    private CheckoutAdapter checkoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        recyclerView = findViewById(R.id.recyclerViewSelectedProducts);
        btnSelectAddress = findViewById(R.id.btnSelectAddress);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);

        // 接收从 ProductListActivity 传递过来的商品列表和总金额
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectedProducts = (List<Product>) extras.getSerializable("selected_products");
            totalAmount = extras.getDouble("cart_total_value");

            tvTotalAmount.setText("Total Amount: $" + String.format("%.2f", totalAmount));

            checkoutAdapter = new CheckoutAdapter(selectedProducts, new CheckoutAdapter.OnDeleteClickListener() {
                @Override
                public void onDeleteClick(int position) {
                    removeProductFromCart(position);
                }
            });

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(checkoutAdapter);
        }

        btnSelectAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckoutActivity.this, AddressListActivity.class);
                intent.putExtra("total_amount", totalAmount);
                startActivity(intent);
            }
        });
    }

    private void removeProductFromCart(int position) {
        if (position >= 0 && position < selectedProducts.size()) {
            Product removedProduct = selectedProducts.remove(position);
            totalAmount -= removedProduct.getPrice();
            tvTotalAmount.setText("Total Amount: $" + String.format("%.2f", totalAmount));
            checkoutAdapter.notifyItemRemoved(position);
            checkoutAdapter.notifyItemRangeChanged(position, selectedProducts.size());

            // 如果购物车为空，可以考虑返回到 ProductListActivity 或其他适当的页面
            if (selectedProducts.isEmpty()) {
                finish(); // 关闭当前 Activity
            }
        }
    }
}




