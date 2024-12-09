package com.example.cartasmagic;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cartasmagic.Carta;
import com.example.cartasmagic.R;

import java.util.ArrayList;

public class ElementuaAdapter extends RecyclerView.Adapter<ElementuaAdapter.ViewHolder> {

    private ArrayList<Carta> elementuakList;

    public ElementuaAdapter(ArrayList<Carta> elementuakList) {
        this.elementuakList = elementuakList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public TextView nombre;
        public TextView color;
        public TextView tipo;
        public TextView rareza;
        public TextView descripcion;
        public CheckBox favorito;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txtId);
            nombre = itemView.findViewById(R.id.txtNombre);
            color = itemView.findViewById(R.id.txtColor);
            tipo = itemView.findViewById(R.id.txtTipo);
            rareza = itemView.findViewById(R.id.txtRarezaValor);
            descripcion = itemView.findViewById(R.id.txtDescripcion);
            favorito = itemView.findViewById(R.id.chkFavorito);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_elementua_adapter, parent, false); // Asegúrate de que el archivo XML tiene el nombre correcto
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Carta currentCarta = elementuakList.get(position);

        holder.id.setText(String.valueOf(currentCarta.getId()));
        holder.nombre.setText(currentCarta.getNombre());
        holder.color.setText(currentCarta.getColor());
        holder.tipo.setText(currentCarta.getTipo());
        holder.rareza.setText(currentCarta.getRareza());
        holder.descripcion.setText(currentCarta.getDescripcion());
        holder.favorito.setChecked(currentCarta.isFavorito());

        // Escucha de cambios en el CheckBox
        holder.favorito.setOnCheckedChangeListener((buttonView, isChecked) -> {
            currentCarta.setFavorito(isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return elementuakList.size();
    }
}
