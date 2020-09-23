package com.co.movil.retos.reto2;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.co.movil.retos.R;
import com.co.movil.retos.adapters.AdapterAnimal;
import com.co.movil.retos.clases.Animal;

import java.util.ArrayList;
import java.util.List;

public class Animales extends AppCompatActivity {

    public ListView listViewAnimales;
    public EditText editTextTextBuscarAnimal;
    private AdapterAnimal adapterAnimal;
    public List<Animal> animales;
    private MediaPlayer mp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animales);
        listViewAnimales = findViewById(R.id.listViewAnimales);
        editTextTextBuscarAnimal = findViewById(R.id.editTextTextBuscarAnimal);
        mp = null;
        crearListaAnimales();
        buscarAnimal();
        reproducirSonido();
    }

    private void crearListaAnimales() {
        animales = new ArrayList<>();
        animales.add(new Animal(R.drawable.conejo, "conejo", R.raw.conejo, "Herviboro, que sirven como base de la cadena trofica"));
        animales.add(new Animal(R.drawable.lobo, "lobo", R.raw.lobo, "Carnivoro que habita en bosques y montañas en manadas"));
        animales.add(new Animal(R.drawable.mapache, "Mapache", R.raw.mapache, "Omnivoro, nocturno con malas costumbres de saquear"));
        animales.add(new Animal(R.drawable.zorro, "Zorro", R.raw.zorro, "Carnivoro, muy inteligente que actua en solitario"));
        animales.add(new Animal(R.drawable.perro, "perro", R.raw.perro, "El perro es un animal mamífero y cuadrúpedo que convive con el hombre como una mascota."));

        adapterAnimal = new AdapterAnimal(getApplicationContext(), animales);
        listViewAnimales.setAdapter(adapterAnimal);
    }

    private void buscarAnimal() {
        editTextTextBuscarAnimal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapterAnimal.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void reproducirSonido() {

        listViewAnimales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(mp !=null){
                    mp.stop();
                }
                mp = MediaPlayer.create(getApplicationContext(), animales.get(i).getSonido());
                mp.start();
            }

        });



    }

}