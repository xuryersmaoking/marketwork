package com.example.marketwork;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private Button btnCart;
    private TextView tvCartItemCounter;
    private double cartTotalValue = 0.0;
    private int cartItemCount = 0;
    private List<Product> selectedProducts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        recyclerView = findViewById(R.id.recyclerViewProducts);
        btnCart = findViewById(R.id.btnCart);
        tvCartItemCounter = findViewById(R.id.tvCartItemCounter);

        // 获取商家名称
        Intent intent = getIntent();
        String merchantName = intent.getStringExtra("merchantName");
        Log.d("ProductListActivity", "Received merchant name: " + merchantName); // 添加日志

        productList = new ArrayList<>();
        // 根据商家名称加载不同的产品数据
        switch (merchantName) {
            case "古茗":
                productList.add(new Product("Product A1", "Description A1", 19.99, R.drawable.product_a));
                break;
            case "阿迪达斯":
                productList.add(new Product("Product B1", "Description B1", 400.00, R.drawable.a1));
                productList.add(new Product("Product B2", "Description B2", 500.00, R.drawable.a2));
                productList.add(new Product("Product B3", "Description C1", 550.00, R.drawable.a3));
                productList.add(new Product("Product B4", "Description C2", 570.00, R.drawable.a4));
                productList.add(new Product("Product B5", "Description C2", 1000.00, R.drawable.a5));
                productList.add(new Product("Product B6", "Description C2", 700.00, R.drawable.a6));
                productList.add(new Product("Product B7", "Description C2", 500.00, R.drawable.a7));
                productList.add(new Product("Product B8", "Description C2", 800.00, R.drawable.a8));
                break;
            case "耐克":
                productList.add(new Product("Product C1", "Description C1", 59.99, R.drawable.product_c));
                productList.add(new Product("Product C2", "Description C2", 69.99, R.drawable.product_c));
                break;
            case "蜜雪冰城":
                productList.add(new Product("Product C1", "Description C1", 4.0, R.drawable.m1));
                productList.add(new Product("Product C2", "Description C2", 8.0, R.drawable.m2));
                productList.add(new Product("Product C3", "Description C2", 9.0, R.drawable.m3));
                productList.add(new Product("Product C4", "Description C2", 12.0, R.drawable.m4));
                productList.add(new Product("Product C5", "Description C2", 7.0, R.drawable.m5));
                productList.add(new Product("Product C6", "Description C2", 15.0, R.drawable.m6));
                productList.add(new Product("Product C7", "Description C2", 12.0, R.drawable.m7));
                productList.add(new Product("Product C8", "Description C2", 11.0, R.drawable.m8));
                break;
            default:
                // 默认情况下的产品列表
                productList.add(new Product("Default Product", "Default Description", 9.99, R.drawable.product_c));
                break;
        }

        productAdapter = new ProductAdapter(productList, new ProductAdapter.OnAddToCartClickListener() {
            @Override
            public void onAddToCartClick(Product product) {
                addToCart(product);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductListActivity.this, CheckoutActivity.class);
                intent.putExtra("selected_products", (Serializable) selectedProducts);
                intent.putExtra("cart_total_value", cartTotalValue);
                startActivity(intent);
            }
        });
    }

    private void addToCart(Product product) {
        cartItemCount++;
        cartTotalValue += product.getPrice();
        selectedProducts.add(product);
        updateCartItemCounter();
    }

    private void updateCartItemCounter() {
        if (cartItemCount > 0) {
            tvCartItemCounter.setVisibility(View.VISIBLE);
            tvCartItemCounter.setText(String.valueOf(cartItemCount));
        } else {
            tvCartItemCounter.setVisibility(View.GONE);
        }
    }
}



