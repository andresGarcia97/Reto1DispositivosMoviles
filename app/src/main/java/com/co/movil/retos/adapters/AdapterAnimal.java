package com.co.movil.retos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.co.movil.retos.R;
import com.co.movil.retos.clases.Animal;

import java.util.ArrayList;
import java.util.List;

public class AdapterAnimal extends BaseAdapter {

    private List<Animal> listaAnimalesOut;
    private List<Animal> listaAnimalesIn;
    private final LayoutInflater inflater;

    public AdapterAnimal(Context context, List<Animal> animales) {
        super();
        listaAnimalesOut = animales;
        listaAnimalesIn = animales;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listaAnimalesOut.size();
    }

    @Override
    public Animal getItem(int i) {
        return listaAnimalesOut.get(i);
    }

    public Filter getFilter() {

        Filter filter = new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listaAnimalesOut = (List<Animal>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Animal> FilteredArrList = new ArrayList<>();
                if (listaAnimalesIn == null) {
                    listaAnimalesIn = new ArrayList<>(listaAnimalesOut);
                }
                if (constraint == null || constraint.length() == 0) {
                    results.count = listaAnimalesIn.size();
                    results.values = listaAnimalesIn;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < listaAnimalesIn.size(); i++) {
                        if (listaAnimalesIn.get(i).getNombre().toLowerCase().contains(constraint.toString())) {
                            FilteredArrList.add(listaAnimalesIn.get(i));
                        }
                    }
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.layout_animal_item, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.imageViewAnimal.setImageResource(listaAnimalesOut.get(i).getImagen());
        holder.textViewNombreAnimal.setText(listaAnimalesOut.get(i).getNombre());
        holder.textViewDescripcionAnimal.setText(listaAnimalesOut.get(i).getDescripcion());
        return view;
    }

    class ViewHolder {
        ImageView imageViewAnimal;
        TextView textViewNombreAnimal;
        TextView textViewDescripcionAnimal;

        public ViewHolder(View view) {
            super();
            imageViewAnimal = view.findViewById(R.id.imageViewAnimal);
            textViewNombreAnimal = view.findViewById(R.id.textViewNombreAnimal);
            textViewDescripcionAnimal = view.findViewById(R.id.textViewDescripcionAnimal);
        }
    }
}
