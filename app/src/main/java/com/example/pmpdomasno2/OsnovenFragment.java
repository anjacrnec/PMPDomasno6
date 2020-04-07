package com.example.pmpdomasno2;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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


public class OsnovenFragment extends Fragment {

    ListView lvProdukti;
    ArrayList<Produkt> zacuvani;
    static ArrayList<Produkt> momentalnoSelektirani;
   public static ListAdapter adapter;
    String p="";
    public static ArrayList<Produkt> listaProdukti;

    public OsnovenFragment() {
        // Required empty public constructor
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
        lvProdukti=((MainActivity)getActivity()).lvProdukti;
        adapter=((MainActivity)getActivity()).adapter;
        listaProdukti=((MainActivity)getActivity()).listaProdukti;
        lvProdukti = (ListView) v.findViewById(R.id.lvProdukti);




        Produkt.setProdukti(getContext());
        listaProdukti=Produkt.getProdukti();
        try {
            updateListProdukti();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        adapter = new ListAdapter((MainActivity)getActivity(), listaProdukti);
        lvProdukti.setAdapter(adapter);
        if(savedInstanceState!=null)
        {
            momentalnoSelektirani = savedInstanceState.getParcelableArrayList("kluc");
            ListView lv=v.findViewById(R.id.lvProdukti);
            for(int i=0;i<momentalnoSelektirani.size();i++)
            {
                listaProdukti.get(i).setCounter(momentalnoSelektirani.get(i).getCounter());
                adapter.notifyDataSetChanged();
            }
        }
        else
        {
            momentalnoSelektirani=new ArrayList<Produkt>();
           setMomentalnoSelektirani();
        }




        lvProdukti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                int i= listaProdukti.get(position).getCounter();
                i++;
                listaProdukti.get(position).setCounter(i);
                adapter.notifyDataSetChanged();
                Boolean ima=false;

                momentalnoSelektirani.get(position).setCounter(i);



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
                  undoProdukti();
                } catch (FileNotFoundException e) {
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
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
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

                    intent.putParcelableArrayListExtra("listaProduktiIntent", listaProdukti);
                    startActivityForResult(intent, 0);
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

    @Override
    public void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("kluc",momentalnoSelektirani);

    }

    public static ArrayList<Produkt> getListProdukt()
    {
        return listaProdukti;
    }


    public void onActivityResult(int requestCode,int resultCode, Intent intent) {

       super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode==0)
        {

            ArrayList<String> novoDodadeni=intent.getStringArrayListExtra("novoDodadeni");

            for(int i=0;i<novoDodadeni.size();i++)
            {
                listaProdukti.add(new Produkt(novoDodadeni.get(i),novoDodadeni.get(i),0, R.drawable.placeholder));
            }
            adapter.notifyDataSetChanged();
        }

    }

    public void updateListProdukti() throws FileNotFoundException {
       File file = getActivity().getFileStreamPath("novoDodadeniProdukti");
        if (file.exists()) {
            Scanner scan = new Scanner(getActivity().openFileInput("novoDodadeniProdukti"));
            while (scan.hasNext()) {
                String produkt = scan.nextLine();
                listaProdukti.add(new Produkt(produkt,produkt, 0, R.drawable.placeholder));
            }

        }
    }

    public void setMomentalnoSelektirani()
    {
        Produkt p;
        for(int i=0;i<listaProdukti.size();i++) {
            p = listaProdukti.get(i);
            String s=p.getIme();
            int c=p.getCounter();
            momentalnoSelektirani.add(new Produkt(s,c));
        }
    }

    public void dodajProdukt() throws FileNotFoundException {
        Resources res=getResources();
        PrintStream ps=new PrintStream(getActivity().openFileOutput("vkupnoProdukti",getActivity().MODE_APPEND));
        String poraka="";
        int counterCounter=0;
        for(int i=0;i<listaProdukti.size();i++)
        {
            int countKliknato=listaProdukti.get(i).getCounter();
            for(int j=0;j<countKliknato;j++)
            {
                ps.println(listaProdukti.get(i).getKod());

            }
            if(countKliknato!=0) {
                poraka = poraka + "\n" + listaProdukti.get(i).getIme()+" " + res.getString(R.string.kolicna) +" "+ countKliknato;
                listaProdukti.get(i).setCounter(0);
                counterCounter=counterCounter+countKliknato;
            }
            resetListViewProdukti();
            adapter.notifyDataSetChanged();
        }
        ps.close();

        String celaPoraka;
        if (counterCounter==0)
            celaPoraka=res.getString(R.string.nemaDodadeniProdukti);
        else
            celaPoraka=res.getQuantityString(R.plurals.uspeshnoDodadeniProdukti,counterCounter)+poraka;
        Toast.makeText((MainActivity)getActivity(),celaPoraka,Toast.LENGTH_LONG).show();

    }

    public void prikaziIstorijaProdukti() throws FileNotFoundException {
        Resources res=getContext().getResources();
        Scanner scan=new Scanner(getActivity().openFileInput("vkupnoProdukti"));
        ArrayList<String> produktiOdDatoteka=new ArrayList<String>();
        String poraka=res.getString(R.string.istorijaProdukti);
        while(scan.hasNext())
        {
            String produkt=scan.nextLine();
            produktiOdDatoteka.add(produkt);
        }
        for(int i=0;i<listaProdukti.size();i++)
        {
            int k=0;
            for(int j=0;j<produktiOdDatoteka.size();j++)
            {
                if(listaProdukti.get(i).getKod().equalsIgnoreCase(produktiOdDatoteka.get(j)))
                    k++;
            }
            poraka=poraka+"\n"+listaProdukti.get(i).getIme()+" "+res.getString(R.string.kolicna)+" "+k+" "+p;

        }
        Toast.makeText((MainActivity)getActivity(),poraka,Toast.LENGTH_LONG).show();
    }

    public void resetListViewProdukti()
    {
        for(int i=lvProdukti.getFirstVisiblePosition();i<=lvProdukti.getLastVisiblePosition();i++)
        {
            View v=lvProdukti.getChildAt(i-lvProdukti.getFirstVisiblePosition());
            //v.setBackgroundColor(getResources().getColor(R.color.white));
           // TextView t=(TextView)v.findViewById(R.id.txtCounter);
           // t.setTextColor(getResources().getColor(R.color.white));
        }
    }

    public void undoProdukti()
    {


        for(int i=0;i<listaProdukti.size();i++)
        {
            listaProdukti.get(i).setCounter(0);
            momentalnoSelektirani.get(i).setCounter(0);
        }
        resetListViewProdukti();
        adapter.notifyDataSetChanged();

    }

}

