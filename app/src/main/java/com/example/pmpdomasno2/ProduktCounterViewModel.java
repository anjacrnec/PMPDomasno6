package com.example.pmpdomasno2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProduktCounterViewModel extends AndroidViewModel {

    private ProduktCounterRepository repository;
    private LiveData<List<Produkt>> siteProdukti;


    public ProduktCounterViewModel(@NonNull Application application) {
        super(application);
        repository=new ProduktCounterRepository(application);
        siteProdukti=repository.getSiteProdukti();



    }

    public void insert(Produkt produkt)
    {
        repository.insert(produkt);
    }

    public void update(Produkt produkt)
    {
        repository.update(produkt);
    }

    public void delete(Produkt produkt)
    {
        repository.delete(produkt);
    }

    public void izbrisiSiteProdukti()
    {
        repository.izbrisiSiteProdukti();
    }

    public LiveData<List<Produkt>> getSiteProdukti()
    {
        return siteProdukti;
    }

    public void updateDeafultProdukti(String ime,int id)
    {
        repository.updateDeafultProdukti(ime,id);
    }

    public void updateCounterTempIncrement(int id)
    {
        repository.updateCounterTempIncrement(id);
    }

    public void resetCounterTemp()
    {
        repository.resetCounterTemp();
    }

    public void updateCounter()
    {
        repository.updateCounter();
    }

    public List<Produkt> getListprodukti() throws ExecutionException, InterruptedException {
        return  repository.getListProdukti();
    }

    public int getSumCounterTemp () throws ExecutionException, InterruptedException {
        return  repository.getSumCounterTemp();
    }

}
