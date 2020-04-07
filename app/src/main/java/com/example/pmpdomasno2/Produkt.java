package com.example.pmpdomasno2;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Produkt implements Parcelable {



    private int id;
    private String kod;
    private String ime;
    private int counter;
    private int slika;
    private static ArrayList<Produkt> produkti;

    public Produkt(String kod,String ime, int counter, int slika) {
        this.id=id;
        this.kod=kod;
        this.ime = ime;
        this.counter = counter;
        this.slika = slika;
    }

    public Produkt(String ime, int counter ) {
        this.ime = ime;
        this.counter = counter;

    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
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

    public static void setProdukti(Context context)
    {
        Resources resources=context.getResources();
        Produkt banana=new Produkt("banana1",resources.getString(R.string.banana),0,R.drawable.bananas);
        Produkt leb=new Produkt("leb1",resources.getString(R.string.leb),0,R.drawable.leb);
        Produkt kafe=new Produkt("kafe1",resources.getString(R.string.kafe),0,R.drawable.kafe);
        Produkt jabolko=new Produkt("jabolko1",resources.getString(R.string.jabolko),0,R.drawable.jabolko);
        Produkt  vino=new Produkt("vino1",resources.getString(R.string.vino),0,R.drawable.vino);

        ArrayList<Produkt> listaProdukti=new ArrayList<Produkt>();
        listaProdukti.add(banana);
        listaProdukti.add(leb);
        listaProdukti.add(kafe);
        listaProdukti.add(jabolko);
        listaProdukti.add(vino);

        produkti=listaProdukti;
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
