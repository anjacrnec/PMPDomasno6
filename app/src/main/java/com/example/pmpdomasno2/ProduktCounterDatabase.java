package com.example.pmpdomasno2;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

@Database(entities = Produkt.class,version = 1)
public abstract class ProduktCounterDatabase extends RoomDatabase {

    private static  ProduktCounterDatabase instance;
    private static Context con;
    public abstract ProduktDao produktDao();

    public static synchronized ProduktCounterDatabase getInstance(Context context)
    {
        if(instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(),ProduktCounterDatabase.class,"produkt_counter_bp")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        con=context;
        return instance;

    }

    private static RoomDatabase.Callback roomCallback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new incijalizirajDBASyncTask(instance).execute();
        }
    };

    private static class incijalizirajDBASyncTask extends AsyncTask<Void,Void,Void>{

        private ProduktDao produktDao;
        private incijalizirajDBASyncTask(ProduktCounterDatabase db)
        {
            produktDao=db.produktDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            List <Produkt> produkti=Produkt.inicijalizacijaProdukti(con);
            for(int i=0;i<produkti.size();i++)
            {
                produktDao.insert(produkti.get(i));
            }
            return null;
        }
    }

}
