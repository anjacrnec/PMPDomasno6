package com.example.pmpdomasno2;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.system.Os;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;


public class KreirajNovProduktFragment extends Fragment {
    ArrayList<Produkt> listaProdukti;
    ArrayList<String> novoDodadeni;
   ArrayList<String> listaProduktiIminja;
    Boolean daliValiden;
    String porakaValidnost;

    ListView lvProduktiKratko;
    Boolean proveriLvProduktiKratko;


    public KreirajNovProduktFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View v= inflater.inflate(R.layout.fragment_kreiraj_nov_produkt, container, false);


            //Intent intent = getActivity().getIntent();
            //listaProdukti = intent.getParcelableArrayListExtra("listaProduktiIntent");
           listaProduktiIminja = new ArrayList<String>();
            novoDodadeni = new ArrayList<String>();
            if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT) {
               listaProdukti = KreirajNovProizvodActivity.getListaProdukt();
               listaProduktiIminja = KreirajNovProizvodActivity.getListaIminja();
              // for (int i = 0; i < listaProdukti.size(); i++)
                    //listaProduktiIminja.add(listaProdukti.get(i).getIme());

            }
            else
            {
                listaProdukti=OsnovenFragment.getListProdukt();
                for (int i = 0; i < listaProdukti.size(); i++)
                    listaProduktiIminja.add(listaProdukti.get(i).getIme());
            }



        final Button btnNovProdukt=(Button) v.findViewById(R.id.btnNovProdukt);
        btnNovProdukt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                EditText et=(EditText)v.findViewById(R.id.tbNovProdukt);
                try {
                    kreirajNovProdukt(et);
                    if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE)
                    {
                        OsnovenFragment.adapter.notifyDataSetChanged();
                        OsnovenFragment.momentalnoSelektirani.add(new Produkt(listaProdukti.get(listaProdukti.size()-1).getIme(),listaProdukti.get(listaProdukti.size()-1).getCounter()));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });


        return v;
    }

    public Boolean kreirajNovProdukt(EditText et) throws FileNotFoundException {

        daliValiden=true;
        porakaValidnost="";
        proveriValidnostNanNovProdukt(et);
        if(daliValiden) {
            PrintStream ps = new PrintStream(getActivity().openFileOutput("novoDodadeniProdukti", getActivity().MODE_APPEND));
            String ime = et.getText().toString();
            ps.println(ime);
            ps.close();
            Produkt p = new Produkt(ime, 0, R.drawable.placeholder);
            listaProdukti.add(p);
            listaProduktiIminja.add(ime);
            novoDodadeni.add(ime);

            et.setText("");
            String poraka = "Uspehsno dodaden produktot " + ime;
            Toast.makeText(getActivity(), poraka+" "+porakaValidnost, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getActivity(),porakaValidnost,Toast.LENGTH_SHORT).show();
        }

        return daliValiden;
    }

    public void proveriValidnostNanNovProdukt(EditText et)
    {


        String ime=et.getText().toString();


        if(ime=="" || ime.isEmpty())
        {
            porakaValidnost="Ne mozite da vnesite produkt bez ime!";
            daliValiden=false;

        }
        else {
            for (int i = 0; i < listaProdukti.size(); i++) {
                if (ime.equalsIgnoreCase(listaProdukti.get(i).getIme())) {
                    porakaValidnost = "Ne mozite da vnesite produkt so ime " + ime + "bidejki vejke e vnesen takov produkt";
                    daliValiden = false;

                }
                if (!daliValiden)
                    break;
            }
        }
    }


}
