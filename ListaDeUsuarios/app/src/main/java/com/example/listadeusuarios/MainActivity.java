package com.example.listadeusuarios;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

    private ArrayList<Usuario> elementuak;  // Lista principal de usuarios
    private ArrayList<Usuario> elementuakFilt;  // Lista para filtrado
    private ElementuaAdapter adapter;  // Adaptador para RecyclerView
    private RecyclerView recyclerView;  // Referencia a RecyclerView


    private final ActivityResultLauncher<Intent> elementuaGehituLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Usuario usuarioActualizado = Parcels.unwrap(result.getData().getParcelableExtra("usuario_actualizado"));

                    if (usuarioActualizado != null) {
                        // Buscar y actualizar al usuario en la lista
                        for (int i = 0; i < elementuak.size(); i++) {
                            if (elementuak.get(i).getId() == usuarioActualizado.getId()) {
                                elementuak.set(i, usuarioActualizado);
                                break;
                            }
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

        // Ajustar márgenes de la vista principal para soportar barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar vistas y botones
        Button btnGehitu = findViewById(R.id.btn_Gehitu);
        Button btnAldatu = findViewById(R.id.btn_Aldatu);
        Button btnEzabatu = findViewById(R.id.btn_Ezabatu);
        Button btnIragazi = findViewById(R.id.btn_Iragazi);
        Button btnGarbitu = findViewById(R.id.btn_Garbitu);
        TextView txtId = findViewById(R.id.txtIdHint);
        TextView txtIzena = findViewById(R.id.txtIzenaHint);

        // Inicializar listas
        elementuak = new ArrayList<>();
        elementuakFilt = new ArrayList<>();

        // Añadir usuarios a la lista principal
        elementuak.add(new Usuario(1, "JonH", "1234", "Jon", "es", true));
        elementuak.add(new Usuario(2, "AneG", "1234", "Ane", "es", true));
        elementuak.add(new Usuario(3, "MikelL", "1234", "Mikel", "es", false));
        elementuak.add(new Usuario(4, "AmaiaM", "1234", "Amaia", "es", true));

        // Configurar RecyclerView y su adaptador
        recyclerView = findViewById(R.id.reciclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ElementuaAdapter(elementuak);
        recyclerView.setAdapter(adapter);

        // Botón para agregar usuarios (sin implementación completa)
        btnGehitu.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Registro.class);
            elementuaGehituLauncher.launch(intent);

        });

        // Botón para modificar usuarios (sin implementación completa)
        btnAldatu.setOnClickListener(view -> {
            int id = Integer.parseInt(txtId.getText().toString().trim());
            Usuario usuarioEditar = null;

            // Buscar el usuario por ID
            for (Usuario usuario : elementuak) {
                if (usuario.getId() == id) {
                    usuarioEditar = usuario;
                    break;
                }
            }

            if (usuarioEditar != null) {
                // Pasar el usuario a la ventana de Registro
                Intent intent = new Intent(MainActivity.this, Editar.class);
                intent.putExtra("usuario", Parcels.wrap(usuarioEditar)); // Envolver con Parcels
                elementuaGehituLauncher.launch(intent);
            } else {
                Log.d("MainActivity", "Usuario no encontrado para editar.");
            }
        });


        // Botón para eliminar usuarios (sin implementación completa)
        btnEzabatu.setOnClickListener(view -> {
            for (Usuario usuario : elementuak) {
                if (usuario.getId() == Integer.parseInt(txtId.getText().toString())) {
                    elementuak.remove(usuario);
                    break;
                }
            }
            adapter.notifyDataSetChanged();


        });

        // Botón para filtrar usuarios por nombre
        btnIragazi.setOnClickListener(view -> {
            String filtro = txtIzena.getText().toString().trim();
            elementuakFilt.clear();  // Limpiar lista filtrada

            if (!filtro.isEmpty()) {
                for (Usuario usuario : elementuak) {
                    // Filtrar por coincidencia de nombre
                    if (usuario.getNombre().toLowerCase().contains(filtro.toLowerCase())) {
                        elementuakFilt.add(usuario);
                    }
                }
                // Actualizar adaptador con la lista filtrada
                adapter = new ElementuaAdapter(elementuakFilt);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Log.d("MainActivity", "Filtrado completado: " + filtro);
            } else {
                Log.d("MainActivity", "Campo de filtro vacío. No se realizó filtrado.");
            }
        });

        // Botón para restaurar la lista original
        btnGarbitu.setOnClickListener(view -> {
            txtId.setText("");  // Limpiar texto del campo ID
            txtIzena.setText("");  // Limpiar texto del campo Nombre

            // Restaurar lista original en el adaptador
            adapter = new ElementuaAdapter(elementuak);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            Log.d("MainActivity", "Lista restaurada a su estado original.");
        });
    }
}
