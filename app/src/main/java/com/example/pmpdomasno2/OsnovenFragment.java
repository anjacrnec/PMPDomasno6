package com.example.pmpdomasno2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class OsnovenFragment extends Fragment {

    ListView lvProdukti;

    public static ListAdapter adapter;

    public static ArrayList<Produkt> listaProdukti;

    private static ProduktCounterViewModel pcViewModel;

    public static final int KREIRAJ_PRODUKT_REQ=0;
    List <Produkt> pp;
    public OsnovenFragment() {

    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_osnoven, container, false);
        int t=Tema.odrediTema(getContext());
        final FloatingActionButton fabKreirajNov=(FloatingActionButton) v.findViewById(R.id.fabKreirajNov);
        Tema.setTemaSliki(getContext(),t,fabKreirajNov);

        pcViewModel= ViewModelProviders.of(getActivity()).get(ProduktCounterViewModel.class);
        pcViewModel.getSiteProdukti().observe(getActivity(), new Observer<List<Produkt>>() {
            @Override
            public void onChanged(List<Produkt> produkts) {
                adapter.setProdukti(produkts);
            }
        });


        lvProdukti = (ListView) v.findViewById(R.id.lvProdukti);

        adapter = new ListAdapter((MainActivity)getActivity());
        lvProdukti.setAdapter(adapter);


        lvProdukti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                TextView tvId=view.findViewById(R.id.txtProduktID);
                int intId=Integer.parseInt(tvId.getText().toString());
                pcViewModel.updateCounterTempIncrement(intId);
            }
        });

       lvProdukti.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               Produkt p=adapter.getProduktAt(position);
               potvrdiBrisenjeProdukt(p);
               return true;
           }
       });


        final ImageButton btnDodajProdukt=(ImageButton) v.findViewById(R.id.btnDodajProdukt);
        btnDodajProdukt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try {
                    dodajProdukt();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        final ImageButton btnIstorijaProdukti=(ImageButton)v.findViewById(R.id.btnIsotrijaProdukti);
        btnIstorijaProdukti.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public  void onClick(View view)
            {
                try {
                    prikaziIstorijaProdukti();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ;
            }
        });

        final ImageButton btnUndo=(ImageButton) v.findViewById(R.id.btnUndo);
        btnUndo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                undoProdukti();
            }
        });

        fabKreirajNov.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT) {
                    Intent intent = new Intent(getActivity(), KreirajNovProizvodActivity.class);
                    startActivity(intent);
                }
                else
                {
                    ((MainActivity) getActivity()).addKreirajNovProizvodFragment();
                    FrameLayout fl=getActivity().findViewById(R.id.osnovenFragmentContainer);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) fl.getLayoutParams();
                    layoutParams.weight = 2;
                    fl.setLayoutParams(layoutParams);


                }
            }
        });



        return v;
    }









    public void dodajProdukt() throws ExecutionException, InterruptedException {
        Resources res=getContext().getResources();
        int sum=pcViewModel.getSumCounterTemp();
        if(sum!=0)
        {
            pcViewModel.updateCounter();
            pcViewModel.resetCounterTemp();
            Toast.makeText(getActivity(), res.getQuantityString(R.plurals.uspeshnoDodadeniProdukti,sum,sum), Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getActivity(),res.getString(R.string.nemaDodadeniProdukti),Toast.LENGTH_LONG).show();
        }
    }

    public void prikaziIstorijaProdukti() throws ExecutionException, InterruptedException {
        Resources res=getContext().getResources();
        List<Produkt> istorijaProdukti=pcViewModel.getListprodukti();
        String istorija=res.getString(R.string.istorijaProdukti)+"\n";
        for(int i=0;i<istorijaProdukti.size();i++)
        {
            istorija=istorija+istorijaProdukti.get(i).getIme()+" - "+istorijaProdukti.get(i).getCounter()+" \n";
        }
        Toast.makeText(getActivity(),istorija,Toast.LENGTH_LONG).show();
    }


    public void undoProdukti()
    {
      pcViewModel.resetCounterTemp();
    }

    public void potvrdiBrisenjeProdukt(final Produkt p)
    {
        Resources res=getContext().getResources();
        AlertDialog dialog=new AlertDialog.Builder(getContext())
                .setTitle(res.getString(R.string.potvrdaBrisenjeProduktNaslov))
                .setMessage(res.getString(R.string.potvrdaBrisenjeProduktOpis,p.getIme()))
                .setPositiveButton(res.getString(R.string.da), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pcViewModel.delete(p);
                    }
                })
                .setNegativeButton(res.getString(R.string.ne), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
        dialog.show();

    }

    public static ProduktCounterViewModel getPcViewModel()
    {
        return pcViewModel;
    }
}

