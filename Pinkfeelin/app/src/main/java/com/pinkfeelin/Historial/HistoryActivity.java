package com.pinkfeelin.Historial;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.pinkfeelin.Data.Data.Buy;
import com.pinkfeelin.Data.Data.Repository.BuysRepository;
import com.pinkfeelin.Detalle.DetallesActivity;
import com.pinkfeelin.R;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private ListView historial;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        Bundle b1=getIntent().getBundleExtra("login");
        this.id = b1.getInt("id");
        historial= (ListView)findViewById(R.id.buysListView);
        historial.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openDetalles(id);
            }
        });
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

    private void openDetalles(long id_item) {
        Intent intent= new Intent(this, DetallesActivity.class);
        Bundle b= new Bundle();
        b.putInt("id_compra",(int)id_item);
        intent.putExtra("history",b);
        startActivity(intent);
    }
}
