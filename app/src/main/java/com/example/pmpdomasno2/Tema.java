package com.example.pmpdomasno2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.media.TimedText;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.internal.ContextUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Scanner;

public class Tema {



    private int id;
    private String imeTema;
    private int primary;
    private int primaryDark;
    private int secondary;



    private Boolean checked;
    static ArrayList<Tema> temi;

    public int getId() { return id; }

    public String getImeTema() {
        return imeTema;
    }

    public int getPrimary() {
        return primary;
    }

    public int getPrimaryDark() {
        return primaryDark;
    }

    public int getSecondary() {
        return secondary;
    }

    public Boolean getChecked() {return  checked;}

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Tema(int id,String imeTema,int primary,int primaryDark,int secondary)
    {
        this.id=id;
        this.imeTema=imeTema;
        this.primary=primary;
        this.primaryDark=primaryDark;
        this.secondary=secondary;
    }


    public Tema(int id,String imeTema,int primary,int primaryDark,int secondary,Boolean checked)
    {
        this.id=id;
        this.imeTema=imeTema;
        this.primary=primary;
        this.primaryDark=primaryDark;
        this.secondary=secondary;
        this.checked=checked;
    }




    public static int odrediTema(Context c)
    {
        SharedPreferences sharedPref = c.getSharedPreferences("Temi",c.MODE_PRIVATE);
        int t=sharedPref.getInt("tema",1);
        return t;
    }

    public static void setTemaPrefs(Activity activity,int tema)
    {
        SharedPreferences pref=activity.getSharedPreferences("Temi",activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("tema",tema);
        editor.commit();
    }


    public static void setTema(Activity activity)
    {
        SharedPreferences sharedPref = activity.getSharedPreferences("Temi",activity.MODE_PRIVATE);
        int t=sharedPref.getInt("tema",1);
        if(t<=4) {
            if (t == 1)
                activity.setTheme(R.style.Osnovna);
            else if (t == 2)
                activity.setTheme(R.style.Magenta);
            else if (t == 3)
                activity.setTheme(R.style.Tropical);
            else if (t == 4)
                activity.setTheme(R.style.Lilac);
        }
        else
        {
            int i=getMomentalnaTemaIndex(t);
            activity.getWindow().setStatusBarColor(temi.get(i).getPrimaryDark());
            temi.get(i).setChecked(true);
            ((AppCompatActivity)activity).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(temi.get(i).getPrimary()));
        }
    }

    public static void setTemaSliki(Context c, int t, View iv)
    {
            int j=getMomentalnaTemaIndex(t);
            iv.setBackgroundColor((temi.get(j).getSecondary()));
            iv.setBackgroundTintList(ColorStateList.valueOf(temi.get(j).getSecondary()));

    }


    public static int getMomentalnaTemaIndex(int t)
    {
        int j=1;
        for(int i=0;i<temi.size();i++)
        {
            if(temi.get(i).getId()==t) {
                j=i;
                break;
            }

        }
        return j;
    }

    public static void checkTemiSporedMomentalna(Context context,int id,Boolean checkedVal1,Boolean checkedVal2) {
        int t = Tema.odrediTema(context);
        int j = Tema.getMomentalnaTemaIndex(t);
        temi.get(j).setChecked(checkedVal1);

        for (int i = 0; i < temi.size(); i++) {
            if (temi.get(i).getId() == id) {
                temi.get(i).setChecked(checkedVal2);
                break;
            }
        }
    }



    public static void setOsnovniTemi(Context context)
    {
        Resources r=context.getResources();
        temi=new ArrayList<Tema>();
        temi.add(new Tema(1,"Osnovna",r.getColor(R.color.colorPrimary),r.getColor(R.color.colorPrimaryDark),r.getColor(R.color.slikaPozadinaAccent)));
        temi.add(new Tema(2,"Magenta",r.getColor(R.color.magenta),r.getColor(R.color.magentaDark), r.getColor(R.color.mediumGrey)));
        temi.add(new Tema(3,"Tropical",r.getColor(R.color.turquoiseDark),r.getColor(R.color.colorPrimaryDark),r.getColor(R.color.turquoiseLight)));
        temi.add(new Tema(4,"Lilac",r.getColor(R.color.lilac),r.getColor(R.color.lilacDark),r.getColor(R.color.yellowLight)));

        int t=odrediTema(context);
        for(int i=0;i<temi.size();i++)
        {
           if(temi.get(i).getId()==t)
               temi.get(i).setChecked(true);
           else
               temi.get(i).setChecked(false);
        }
    }

    public static void restartTema(Activity activity)
    {

        Intent intent = new Intent(activity, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);

    }

    public static void kreirajNovaTema(Activity activity,String imeTema,int boja1,int boja2,int boja3) throws FileNotFoundException {
        PrintStream ps = new PrintStream(activity.openFileOutput("novoDodadeniTemi", activity.MODE_APPEND));
        int id=temi.get(temi.size()-1).getId()+1;
        ps.println(id);
        ps.println(imeTema);
        ps.println(boja1);
        ps.println(boja2);
        ps.println(boja3);
        Tema tema=new Tema(id,imeTema,boja1,boja2,boja3,false);
        temi.add(tema);
        ps.close();
    }

    public static void kreirajNovaTema2(Activity activity,String imeTema,int boja1,int boja2,int boja3) throws FileNotFoundException {
        PrintStream ps = new PrintStream(activity.openFileOutput("novoDodadeniTemi", activity.MODE_APPEND));
        int id=temi.get(temi.size()-1).getId()+1;
        String r=id+" "+imeTema+" "+boja1+" "+boja2+" "+boja3+" false";
        ps.println(r);
        Tema tema=new Tema(id,imeTema,boja1,boja2,boja3,false);
        temi.add(tema);
        ps.close();
    }

    public static void updateTemiLista(Activity activity) throws FileNotFoundException {
        File file = activity.getFileStreamPath("novoDodadeniTemi");
        if (file.exists()) {
            Scanner scan = new Scanner(activity.openFileInput("novoDodadeniTemi"));
            ArrayList<String> temaStr=new ArrayList<String>();
            while (scan.hasNext()) {
                String redStr=scan.nextLine().toString();
                char[] red=redStr.toCharArray();
                String s="";
                Log.d("redot",redStr);
                for(int i=0;i<red.length;i++)
                {
                    if(!Character.isWhitespace(red[i]) && i!=red.length-1)
                    {
                        s=s+red[i];
                    }
                    else
                    {
                        Log.d("proba",s);
                        temaStr.add(s);
                        s="";
                    }
                }

                int id=Integer.parseInt(temaStr.get(0));
                String ime=temaStr.get(1);
                int boja1=Integer.parseInt(temaStr.get(2));
                int boja2=Integer.parseInt(temaStr.get(3));
                int boja3=Integer.parseInt(temaStr.get(4));
                boolean checked=Boolean.valueOf(temaStr.get(5));
                temi.add(new Tema(id,ime,boja1,boja2,boja3,checked));
                temaStr.clear();
            }
    }

}
    public static void setBojaPaleta(Context context,ImageButton btn1,ImageButton btn2, ImageButton btn3)
    {
        int t=odrediTema(context);
        int i= getMomentalnaTemaIndex(t);
        btn1.setBackgroundColor(temi.get(i).getPrimary());
        btn2.setBackgroundColor(temi.get(i).getPrimaryDark());
        btn3.setBackgroundColor(temi.get(i).getSecondary());
    }
}
