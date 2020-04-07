package com.example.pmpdomasno2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListJazikAdapter extends BaseAdapter {

    Context context;
    private List<Jazik> jazici;
    private RadioButton selected = null;

    public ListJazikAdapter(Context context, List<Jazik> jazici){
        this.context = context;
        this.jazici=jazici;

    }

    @Override
    public int getCount() {
        return jazici.size();
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


        final ListJazikAdapter.ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ListJazikAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.lv_jazik, parent, false);

            viewHolder.rbJazik = (RadioButton) convertView.findViewById(R.id.rbJazik);
            viewHolder.zname=(ImageView) convertView.findViewById(R.id.ivZname);
            viewHolder.txtId=(TextView) convertView.findViewById(R.id.txtIdZname);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ListJazikAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }



        viewHolder.rbJazik.setChecked(jazici.get(position).getChecked());
        viewHolder.rbJazik.setText(jazici.get(position).getIme());
        viewHolder.zname.setImageResource(jazici.get(position).getZname());
        viewHolder.txtId.setText(Integer.toString(jazici.get(position).getId()));




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

        RadioButton rbJazik;
        ImageView zname;
        TextView txtId;


    }
}
