package com.example.listadeusuarios;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class ElementuaAdapter extends RecyclerView.Adapter<ElementuaAdapter.viewHolder> {

    private ArrayList<Usuario> elementuakList;

    public ElementuaAdapter(ArrayList<Usuario> elementuakList) {
        this.elementuakList = elementuakList;
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public TextView izena;
        public TextView idioma;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txt1);
            izena = itemView.findViewById(R.id.txt2);
            idioma = itemView.findViewById(R.id.txt3);
        }


    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_elementua_adapter, parent, false);
        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Usuario currentElementua = elementuakList.get(position);
        currentElementua.mostrarResultados();
        holder.id.setText(String.valueOf(currentElementua.getId()));
        holder.izena.setText(currentElementua.getNombre());
        holder.idioma.setText(currentElementua.getIdioma());
    }

    @Override
    public int getItemCount() {
        return elementuakList.size();
    }


}