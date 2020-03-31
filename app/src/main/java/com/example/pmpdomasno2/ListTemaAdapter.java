package com.example.pmpdomasno2;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListTemaAdapter extends BaseAdapter {


    Context context;
    private List<Tema> temi;
    private RadioButton selected = null;
    public ListTemaAdapter(Context context, List<Tema> temi){
        this.context = context;
        this.temi=temi;

    }

    @Override
    public int getCount() {
        return temi.size();
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


         final ListTemaAdapter.ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

             viewHolder = new ListTemaAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.lv_tema, parent, false);
            viewHolder.rbTema = (RadioButton) convertView.findViewById(R.id.rbTema);
            viewHolder.primary=(ImageView) convertView.findViewById(R.id.ivPrimary);
            viewHolder.primaryDark=(ImageView) convertView.findViewById(R.id.ivPrimaryDark);
            viewHolder.secondary=(ImageView)convertView.findViewById(R.id.ivSecondary);
            viewHolder.txtId=(TextView) convertView.findViewById(R.id.txtId);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ListTemaAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }



        viewHolder.rbTema.setChecked(temi.get(position).getChecked());
        viewHolder.rbTema.setText(temi.get(position).getImeTema());
        viewHolder.primary.setBackgroundColor(temi.get(position).getPrimary());
        viewHolder.primaryDark.setBackgroundColor(temi.get(position).getPrimaryDark());
        viewHolder.secondary.setBackgroundColor(temi.get(position).getSecondary());
        viewHolder.txtId.setText(Integer.toString(temi.get(position).getId()));




      /*  viewHolder.rbTema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selected != null) {
                    selected.setChecked(false);
                }
                viewHolder.rbTema.setChecked(true);
                selected = viewHolder.rbTema;
            }
        });*/


        return convertView;
    }


    private static  class ViewHolder {

        RadioButton rbTema;
        ImageView primary;
        ImageView primaryDark;
        ImageView  secondary;
        TextView txtId;


    }
}
