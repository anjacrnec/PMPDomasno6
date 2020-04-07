package com.example.pmpdomasno2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Locale;

public class Jazik {

    private int id;
    private String jazikKod;
    private String  drzavaKod;
    private String ime;
    private int zname;
    static ArrayList<Jazik> jazici;

    private Boolean checked;

    public String getJazikKod() {
        return jazikKod;
    }

    public void setJazikKod(String jazikKod) {
        this.jazikKod = jazikKod;
    }

    public String getDrzavaKod() {
        return drzavaKod;
    }

    public void setDrzavaKod(String drzavaKod) {
        this.drzavaKod = drzavaKod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getZname() {
        return zname;
    }

    public void setZname(int zname) {
        this.zname = zname;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Jazik(int id, String jazikKod,String drzavaKod,String ime, int zname, Boolean checked)
    {
        this.id=id;
        this.ime=ime;
        this.drzavaKod=drzavaKod;
        this.jazikKod=jazikKod;
        this.zname=zname;
        this.checked=checked;
    }

    public Jazik(int id, String jazikKod,String drzavaKod,String ime, int zname) {
        this.id = id;
        this.ime = ime;
        this.drzavaKod = drzavaKod;
        this.jazikKod = jazikKod;
        this.zname = zname;
    }

    public static int odrediJazik(Context c)
    {
        SharedPreferences sharedPref = c.getSharedPreferences("Jazici",c.MODE_PRIVATE);
        int j=sharedPref.getInt("jazik",1);
        return j;
    }

    public static void setJazikPrefs(Activity activity,int j)
    {
        SharedPreferences pref=activity.getSharedPreferences("Jazici",activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("jazik",j);
        editor.commit();
    }


    public static Jazik getJazikPoID(int j)
    {
        return jazici.get(j-1);
    }

    public static void setJazici(Context context)
    {
        jazici=new ArrayList<Jazik>();
        jazici.add(new Jazik(1,"en","US","English (US)",R.drawable.us_zname));
        jazici.add(new Jazik(2,"en","GB","English (UK)",R.drawable.uk_zname));
        jazici.add(new Jazik(3,"mk","MK","Македонски (МК)",R.drawable.mkd_zname));
        int j=odrediJazik(context);
        for(int i=0;i<jazici.size();i++)
        {
            if(j==i)
                jazici.get(i).setChecked(true);
            else
                jazici.get(i).setChecked(false);
        }
    }

    public static void smeniJazik(Context context, Activity activity,String jazik, String drzava)
    {
        Locale locale;
        locale=new Locale(jazik,drzava);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,context.getResources().getDisplayMetrics());

    }

    public static void restartSmenaJazik(Activity activity)
    {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.finish();
        activity.startActivity(intent);
    }




}
