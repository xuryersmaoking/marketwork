package com.example.marketwork;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MerchantViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivMerchantAvatar;
    public TextView tvMerchantName;
    public TextView tvMerchantDescription;

    public MerchantViewHolder(@NonNull View itemView) {
        super(itemView);
        ivMerchantAvatar = itemView.findViewById(R.id.ivMerchantAvatar);
        tvMerchantName = itemView.findViewById(R.id.tvMerchantName);
        tvMerchantDescription = itemView.findViewById(R.id.tvMerchantDescription);
    }
}



