package com.pinkfeelin.Detalle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.pinkfeelin.Data.Data.Item;
import com.pinkfeelin.Data.Data.Repository.ItemsRepository;
import com.pinkfeelin.R;

import java.util.List;

public class DetallesActivity extends AppCompatActivity {
    ListView detalles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        Bundle b= getIntent().getBundleExtra("history");
        int id_compra= b.getInt("id_compra");
        detalles=(ListView) findViewById(R.id.detalles_list);
        ItemsRepository.getDetails(id_compra, new ItemsRepository.DetailsCallback() {
            @Override
            public void error(String msg) {
                Toast.makeText(DetallesActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void success(boolean success, List<Item> list) {
                DetailsAdapter da= new DetailsAdapter(DetallesActivity.this,list);
                detalles.setAdapter(da);
                da.notifyDataSetChanged();

            }
        });



    }
}
