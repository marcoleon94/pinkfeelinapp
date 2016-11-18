package com.pinkfeelin.Detalle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pinkfeelin.Data.Data.Buy;
import com.pinkfeelin.Data.Data.Item;
import com.pinkfeelin.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Marco on 17/11/2016.
 */

public class DetailsAdapter extends BaseAdapter {
    private List<Item> list;
    private Context context;
    private int resource;
    private LayoutInflater inflater=null;

    public DetailsAdapter(final Context context,final List<Item> list){
        this.list=list;
        this.context=context;
        this.resource= R.layout.item_detalle;
        this.inflater= LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long)this.list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=(RelativeLayout)inflater.inflate(resource,parent,false);
        }
        Item item= list.get(position);
        TextView id_compra=(TextView)convertView.findViewById(R.id.idBuyTv);
        TextView id_item= (TextView)convertView.findViewById(R.id.idItemTv);
        TextView name= (TextView)convertView.findViewById(R.id.NameTv);
        TextView qty = (TextView)convertView.findViewById(R.id.qtyTv);
        id_compra.setText(Integer.toString(item.getId_compra()));
        id_item.setText(Integer.toString(item.getId_compra()));
        name.setText(item.getNombre());
        qty.setText(Integer.toString(item.getCantidad()));
        return convertView;
    }
}
