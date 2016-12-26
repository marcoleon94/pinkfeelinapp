package exaples.com.dailyselfie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco on 14/12/2016.
 */

public class PictureAdapter extends BaseAdapter {
    private ArrayList<File> data = new ArrayList<>();
    private Context context;
    private int resource;
    private LayoutInflater inflater=null;

    public PictureAdapter(Context context){
        this.context=context;
        this.resource=R.layout.item_foto;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=(RelativeLayout)inflater.inflate(resource,parent,false);
        }
        File f=data.get(position);
        Bitmap bitmap= BitmapFactory.decodeFile(f.getPath());
        ImageView foto=(ImageView) convertView.findViewById(R.id.foto);
        TextView nombre= (TextView) convertView.findViewById(R.id.nombre_foto);
        foto.setImageBitmap(bitmap);
        nombre.setText(f.getName());
        return convertView;
    }
    public void add(File f){
        data.add(f);
        notifyDataSetChanged();
    }
}
