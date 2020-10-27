package com.co.movil.retos.reto1;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.co.movil.retos.R;

import java.util.Calendar;

public class Edad extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView dateInScreen;
    private Calendar currentlyDate;
    private Calendar bornDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentlyDate = Calendar.getInstance();
        setContentView(R.layout.activity_edad);
        dateInScreen = findViewById(R.id.fechaNacimiento);
        findViewById(R.id.fechaNacimiento).setOnClickListener(view -> showDatePicker());
    }

    private void showDatePicker() {
        Calendar minimeYear = Calendar.getInstance();
        Calendar maximeYear = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
                currentlyDate.get(Calendar.YEAR), currentlyDate.get(Calendar.MONTH), currentlyDate.get(Calendar.DAY_OF_MONTH));
        minimeYear.set(Calendar.YEAR, currentlyDate.get(Calendar.YEAR) - 50);
        datePickerDialog.getDatePicker().setMinDate(minimeYear.getTimeInMillis());
        maximeYear.set(Calendar.YEAR, currentlyDate.get(Calendar.YEAR) + 1);
        datePickerDialog.getDatePicker().setMaxDate(maximeYear.getTimeInMillis());
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        bornDate = Calendar.getInstance();
        bornDate.set(Calendar.YEAR, year);
        bornDate.set(Calendar.MONTH, month);
        bornDate.set(Calendar.DAY_OF_MONTH, day);
        String dateSelected = "Fecha:     " + year + " / " + (month + 1) + " / " + day;
        dateInScreen.setText(dateSelected);
    }

    public void calcularEdad(View view) {
        currentlyDate = Calendar.getInstance();
        if (bornDate == null) {
            Toast.makeText(getApplicationContext(), R.string.noSeleccionoFecha, Toast.LENGTH_LONG).show();
        } else if (bornDate.get(Calendar.YEAR) == currentlyDate.get(Calendar.YEAR) &&
                bornDate.get(Calendar.MONTH) == currentlyDate.get(Calendar.MONTH) &&
                bornDate.get(Calendar.DAY_OF_MONTH) == currentlyDate.get(Calendar.DAY_OF_MONTH)) {
            Toast.makeText(getApplicationContext(), R.string.fechaIgual, Toast.LENGTH_LONG).show();
        } else if (bornDate.get(Calendar.YEAR) >= currentlyDate.get(Calendar.YEAR) &&
                (bornDate.get(Calendar.MONTH) >= currentlyDate.get(Calendar.MONTH) && bornDate.get(Calendar.DAY_OF_MONTH) >= currentlyDate.get(Calendar.DAY_OF_MONTH))) {
            Toast.makeText(getApplicationContext(), R.string.fechaFutura, Toast.LENGTH_LONG).show();
        } else if (bornDate.get(Calendar.YEAR) <= currentlyDate.get(Calendar.YEAR) &&
                bornDate.get(Calendar.MONTH) <= currentlyDate.get(Calendar.MONTH)) {
            int years = currentlyDate.get(Calendar.YEAR) - bornDate.get(Calendar.YEAR);
            int months = currentlyDate.get(Calendar.MONTH) - bornDate.get(Calendar.MONTH);
            int days = Math.abs(currentlyDate.get(Calendar.DAY_OF_MONTH) - bornDate.get(Calendar.DAY_OF_MONTH));
            String age = "Usted tiene: " + years + " AÑOS " + months + " MESES " + days + " DIAS ";
            Toast.makeText(getApplicationContext(), age, Toast.LENGTH_LONG).show();
        }
    }
}