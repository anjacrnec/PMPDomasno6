package com.example.pmpdomasno2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProduktDao {

    @Insert
    void insert(Produkt produkt);

    @Update
    void update(Produkt produkt);

    @Delete
    void delete(Produkt produkt);

    @Query("DELETE FROM table_produkt")
    void izbrisiSiteProdukti();

    @Query("SELECT * FROM table_produkt")
    LiveData<List<Produkt>> getSiteProdukti();

    @Query("UPDATE table_produkt SET ime=:ime where id=:id")
    void updateDeafultProdukti(String ime,int id);

    @Query("UPDATE table_produkt SET counterTemp=counterTemp+1 where id=:id")
    void updateCounterTempIncrement(int id);

    @Query("UPDATE table_produkt SET counterTemp=0")
    void resetCounterTemp();

    @Query("SELECT SUM(counterTemp) FROM table_produkt")
    int getSumCounterTemp();

    @Query("UPDATE table_produkt SET counter=counter+counterTemp")
    void updateCounter();

    @Query ("SELECT * FROM table_produkt")
     List<Produkt> getListProdukti();


}
