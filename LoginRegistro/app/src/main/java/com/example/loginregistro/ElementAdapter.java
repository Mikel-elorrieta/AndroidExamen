package com.example.loginregistro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ElementAdapter extends RecyclerView.Adapter<ElementAdapter.viewHolder> {
    private ArrayList<Usuario> elementuakList;

    public ElementAdapter(ArrayList<Usuario> elementuakList) {
        this.elementuakList = elementuakList;
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        public TextView Usename;
        public TextView Genero;
        public TextView MayorDeEdad;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            Usename = itemView.findViewById(R.id.txtUsername);
            Genero = itemView.findViewById(R.id.txtGenero);
            MayorDeEdad = itemView.findViewById(R.id.txtMayorDeEdad);
        }


    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_element_adapter, parent, false);
        return new viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Usuario currentElementua = elementuakList.get(position);
        holder.Usename.setText(String.valueOf(currentElementua.getUsername()));
        holder.Genero.setText(currentElementua.getGenero());
                if(currentElementua.getMayorEdad()){
                    holder.MayorDeEdad.setText("MenorDeEdad");

                }else{
                    holder.MayorDeEdad.setText("MayorDeEdad");

                }

    }

    @Override
    public int getItemCount() {
        return elementuakList.size();
    }


}