package com.example.pmpdomasno2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class KreirajNovProizvodActivity extends AppCompatActivity {
    static ArrayList<Produkt> listaProdukti;
   static ArrayList<String> listaProduktiIminja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_kreiraj_nov_proizvod_activity);
        Intent intent=getIntent();
        listaProduktiIminja=new ArrayList<String>();
        listaProdukti= intent.getParcelableArrayListExtra("listaProduktiIntent");
        for(int i=0;i<listaProdukti.size();i++)
        {
            listaProduktiIminja.add(listaProdukti.get(i).getIme());
        }

        addKreirajNovProizvodFragment();
        /*Intent intent=getIntent();
        listaProdukti= intent.getParcelableArrayListExtra("listaProduktiIntent");
        novoDodadeni=new ArrayList<String>();
        listaProduktiIminja=new ArrayList<String>();
        for(int i=0;i<listaProdukti.size();i++)
        {
            listaProduktiIminja.add(listaProdukti.get(i).getIme());
        }
        lvProduktiKratko=(ListView) findViewById(R.id.lvProduktiItems);
        adapterKratko = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaProduktiIminja);
        lvProduktiKratko.setAdapter(adapterKratko);
        lvProduktiKratko.setVisibility(View.INVISIBLE);
        proveriLvProduktiKratko=false;*/

    }

    public void addKreirajNovProizvodFragment()
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
         Fragment vtorfr=new KreirajNovProduktFragment();
        ft.replace(R.id.kreirajNovProduktConatiner,vtorfr);
        ft.commit();
    }



    public static ArrayList<String> getListaIminja()
    {
        return listaProduktiIminja;
    }

    public static ArrayList<Produkt> getListaProdukt()
    {
        return listaProdukti;
    }

   /* public void kreirajNovProdukt(View view) throws FileNotFoundException {
        daliValiden=true;
        porakaValidnost="";
        proveriValidnostNanNovProdukt();
        if(daliValiden) {
            PrintStream ps = new PrintStream(openFileOutput("novoDodadeniProdukti", MODE_APPEND));
            EditText et = (EditText) findViewById(R.id.tbNovProdukt);
            String ime = et.getText().toString();
            ps.println(ime);
            ps.close();
            Produkt p = new Produkt(ime, 0, R.drawable.placeholder);
            listaProdukti.add(p);
            listaProduktiIminja.add(ime);
            novoDodadeni.add(ime);
            adapterKratko.notifyDataSetChanged();
            et.setText("");
            String poraka = "Uspehsno dodaden produktot " + ime;
            Toast.makeText(this, poraka+" "+porakaValidnost, Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,porakaValidnost,Toast.LENGTH_SHORT).show();
        }

    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
       // listaProdukti.add(new Produkt("proba",0,R.drawable.placeholder));
       // intent.putParcelableArrayListExtra("proba",listaProdukti);
       // setResult(0,intent);

        intent.putStringArrayListExtra("novoDodadeni",novoDodadeni);
        setResult(0,intent);
        finish();

        return true;
    }

    public void proveriValidnostNanNovProdukt()
    {

        EditText et=(EditText) findViewById(R.id.tbNovProdukt);
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

    public void prikaziLista(View view)
    {
         if(proveriLvProduktiKratko)
         {
             lvProduktiKratko.setVisibility(View.INVISIBLE);
             proveriLvProduktiKratko=false;
         }
         else
         {
             lvProduktiKratko.setVisibility(View.VISIBLE);
             proveriLvProduktiKratko=true;
         }

    }


*/



}
