package com.example.pmpdomasno2;

import java.util.ArrayList;
import java.util.List;

public class Produkt {

    private String ime;
    private int counter;
    private int slika;
    private static ArrayList<Produkt> produkti;

    public Produkt(String ime, int counter, int slika) {
        this.ime = ime;
        this.counter = counter;
        this.slika = slika;
    }

    public Produkt(String ime, int counter ) {
        this.ime = ime;
        this.counter = counter;

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

    public static void setProdukti()
    {
        Produkt banana=new Produkt("Banana",0,R.drawable.bananas);
        Produkt leb=new Produkt("Leb",0,R.drawable.leb);
        Produkt kafe=new Produkt("Kafe",0,R.drawable.kafe);
        Produkt jabolko=new Produkt("jabolko",0,R.drawable.jabolko);
        Produkt  vino=new Produkt("vino",0,R.drawable.vino);

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



}
