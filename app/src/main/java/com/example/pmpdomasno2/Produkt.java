package com.example.pmpdomasno2;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "table_produkt")
public class Produkt implements Parcelable {



    @PrimaryKey(autoGenerate = true)
    private int id;



    private String ime;

    private int counter;

    private int counterTemp;

    private int slika;


    @Ignore
    private static ArrayList<Produkt> produkti;

    @Ignore
    public Produkt(int id,String ime, int counter, int slika) {
        this.id=id;
        this.ime = ime;
        this.counter = counter;
        this.slika = slika;
    }

    public Produkt(String ime,int counterTemp,  int counter, int slika) {
        this.ime = ime;
        this.counterTemp=counterTemp;
        this.counter = counter;
        this.slika = slika;
    }


    @Ignore
    public Produkt(String ime, int counter) {
        this.ime = ime;
        this.counter = counter;

    }
    public void setId(int id) {
        this.id = id;
    }



    public int getId() {
        return id;
    }
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getCounterTemp() {
        return counterTemp;
    }

    public void setCounterTemp(int counterTemp) {
        this.counterTemp = counterTemp;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getSlika() {
        return slika;
    }

    public void setSlika(int slika) {
        this.slika = slika;
    }

    public static List<Produkt> inicijalizacijaProdukti(Context context)
    {

        Resources resources=context.getResources();
        Produkt banana=new Produkt(resources.getString(R.string.banana),0,0,R.drawable.bananas);
        Produkt leb=new Produkt(resources.getString(R.string.leb),0,0,R.drawable.leb);
        Produkt kafe=new Produkt(resources.getString(R.string.kafe),0,0,R.drawable.kafe);
        Produkt jabolko=new Produkt(resources.getString(R.string.jabolko),0,0,R.drawable.jabolko);
        Produkt  vino=new Produkt(resources.getString(R.string.vino),0,0,R.drawable.vino);

        ArrayList<Produkt> listaProdukti=new ArrayList<Produkt>();
        listaProdukti.add(banana);
        listaProdukti.add(leb);
        listaProdukti.add(kafe);
        listaProdukti.add(jabolko);
        listaProdukti.add(vino);

        return listaProdukti;
    }

    public static ArrayList<Produkt> getProdukti()
    {
        return produkti;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ime);
        dest.writeInt(counter);
        dest.writeInt(slika);
    }

    protected Produkt(Parcel in) {
        this.ime = in.readString();
        this.counter = in.readInt();
        this.slika=in.readInt();
    }

    public static final Creator<Produkt> CREATOR = new Creator<Produkt>(){
        @Override
        public Produkt createFromParcel(Parcel source) {
            return new Produkt(source);
        }

        @Override
        public Produkt[] newArray(int size) {
            return new Produkt[size];
        }
    };
}

