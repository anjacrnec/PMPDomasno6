package com.example.pmpdomasno2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListAdapter extends BaseAdapter {


    Context context;
    private  List <Produkt> produkti=new ArrayList<Produkt>();


    public ListAdapter(Context context){
        this.context = context;

    }

    @Override
    public int getCount() {
        return produkti.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.moj_lv_layout, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtImeProdukt);
            viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.txtCounter);
            viewHolder.txtId=(TextView) convertView.findViewById(R.id.txtProduktID);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.ivIkonaPordukt);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }



        viewHolder.txtName.setText(produkti.get(position).getIme().toString());
        viewHolder.txtId.setText(Integer.toString(produkti.get(position).getId()));
        viewHolder.txtVersion.setText(Integer.toString(produkti.get(position).getCounterTemp()));
        viewHolder.icon.setImageResource(produkti.get(position).getSlika());

        int t=Tema.odrediTema(context);
        Tema.setTemaSliki(context,t,viewHolder.icon);

        return convertView;
    }

    public Produkt getProduktAt(int pos)
    {
        return produkti.get(pos);
    }

    private static class ViewHolder {

        TextView txtName;
        TextView txtVersion;
        TextView txtId;
        ImageView icon;

    }

    public void setProdukti(List<Produkt> produkti)
    {
        this.produkti=produkti;
        notifyDataSetChanged();
    }
}
