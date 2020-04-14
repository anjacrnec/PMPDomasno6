package com.example.pmpdomasno2;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProduktCounterRepository {

    private ProduktDao produktDao;
    private LiveData<List<Produkt>> siteProdukti;
    public static LiveData<List<Produkt>> p;


    public ProduktCounterRepository(Application app)
    {
        ProduktCounterDatabase db=ProduktCounterDatabase.getInstance(app);
        produktDao=db.produktDao();
        siteProdukti=produktDao.getSiteProdukti();

    }

    public void insert(Produkt produkt)
    {
        new InsertProduktAsyncTask(produktDao).execute(produkt);
    }

    public void update(Produkt produkt)
    {
        new UpdateProduktAsyncTask(produktDao).execute(produkt);
    }

    public void delete(Produkt produkt)
    {
        new DeleteProduktAsyncTask(produktDao).execute(produkt);
    }

    public void izbrisiSiteProdukti()
    {
        new DeleteAllProduktAsyncTask(produktDao).execute();
    }

    public LiveData<List<Produkt>> getSiteProdukti()
    {
        return siteProdukti;
    }






    public void updateDeafultProdukti(String ime,int id)
    {
        new UpdateDefaultProduktiASyncTask(produktDao,ime,id).execute();
    }

    public void updateCounterTempIncrement(int id)
    {
        new updateCounterTempIncrementASyncTask(produktDao,id).execute();
    }

    public void resetCounterTemp()
    {
        new ResetCounterTempASyncTask(produktDao).execute();
    }

    public void updateCounter()
    {
        new UpdateCounterASyncTask(produktDao).execute();
    }

    public List<Produkt> getListProdukti() throws ExecutionException, InterruptedException {
        return new GetListProduktiASyncTask(produktDao).execute().get();
    }

    public int getSumCounterTemp() throws ExecutionException, InterruptedException {
        return new GetSumCounterTempASyncTask(produktDao).execute().get();
    }

    public static class GetSumCounterTempASyncTask extends AsyncTask<Void,Void,Integer>
    {
        private ProduktDao produktDao;
        private GetSumCounterTempASyncTask(ProduktDao produktDao)
        {
            this.produktDao=produktDao;
        }
        @Override
        protected Integer doInBackground(Void... voids) {
            return produktDao.getSumCounterTemp();
        }
    }

    public static class GetListProduktiASyncTask extends AsyncTask<Void,Void,List<Produkt>>
    {
        private ProduktDao produktDao;
        private List<Produkt> produkti;
        private GetListProduktiASyncTask(ProduktDao produktDao)
        {
            this.produktDao=produktDao;
        }
        @Override
        protected List<Produkt> doInBackground(Void... voids) {
            return produktDao.getListProdukti();
        }
    }

    public static class UpdateCounterASyncTask extends AsyncTask<Void,Void,Void>
    {
        private ProduktDao produktDao;
        private UpdateCounterASyncTask(ProduktDao produktDao)
        {
            this.produktDao=produktDao;


        }
        @Override
        protected Void doInBackground(Void... voids) {
            produktDao.updateCounter();
            return null;
        }
    }

    public static class ResetCounterTempASyncTask extends AsyncTask<Void,Void,Void>
    {
        private ProduktDao produktDao;
        private ResetCounterTempASyncTask(ProduktDao produktDao)
        {
            this.produktDao=produktDao;


        }
        @Override
        protected Void doInBackground(Void... voids) {
            produktDao.resetCounterTemp();
            return null;
        }
    }



    public static class updateCounterTempIncrementASyncTask extends AsyncTask<Void,Void,Void>
    {
        private ProduktDao produktDao;
        private int id;
        private updateCounterTempIncrementASyncTask(ProduktDao produktDao,int id)
        {
            this.produktDao=produktDao;
            this.id=id;

        }
        @Override
        protected Void doInBackground(Void... voids) {
            produktDao.updateCounterTempIncrement(id);
            return null;
        }
    }


    private static class UpdateDefaultProduktiASyncTask extends AsyncTask<Void,Void,Void>{

        private ProduktDao produktDao;
        private String ime;
        private int id;
        private UpdateDefaultProduktiASyncTask(ProduktDao produktDao,String ime,int id)
        {
            this.produktDao=produktDao;
           this.ime=ime;
           this.id=id;

        }
        @Override
        protected Void doInBackground(Void... voids) {
            produktDao.updateDeafultProdukti(ime,id);
            return null;
        }
    }

    private static class InsertProduktAsyncTask extends AsyncTask<Produkt,Void,Void>{

        private ProduktDao produktDao;

        private InsertProduktAsyncTask(ProduktDao produktDao)
        {
            this.produktDao=produktDao;
        }
        @Override
        protected Void doInBackground(Produkt... produkts) {
            produktDao.insert(produkts[0]);
            return null;
        }
    }


    private static class UpdateProduktAsyncTask extends AsyncTask<Produkt,Void,Void>{

        private ProduktDao produktDao;

        private UpdateProduktAsyncTask(ProduktDao produktDao)
        {
            this.produktDao=produktDao;
        }
        @Override
        protected Void doInBackground(Produkt... produkts) {
            produktDao.update(produkts[0]);
            return null;
        }
    }

    private static class DeleteProduktAsyncTask extends AsyncTask<Produkt,Void,Void>{

        private ProduktDao produktDao;

        private DeleteProduktAsyncTask(ProduktDao produktDao)
        {
            this.produktDao=produktDao;
        }
        @Override
        protected Void doInBackground(Produkt... produkts) {
            produktDao.delete(produkts[0]);
            return null;
        }
    }

    private static class DeleteAllProduktAsyncTask extends AsyncTask<Void,Void,Void> {

        private ProduktDao produktDao;

        private DeleteAllProduktAsyncTask(ProduktDao produktDao) {
            this.produktDao = produktDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            produktDao.izbrisiSiteProdukti();
            return null;
        }
    }

}
