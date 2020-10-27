package com.co.movil.retos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.co.movil.retos.R;
import com.co.movil.retos.clases.Vehiculo;
import com.co.movil.retos.util.DateUtil;

import java.util.List;

public class AdapterVehiculo extends BaseAdapter {

    private List<Vehiculo> listaVehiculosOut;
    private List<Vehiculo> listaVehiculosIn;
    final private LayoutInflater inflater;

    public AdapterVehiculo(Context context, List<Vehiculo> vehiculos) {
        super();
        listaVehiculosOut = vehiculos;
        listaVehiculosIn = vehiculos;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listaVehiculosOut.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AdapterVehiculo.ViewHolder holder;
        if (view != null) {
            holder = (AdapterVehiculo.ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.layout_lista_vehiculos, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.textViewPlaca.setText(listaVehiculosOut.get(i).getPlaca());
        holder.textViewFechaEntrada.setText(DateUtil.convertDateToString(listaVehiculosOut.get(i).getFechaEntrada()));
        holder.textViewValor.setText(String.valueOf(listaVehiculosOut.get(i).getMontoCalculado()));
        return view;
    }

    class ViewHolder {
        TextView textViewPlaca;
        TextView textViewFechaEntrada;
        TextView textViewValor;

        public ViewHolder(View view) {
            super();
            textViewPlaca = view.findViewById(R.id.textViewPlaca);
            textViewFechaEntrada = view.findViewById(R.id.textViewFechaEntrada);
            textViewValor = view.findViewById(R.id.textViewValor);
        }
    }
}
