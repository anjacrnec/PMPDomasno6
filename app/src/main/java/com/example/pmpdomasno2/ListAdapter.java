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

public class ListAdapter extends BaseAdapter {

    Context context;
    private final List <Produkt> produkti;

    public ListAdapter(Context context, List<Produkt> produkti){
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        this.context = context;
        this.produkti=produkti;
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
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.ivIkonaPordukt);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }



        viewHolder.txtName.setText(produkti.get(position).getIme().toString());
        viewHolder.txtVersion.setText(Integer.toString(produkti.get(position).getCounter()));
        viewHolder.icon.setImageResource(produkti.get(position).getSlika());

        return convertView;
    }

    private static class ViewHolder {

        TextView txtName;
        TextView txtVersion;
        ImageView icon;

    }
}
