package com.pinkfeelin.Historial;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pinkfeelin.Data.Data.Buy;
import com.pinkfeelin.R;

import java.util.List;

/**
 * Created by Marco on 16/11/2016.
 */

public class HistoryAdapter extends BaseAdapter {
    private List<Buy> list;
    private Context context;
    private  int resource;
    private LayoutInflater inflater= null;

    public HistoryAdapter(Context context,List <Buy> list){
        this.list=list;
        this.context=context;
        this.resource= R.layout.item_historial;
        this.inflater=LayoutInflater.from(context);
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
        return this.list.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=(RelativeLayout)inflater.inflate(resource,parent,false);
        }
        Buy item= list.get(position);
        TextView id_compra=(TextView)convertView.findViewById(R.id.idTextView);
        TextView importe= (TextView)convertView.findViewById(R.id.importTextView);
        TextView fecha = (TextView)convertView.findViewById(R.id.dateTextView);
        id_compra.setText(Integer.toString(item.getId()));
        importe.setText(item.getImporte());
        fecha.setText(item.getCreated_at());
        return convertView;
    }
}
