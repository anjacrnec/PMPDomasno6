package com.example.pmpdomasno2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class TemaPostaviFragment extends Fragment {

    ArrayList<Tema> temi;
    ListView lvTemi;
    ListTemaAdapter adapter;


    public TemaPostaviFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v=inflater.inflate(R.layout.fragment_tema_postavi, container, false);

        temi=Tema.temi;

        ListView lv=(ListView)v.findViewById(R.id.lvTemi);
        adapter=new ListTemaAdapter(getActivity(),temi);
        lv.setAdapter(adapter);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                RadioButton rb=(RadioButton)view.findViewById(R.id.rbTema);
                rb.setChecked(true);
                TextView tv=(TextView)view.findViewById(R.id.txtId);
                String strId=tv.getText().toString();
                final int  intId=Integer.parseInt(strId);
                String s=rb.getText().toString();
                Tema.checkTemiSporedMomentalna(v.getContext(),intId,false,true);
                adapter.notifyDataSetChanged();
                potvrdiSmena(v,intId);



            }
        });


        return  v;
    }

    public void potvrdiSmena(final View v,final int id)
    {
        AlertDialog dialog=new AlertDialog.Builder(v.getContext())
                .setTitle("Smena na tema")
                .setMessage("Dali sigurno sakate da ja smenite temata na aplikacijata? Dokolku izberite da, aplikacijata kje se restartira.")
                .setPositiveButton("Da", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Tema.setTemaPrefs(getActivity(),id);
                        Tema.restartTema(getActivity());
                    }
                })
                .setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Tema.checkTemiSporedMomentalna(v.getContext(),id,true,false);
                        adapter.notifyDataSetChanged();
                    }
                }).create();
        dialog.show();

    }

    public ListTemaAdapter getListTemaAdapter()
    {
        return adapter;
    }





}
