package com.example.marketwork;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MerchantAdapter extends RecyclerView.Adapter<MerchantViewHolder> {

    private Context context;
    private List<Merchant> merchantList;

    public MerchantAdapter(Context context, List<Merchant> merchantList) {
        this.context = context;
        this.merchantList = merchantList;
    }

    @NonNull
    @Override
    public MerchantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_merchant, parent, false);
        return new MerchantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MerchantViewHolder holder, int position) {
        Merchant merchant = merchantList.get(position);
        holder.ivMerchantAvatar.setImageResource(merchant.getImageResId());
        holder.tvMerchantName.setText(merchant.getName());
        holder.tvMerchantDescription.setText(merchant.getDescription());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductListActivity.class);
            intent.putExtra("merchantName", merchant.getName());
            Log.d("MerchantAdapter", "Clicked merchant: " + merchant.getName()); // 添加日志
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return merchantList.size();
    }
}



