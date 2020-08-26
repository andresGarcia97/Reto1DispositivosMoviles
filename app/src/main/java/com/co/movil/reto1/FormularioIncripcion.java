package com.co.movil.reto1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FormularioIncripcion extends AppCompatActivity {

    private EditText nombre;
    private EditText apellido;
    private EditText telefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_incripcion);
    }

    private void alerta(int mensajeToast) {
        Toast.makeText(getApplicationContext(),mensajeToast,Toast.LENGTH_SHORT).show();
    }

    public void verificarDatos(View view) {
        nombre = findViewById(R.id.editTextNombre);
        apellido = findViewById(R.id.editTextApellido);
        telefono = findViewById(R.id.editTextPhone);

        String nombreValor = nombre.getText().toString();
        String apellidoValor = apellido.getText().toString();
        String telefonoValor = telefono.getText().toString();

        if(nombreValor.isEmpty()) {
            alerta(R.string.faltaNombre);
        }
        if(apellidoValor.isEmpty()) {
            alerta(R.string.faltaApellido);
        }
        if(telefonoValor.isEmpty()) {
            alerta(R.string.faltaTelefono);
        }
        else if (!nombreValor.isEmpty() && !apellidoValor.isEmpty() && !telefonoValor.isEmpty()){
            String informacion = "Su nombre completo es: "+nombreValor+" "+apellidoValor+" Y su telefono es: "+telefonoValor;
            Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_SHORT).show();
        }
    }
}