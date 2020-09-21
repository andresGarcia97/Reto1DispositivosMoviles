package com.co.movil.retos.reto2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animales);
        listViewAnimales = findViewById(R.id.listViewAnimales);
        editTextTextBuscarAnimal = findViewById(R.id.editTextTextBuscarAnimal);
        crearListaAnimales();
        buscarAnimal();
    }

    private void crearListaAnimales() {
        animales = new ArrayList<>();
        animales.add(new Animal(R.drawable.conejo, "Conejo", R.drawable.avatar, "Herviboro, que sirven como base de la cadena trofica"));
        animales.add(new Animal(R.drawable.lobo, "Lobo", R.drawable.avatar, "Carnivoro que habita en bosques y montañas en manadas"));
        animales.add(new Animal(R.drawable.mapache, "Mapache", R.drawable.avatar, "Omnivoro, nocturno con malas costumbres de saquear"));
        animales.add(new Animal(R.drawable.zorro, "Zorro", R.drawable.avatar, "Carnivoro, muy inteligente que actua en solitario"));
        animales.add(new Animal(R.drawable.perezozo, "Perezoso", R.drawable.avatar, "Omnivoro, el animal más lento del mundo"));

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

}