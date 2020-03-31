package com.example.pmpdomasno2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TemaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tema.setTema(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.activity_tema);
        addTemaPostaviFragment();
        addKreirajSvojaTemaFragment();
    }

    public void addTemaPostaviFragment()
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        TemaPostaviFragment f=new TemaPostaviFragment();
        ft.replace(R.id.idPostaviTemaFragmentContainer,f,"postaviTema");
        ft.commit();
    }

    public void addKreirajSvojaTemaFragment()
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        KreirajNovaTemaFragment f=new KreirajNovaTemaFragment();
        ft.replace(R.id.idKreirajNovaTemaFragmentContainer,f,"kreirajTema");
        ft.commit();
    }
}
