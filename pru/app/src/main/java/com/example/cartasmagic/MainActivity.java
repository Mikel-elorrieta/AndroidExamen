package com.example.cartasmagic;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.parceler.Parcels;

import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity {
        private ArrayList<Carta> elementuak = new ArrayList<>();  // Inicializada correctamente
        private ArrayList<Carta> elementuakFilt = new ArrayList<>();  // Inicializada correctamente
        private ElementuaAdapter adapter;  // Adaptador para RecyclerView
        private RecyclerView recyclerView;  // Referencia a RecyclerView
        private final ActivityResultLauncher<Intent> elementuaGehituLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Carta cartaActualizada = Parcels.unwrap(result.getData().getParcelableExtra("carta_actualizada"));

                        if (cartaActualizada != null) {
                            // Buscar y actualizar la carta en la lista
                            boolean cartaEncontrada = false;
                            for (int i = 0; i < elementuak.size(); i++) {
                                System.out.println(elementuak.get(i).getId());
                                System.out.println(cartaActualizada.getId());
                                if (elementuak.get(i).getId().equals(cartaActualizada.getId())) {
                                    elementuak.set(i, cartaActualizada);
                                    cartaEncontrada = true;
                                    break;
                                }
                            }

                            // Si la carta no fue encontrada, la agregamos a la lista
                            if (!cartaEncontrada) {
                                elementuak.add(cartaActualizada);
                            }

                            // Notificar al adaptador que los datos han cambiado
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main);

            // Inicialización de RecyclerView y adaptador
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new ElementuaAdapter(elementuak);
            recyclerView.setAdapter(adapter);

            // Agregar cartas predeterminadas
            elementuak.add(new Carta("1", "Rayo", "Rojo", "Instantáneo", "Común", "Inflige 3 puntos de daño a cualquier objetivo.", false));
            elementuak.add(new Carta("2", "Ángel de la Restauración", "Blanco", "Criatura", "Rara", "Vuela. Cuando entra al campo de batalla, puedes exiliar otra criatura que controlas.", false));

            // Otros elementos de interfaz como botones
            Button añadirCarta = findViewById(R.id.btn_agregar);
            Button editarCarta = findViewById(R.id.btn_Editar);
            Button eliminarCarta = findViewById(R.id.btn_Borrar);
            Button filtrarCarta = findViewById(R.id.btn_Filtrar);
            Button limpiarFiltro = findViewById(R.id.btn_Limpiar);
            TextView idCarta = findViewById(R.id.txtIdHint);
            TextView nombreCarta = findViewById(R.id.txtNombreHint);



            añadirCarta.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this, Registro.class);
                elementuaGehituLauncher.launch(intent); // Lanzar la actividad para agregar carta
            });


            editarCarta.setOnClickListener(view -> {
                Carta cartaEditar = null;
                for (Carta carta : elementuak) {
                    if (carta.getId().equals(idCarta.getText().toString())) {

                        cartaEditar = carta;
                        Intent intent = new Intent(MainActivity.this, Editar.class);
                        intent.putExtra("carta", Parcels.wrap(cartaEditar));
                        elementuaGehituLauncher.launch(intent); // Lanzar la actividad para agregar carta
                    }
                }

                 if (cartaEditar == null) {
                    Toast.makeText(this, "Carta no encontrada", Toast.LENGTH_SHORT).show();

                }
            });

            eliminarCarta.setOnClickListener(view -> {
               //borrar elemento del array buscando por nombre e id
                for(Carta carta : elementuak){
                    if(carta.getId().equals(idCarta.getText().toString()) || carta.getNombre().equals(nombreCarta.getText().toString())){
                        elementuak.remove(carta);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "Carta eliminada", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                idCarta.setText("");
                nombreCarta.setText("");
                Toast.makeText(this, "Carta no encontrada", Toast.LENGTH_SHORT).show();
            });
            limpiarFiltro.setOnClickListener(view -> {
                idCarta.setText("");
                nombreCarta.setText("");
            });

            filtrarCarta.setOnClickListener(view -> {
                ArrayList<Carta> filteredList = new ArrayList<>();
                for(Carta carta : elementuak){
                    if(carta.getId().equals(idCarta.getText().toString()) || carta.getNombre().equals(nombreCarta.getText().toString())){
                        filteredList.add(carta);
                    }
                }

                if (idCarta.getText().toString().equals("") && nombreCarta.getText().toString().equals("")) {
                    filteredList = new ArrayList<>(elementuak);
                }

                adapter = new ElementuaAdapter(filteredList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                if (filteredList.isEmpty()) {
                    Toast.makeText(this, "Carta no encontrada", Toast.LENGTH_SHORT).show();
                }
            });
        }

}