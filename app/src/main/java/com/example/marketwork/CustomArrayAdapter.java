package com.example.marketwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<Merchant> {

    public CustomArrayAdapter(Context context, int resource, List<Merchant> merchants) {
        super(context, resource, merchants);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Merchant merchant = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_merchant, parent, false);
        }

        ImageView ivMerchantAvatar = convertView.findViewById(R.id.ivMerchantAvatar);
        TextView tvMerchantName = convertView.findViewById(R.id.tvMerchantName);
        TextView tvMerchantDescription = convertView.findViewById(R.id.tvMerchantDescription);

        ivMerchantAvatar.setImageResource(merchant.getImageResId());
        tvMerchantName.setText(merchant.getName());
        tvMerchantDescription.setText(merchant.getDescription());

        return convertView;
    }
}