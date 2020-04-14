package com.example.pmpdomasno2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    ListView lvProdukti;
    ListAdapter adapter;
    String p="";
    public static ArrayList<Produkt> listaProdukti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Jazik.setJazici(getBaseContext());
        int j=Jazik.odrediJazik(getBaseContext());
        Jazik.smeniJazik(getBaseContext(),this,Jazik.jazici.get(j).getJazikKod(),Jazik.jazici.get(j).getDrzavaKod());

        Tema.setOsnovniTemi(this);
        try {
            Tema.updateTemiLista(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Tema.setTema(this);



        setContentView(R.layout.activity_main);
        if(savedInstanceState==null) {
            addOsnovenFragment();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

       if (id == R.id.btnSettings) {

           Intent intent=new Intent(MainActivity.this,SettingsActivity.class);
           startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }



    public void addKreirajNovProizvodFragment()
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        KreirajNovProduktFragment vtorfr=new KreirajNovProduktFragment();
        ft.replace(R.id.kreirajNovProduktConatiner,vtorfr,"TAG1");
        ft.commit();
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent intent) {

      super.onActivityResult(requestCode, resultCode, intent);
       /* if(requestCode==0)
        {
            ArrayList<String> novoDodadeni=intent.getStringArrayListExtra("novoDodadeni");

            for(int i=0;i<novoDodadeni.size();i++)
            {
                listaProdukti.add(new Produkt(novoDodadeni.get(i),0, R.drawable.placeholder));
            }
            adapter.notifyDataSetChanged();
        }*/
    }

    public void addOsnovenFragment()
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        OsnovenFragment prvfr=new OsnovenFragment();
        ft.replace(R.id.osnovenFragmentContainer,prvfr);
        ft.commit();
    }



   }


