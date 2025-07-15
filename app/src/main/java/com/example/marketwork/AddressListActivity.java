package com.example.marketwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AddressListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnAddAddress;
    private AddressAdapter addressAdapter;
    private List<Address> addressList;
    private DatabaseHelper dbHelper;
    private double totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);

        recyclerView = findViewById(R.id.recyclerViewAddresses);
        btnAddAddress = findViewById(R.id.btnAddAddress);

        dbHelper = new DatabaseHelper(this);
        int userId = 1; // 假设用户ID为1，实际应用中应从登录状态获取

        addressList = dbHelper.getAddressesByUserId(userId);

        addressAdapter = new AddressAdapter(addressList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(addressAdapter);

        btnAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressListActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });

        // 接收从 CheckoutActivity 传递过来的总金额
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            totalAmount = extras.getDouble("total_amount");
        }
    }

    private class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {

        private List<Address> addressList;

        AddressAdapter(List<Address> addressList) {
            this.addressList = addressList;
        }

        @NonNull
        @Override
        public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false);
            return new AddressViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
            Address address = addressList.get(position);
            holder.tvAddressDetails.setText(address.getDetails());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(AddressListActivity.this, PaymentActivity.class);
                    intent.putExtra("address_details", address.getDetails());
                    intent.putExtra("total_amount", totalAmount);
                    startActivity(intent);
                }
            });

            holder.btnDeleteAddress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isDeleted = dbHelper.deleteAddressById(address.getId());
                    if (isDeleted) {
                        addressList.remove(position);
                        notifyItemRemoved(position);
                        Toast.makeText(AddressListActivity.this, "Address deleted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddressListActivity.this, "Failed to delete address", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return addressList.size();
        }

        class AddressViewHolder extends RecyclerView.ViewHolder {
            TextView tvAddressDetails;
            Button btnDeleteAddress;

            AddressViewHolder(View itemView) {
                super(itemView);
                tvAddressDetails = itemView.findViewById(R.id.tvAddressDetails);
                btnDeleteAddress = itemView.findViewById(R.id.btnDeleteAddress);
            }
        }
    }
}















