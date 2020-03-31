package com.example.pmpdomasno2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class SettingsFragment extends Fragment {

    ArrayList<String> opcii;
    ListSettingsAdapter adapter;


    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_settings, container, false);

        opcii=new ArrayList<String>();
        opcii.add("Temi");
        ListView lv=(ListView)v.findViewById(R.id.lvOpcii);
        adapter=new ListSettingsAdapter((SettingsActivity)getActivity(),opcii);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                Intent intent=new Intent(getActivity(), TemaActivity.class);
                intent.putExtra("klucOpcija",0);
                startActivity(intent);
                adapter.notifyDataSetChanged();



            }
        });

        return v;
    }
}
