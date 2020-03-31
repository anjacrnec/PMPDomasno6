package com.example.pmpdomasno2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListSettingsAdapter extends BaseAdapter {

    Context context;
    private final List<String> imeOpcija;

    public ListSettingsAdapter(Context context, List<String> imeOpcija){
        this.context = context;
        this.imeOpcija=imeOpcija;
    }

    @Override
    public int getCount() {
        return imeOpcija.size();
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


        ListSettingsAdapter.ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ListSettingsAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.lv_settings, parent, false);
            viewHolder.txtName = (TextView) convertView.findViewById(R.id.txtImeOpcija);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ListSettingsAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }



        viewHolder.txtName.setText(imeOpcija.get(position).toString());


        return convertView;
    }


    private static class ViewHolder {

        TextView txtName;


    }
}
