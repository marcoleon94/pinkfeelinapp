package com.pinkfeelin.Historial;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pinkfeelin.Data.Data.Buy;
import com.pinkfeelin.Data.Data.Repository.BuysRepository;
import com.pinkfeelin.R;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private ListView historial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        Bundle b1=getIntent().getBundleExtra("login");
        int id = b1.getInt("id");
        historial= (ListView)findViewById(R.id.buysListView);
        BuysRepository.getBuys(id, new BuysRepository.BuyCallback() {
            @Override
            public void error(String msg) {
                Toast.makeText(HistoryActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void success(boolean success, List<Buy> list) {
                //algo que hacer con el listView historial
                HistoryAdapter ha=new HistoryAdapter(HistoryActivity.this,list);
                historial.setAdapter(ha);
                ha.notifyDataSetChanged();
            }
        });
    }
}
