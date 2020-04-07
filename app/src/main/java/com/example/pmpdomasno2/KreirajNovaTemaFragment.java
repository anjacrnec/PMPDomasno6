package com.example.pmpdomasno2;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

import java.io.FileNotFoundException;


public class KreirajNovaTemaFragment extends Fragment {

    public KreirajNovaTemaFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_kreiraj_nova_tema, container, false);

        final ImageButton b1=(ImageButton) v.findViewById(R.id.btnPrimarna);
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        izberiBoja(b1);
                    }
                });

        final ImageButton b2=(ImageButton) v.findViewById(R.id.btnSekundarna);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                izberiBoja(b2);
            }
        });

        final ImageButton b3=(ImageButton) v.findViewById(R.id.btnTercijalna);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                izberiBoja(b3);
            }
        });
       final EditText et=(EditText)v.findViewById(R.id.txtImeTema);
        Tema.setBojaPaleta(getContext(),b1,b2,b3);

       final Drawable draw1 = b1.getBackground();
       final Drawable draw2 = b2.getBackground();
       final Drawable draw3 = b3.getBackground();

        final int bojaCur1=((ColorDrawable)draw1).getColor();
        final int bojaCur2=((ColorDrawable)draw2).getColor();
        final int bojaCur3=((ColorDrawable)draw3).getColor();

        final ImageButton btnKreirajNovaTema=(ImageButton)v.findViewById(R.id.btnKreirajNovaTema);
        btnKreirajNovaTema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ime=et.getText().toString();

                int boja1=((ColorDrawable)draw1).getColor();
                int boja2=((ColorDrawable)draw2).getColor();
                int boja3=((ColorDrawable)draw3).getColor();

                try {
                    Tema.kreirajNovaTema2(getActivity(),ime,boja1,boja2,boja3);
                    TemaPostaviFragment f=(TemaPostaviFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.idPostaviTemaFragmentContainer);
                    ListTemaAdapter adapter=f.getListTemaAdapter();
                    adapter.notifyDataSetChanged();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                et.setText(getContext().getResources().getString(R.string.ime));
                b1.setBackgroundColor(bojaCur1);
                b2.setBackgroundColor(bojaCur2);
                b3.setBackgroundColor(bojaCur3);
                Toast.makeText(getContext(),getContext().getResources().getString(R.string.uspeshnoKreiranaTema),Toast.LENGTH_SHORT);
            }
        });


        return v;
    }

    public void izberiBoja(final ImageButton b)
    {
        ColorPickerDialogBuilder
                .with(getContext())
                .setTitle("Choose color")
                .initialColor(Color.WHITE)
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {

                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        b.setBackgroundColor(selectedColor);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .showAlphaSlider(false)
                .build()
                .show();
    }
}
