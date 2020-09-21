package com.co.movil.retos.reto2;

import android.os.Bundle;
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
    }

    private void crearListaAnimales() {
        animales = new ArrayList<>();
        animales.add(new Animal(R.drawable.conejo, "Conejo", R.drawable.avatar, "Herviboros que sirven como base de la cadena trofica"));
        animales.add(new Animal(R.drawable.lobo, "Lobo", R.drawable.avatar, "Carnivoro que habita en laderas, bosques y montañas"));
        animales.add(new Animal(R.drawable.mapache, "Mapache", R.drawable.avatar, "Omnivoros, nocturnos"));
        animales.add(new Animal(R.drawable.zorro, "Zorro", R.drawable.avatar, "Carnivoros muy inteligentes"));
        animales.add(new Animal(R.drawable.perezozo, "Perezoso", R.drawable.avatar, "Omnivoros, el animal más lento del mundo"));

        adapterAnimal = new AdapterAnimal(getApplicationContext(), animales);
        listViewAnimales.setAdapter(adapterAnimal);
    }
}