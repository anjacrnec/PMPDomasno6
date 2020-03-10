package com.example.pmpdomasno2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    ListView lvProdukti;
    ListAdapter adapter;
    ArrayList <Produkt> listaProdukti;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvProdukti = (ListView) findViewById(R.id.lvProdukti);

        Produkt.setProdukti();
        listaProdukti=Produkt.getProdukti();
        try {
            updateListProdukti();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        adapter = new ListAdapter(MainActivity.this, listaProdukti);
        lvProdukti.setAdapter(adapter);

        lvProdukti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                int i= listaProdukti.get(position).getCounter();
                i++;
                listaProdukti.get(position).setCounter(i);
                TextView tv=(TextView) view.findViewById(R.id.txtCounter);
                tv.setTextColor(getResources().getColor(R.color.slikaPozadinaAccent));
                view.setBackgroundColor(getResources().getColor(R.color.lime));
                adapter.notifyDataSetChanged();


            }
        });


    }

    public void kreirajNovProdukt(View view) throws FileNotFoundException {
        PrintStream ps=new PrintStream(openFileOutput("novoDodadeniProdukti",MODE_APPEND));
        EditText et=(EditText) findViewById(R.id.tbVnesiIme);
        String ime=et.getText().toString();
        ps.println(ime);
        ps.close();
        Produkt p=new Produkt(ime,0,R.drawable.placeholder);
        listaProdukti.add(p);
        et.setText("");
        adapter.notifyDataSetChanged();

    }

   public void updateListProdukti() throws FileNotFoundException {
       File file = getFileStreamPath("novoDodadeniProdukti");
       if (file.exists()) {
           Scanner scan = new Scanner(openFileInput("novoDodadeniProdukti"));
           while (scan.hasNext()) {
               String produkt = scan.nextLine();

                   listaProdukti.add(new Produkt(produkt, 0, R.drawable.placeholder));
               }

           }
       }

       public void dodajProdukt(View view) throws FileNotFoundException {
           PrintStream ps=new PrintStream(openFileOutput("vkupnoProdukti",MODE_APPEND));
           String poraka="Uspeshno dodadeni proizvodi: ";
           for(int i=0;i<listaProdukti.size();i++)
           {
               int countKliknato=listaProdukti.get(i).getCounter();
               for(int j=0;j<countKliknato;j++)
               {
                   ps.println(listaProdukti.get(i).getIme());

               }
               if(countKliknato!=0) {
                   poraka = poraka + "\n" + listaProdukti.get(i).getIme() + " kol: " + countKliknato;
                   listaProdukti.get(i).setCounter(0);
               }
               resetListViewProdukti();
               adapter.notifyDataSetChanged();
           }
           ps.close();

           Toast.makeText(MainActivity.this,poraka,Toast.LENGTH_LONG).show();

       }

       public void prikaziIstorijaProdukti(View view) throws FileNotFoundException {
           Scanner scan=new Scanner(openFileInput("vkupnoProdukti"));
           ArrayList<String> produktiOdDatoteka=new ArrayList<String>();
           String poraka="Istorija na prodadeni produkti: ";
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
                   if(listaProdukti.get(i).getIme().equalsIgnoreCase(produktiOdDatoteka.get(j)))
                       k++;
               }
               poraka=poraka+"\n"+listaProdukti.get(i).getIme()+" kol: "+k;

           }
           Toast.makeText(MainActivity.this,poraka,Toast.LENGTH_LONG).show();
       }

       public void resetListViewProdukti()
       {
           for(int i=lvProdukti.getFirstVisiblePosition();i<=lvProdukti.getLastVisiblePosition();i++)
           {
               View v=lvProdukti.getChildAt(i-lvProdukti.getFirstVisiblePosition());
               v.setBackgroundColor(getResources().getColor(R.color.white));
               TextView t=(TextView)v.findViewById(R.id.txtCounter);
               t.setTextColor(getResources().getColor(R.color.white));
           }
       }

       public void undoProdukti(View view)
       {

           TextView tv=(TextView)findViewById(R.id.txtCounter);
           //tv.setTextColor(getResources().getColor(R.color.white));
           for(int i=0;i<listaProdukti.size();i++)
           {
               listaProdukti.get(i).setCounter(0);
           }
           resetListViewProdukti();
           adapter.notifyDataSetChanged();

       }

   }


