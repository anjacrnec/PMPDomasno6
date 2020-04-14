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
        Tema.setTema(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_kreiraj_nov_proizvod_activity);
        addKreirajNovProizvodFragment();


    }

    public void addKreirajNovProizvodFragment()
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
         Fragment vtorfr=new KreirajNovProduktFragment();
        ft.replace(R.id.kreirajNovProduktConatiner,vtorfr);
        ft.commit();
    }







}
