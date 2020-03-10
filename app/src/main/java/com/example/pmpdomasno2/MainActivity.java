package com.example.pmpdomasno2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    ListView lvProdukti;
    ListAdapter adapter;
    ArrayList <Produkt> listaProdukti;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvProdukti = (ListView) findViewById(R.id.lvProdukti);

        Produkt.setProdukti();
        listaProdukti=Produkt.getProdukti();
        try {
            updateListProdukti();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        adapter = new ListAdapter(MainActivity.this, listaProdukti);
        lvProdukti.setAdapter(adapter);


        lvProdukti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                int i= listaProdukti.get(position).getCounter();
                i++;
                listaProdukti.get(position).setCounter(i);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void kreirajNovProdukt(View view) throws FileNotFoundException {
        PrintStream ps=new PrintStream(openFileOutput("novoDodadeniProdukti",MODE_APPEND));
        EditText et=(EditText) findViewById(R.id.tbVnesiIme);
        String ime=et.getText().toString();
        ps.println(ime);
        ps.close();
        Produkt p=new Produkt(ime,0,R.drawable.ic_launcher_foreground);
        listaProdukti.add(p);
        adapter.notifyDataSetChanged();

    }

   public void updateListProdukti() throws FileNotFoundException {
       File file = getFileStreamPath("novoDodadeniProdukti");
       if (file.exists()) {
           Scanner scan = new Scanner(openFileInput("novoDodadeniProdukti"));
           while (scan.hasNext()) {
               String produkt = scan.nextLine();

                   listaProdukti.add(new Produkt(produkt, 0, R.drawable.ic_launcher_foreground));
               }

           }
       }

       public void dodajProdukt()
       {

       }
   }


   /* public void vpisiProduktiDatoteka(View view) throws FileNotFoundException {
        PrintStream ps=new PrintStream(openFileOutput("produkti",MODE_APPEND));
        for(int i=0;i<listaProduktiCount.size();i++)
        {
            int produktCount=Integer.parseInt(listaProduktiCount.get(i));
            if(produktCount>0)
                for(int j=0;j<produktCount;j++)
                    ps.println(listaProduktiIminja.get(i));
        }
        ps.close();
    }

    public void prikaziSiteVneseniProdukti(View view) throws FileNotFoundException {
        Scanner scan=new Scanner(openFileInput("produkti"));
        List<Integer> vkupno=new ArrayList<Integer>();

        int k=0;
        List <String> site=new ArrayList<String>();
        List <Integer> vk=new ArrayList<Integer>();
        while ((scan.hasNext()))
        {
            String line=scan.nextLine();
            {

                site.add(line);
            }
        }

        for(int i=0;i<listaProduktiCount.size();i++)
        {
            for(int j=0;j<site.size();j++)
            {
                if (listaProduktiIminja.get(i).equalsIgnoreCase(site.get(j)))
                {
                    k++;
                }

            }
            vk.add(k);
            k=0;
        }

        for(int i=0;i<listaProduktiCount.size();i++)
            Toast.makeText(MainActivity.this, listaProduktiIminja.get(i)+" "+vk.get(i), Toast.LENGTH_SHORT).show();

        scan.close();
    }*/

