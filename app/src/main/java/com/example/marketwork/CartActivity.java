package com.example.marketwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ListView lvCartItems;
    private Button btnCheckout;
    private TextView tvTotalPrice;
    private ArrayAdapter<String> adapter;
    private List<String> cartItems;
    private double totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        lvCartItems = findViewById(R.id.lvCartItems);
        btnCheckout = findViewById(R.id.btnCheckout);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);

        cartItems = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cartItems);
        lvCartItems.setAdapter(adapter);

        // 获取传递的产品信息并添加到购物车
        Intent intent = getIntent();
        if (intent != null) {
            String productName = intent.getStringExtra("productName");
            double productPrice = intent.getDoubleExtra("productPrice", 0.0);
            if (productName != null) {
                cartItems.add(productName + " - $" + productPrice);
                totalPrice += productPrice;
                updateTotalPrice();
            }
        }

        lvCartItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 可以在这里添加移除商品的逻辑
                cartItems.remove(position);
                totalPrice -= getProductPrice(cartItems.get(position));
                updateTotalPrice();
                adapter.notifyDataSetChanged();
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
                intent.putExtra("totalPrice", totalPrice);
                startActivity(intent);
            }
        });
    }

    private double getProductPrice(String item) {
        String[] parts = item.split(" - \\$");
        return Double.parseDouble(parts[1]);
    }

    private void updateTotalPrice() {
        tvTotalPrice.setText("Total Price: $" + totalPrice);
    }
}



