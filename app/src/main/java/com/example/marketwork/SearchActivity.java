package com.example.marketwork;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText etSearch;
    private RecyclerView recyclerViewMerchants;
    private MerchantAdapter merchantAdapter;
    private List<Merchant> merchantList;
    private List<Merchant> filteredMerchantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etSearch = findViewById(R.id.etSearch);
        recyclerViewMerchants = findViewById(R.id.recyclerViewMerchants);

        Log.d("SearchActivity", "Views initialized");

        // 示例商家列表
        merchantList = Arrays.asList(
                new Merchant("古茗", "源自浙江台州的新式茶饮品牌，以“每天一杯喝不腻”为理念，深耕下沉市场，凭借新鲜原料、高性价比和创新产品，成为全国拥有近万家门店的头部茶饮连锁品牌", R.drawable.merchant_a),
                new Merchant("阿迪达斯", "源自德国的全球知名运动品牌，以三条纹、三叶草为标志，致力于通过创新设计与科技推动运动装备进步，产品涵盖运动鞋、服装及配件，赞助全球顶级赛事与体育团队，融合运动性能与时尚潮流，成为运动爱好者和追求品质生活人群的优选品牌。", R.drawable.merchant_b),
                new Merchant("耐克", "全球著名的美国运动品牌，以“Just Do It”为核心理念，凭借创新的气垫技术、时尚的设计和卓越的运动性能，成为全球运动鞋服及装备市场的领军品牌。", R.drawable.merchant_c),
                new Merchant("蜜雪冰城", "中国本土高人气新茶饮品牌，以“高质平价”为核心，凭借魔性主题曲、雪王IP和遍布全国的2万+门店，用3元冰淇淋、4元柠檬水等亲民产品征服年轻消费者，成为下沉市场“奶茶界拼多多”。", R.drawable.merchant_d),
                new Merchant("三只松鼠", "中国知名的休闲零食品牌，以坚果为核心产品，通过“高端性价比”战略和全渠道布局，成为累计服务超1.7亿消费者的行业龙头，并成功孵化婴童食品子品牌“小鹿蓝蓝”。", R.drawable.merchant_e)
        );

        Log.d("SearchActivity", "Merchant list created with size: " + merchantList.size());

        filteredMerchantList = new ArrayList<>(merchantList);
        merchantAdapter = new MerchantAdapter(this, filteredMerchantList);
        recyclerViewMerchants.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMerchants.setAdapter(merchantAdapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterMerchants(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        Log.d("SearchActivity", "RecyclerView initialized");
    }

    private void filterMerchants(String query) {
        filteredMerchantList.clear();
        for (Merchant merchant : merchantList) {
            if (merchant.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredMerchantList.add(merchant);
            }
        }
        merchantAdapter.notifyDataSetChanged();
        Log.d("SearchActivity", "Filtered merchant list updated with size: " + filteredMerchantList.size());
    }
}



