package com.example.pmpdomasno2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;


public class JazikFragment extends Fragment {

    public JazikFragment() {
        // Required empty public constructor
    }

    ArrayList<Jazik> jazici;
    ListJazikAdapter adapter;
    ListView lvJazici;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_jazik, container, false);

        Jazik.setJazici(getContext());
        jazici=Jazik.jazici;
        ListView lv=(ListView)v.findViewById(R.id.lvJazici);
        adapter=new ListJazikAdapter(getActivity(),jazici);
        lv.setAdapter(adapter);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                RadioButton rb=(RadioButton)view.findViewById(R.id.rbJazik);
                rb.setChecked(true);
                jazici.get(position).setChecked(true);
                jazici.get(Jazik.odrediJazik(getContext())).setChecked(false);
                adapter.notifyDataSetChanged();
                potvrdiSmena(view,position);

            }
        });
        return v;
    }


    public void potvrdiSmena(final View v,final int pos)
    {
        Resources res=getContext().getResources();
        AlertDialog dialog=new AlertDialog.Builder(v.getContext())
                .setTitle(res.getString(R.string.potvrdiSmenaJazikNaslov))
                .setMessage(res.getString(R.string.potvrdaSmenaJazikOpis))
                .setPositiveButton(res.getString(R.string.da), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Jazik.smeniJazik(getContext(),getActivity(),jazici.get(pos).getJazikKod(),jazici.get(pos).getDrzavaKod());
                        Jazik.setJazikPrefs(getActivity(),pos);
                        Jazik.restartSmenaJazik(getActivity());
                    }
                })
                .setNegativeButton(res.getString(R.string.ne), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        jazici.get(pos).setChecked(false);
                        jazici.get(Jazik.odrediJazik(getContext())).setChecked(true);
                        adapter.notifyDataSetChanged();
                    }
                }).create();
        dialog.show();

    }

}
