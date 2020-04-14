package com.example.pmpdomasno2;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.system.Os;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;


public class KreirajNovProduktFragment extends Fragment {


   public static final String novProduktImeKluc="novProduktIme";

    public KreirajNovProduktFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v= inflater.inflate(R.layout.fragment_kreiraj_nov_produkt, container, false);

        int t=Tema.odrediTema(getContext());
        TextView tv=(TextView)v.findViewById(R.id.txtNov);
        LinearLayout ll=(LinearLayout) v.findViewById(R.id.ll);
        Tema.setTemaSliki(getContext(),t,tv);
        Tema.setTemaSliki(getContext(),t,ll);



        final Button btnNovProdukt=(Button) v.findViewById(R.id.btnNovProdukt);
        btnNovProdukt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                EditText et=(EditText)v.findViewById(R.id.tbNovProdukt);
                    kreirajNovProdukt(et);
                    if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE)
                    {
                        OsnovenFragment.adapter.notifyDataSetChanged();

                    }


            }
        });


        return v;
    }


    public void kreirajNovProdukt(EditText et)
    {
        Resources res=getContext().getResources();
        String ime=et.getText().toString();
        if(ime.trim().isEmpty())
        {
            Toast.makeText(getContext(),res.getString(R.string.warningProduktBezIme),Toast.LENGTH_LONG);
            return;
        }

        Produkt p=new Produkt(ime,0,0,R.drawable.placeholder);
        OsnovenFragment.getPcViewModel().insert(p);
        et.setText("");
        Toast.makeText(getContext(),res.getString(R.string.uspeshnoKreiranProdukt)+" "+ime,Toast.LENGTH_SHORT).show();
    }


}
