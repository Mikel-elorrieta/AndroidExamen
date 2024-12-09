package com.example.loginregistro;

import android.content.Intent;
import android.os.Bundle;
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
    private ArrayList<Usuario> elementuak = new ArrayList<>();  // Inicializada correctamente
    private ArrayList<Usuario> elementuakFilt = new ArrayList<>();  // Inicializada correctamente
   private ElementAdapter adapter;  // Adaptador para RecyclerView
    private RecyclerView recyclerView;
    private final ActivityResultLauncher<Intent> elementuaGehituLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {

                    Usuario usuario = Parcels.unwrap(result.getData().getParcelableExtra("Usuarionuevo"));
                    elementuak.add(usuario);
                    System.out.println(usuario);
                    adapter.notifyDataSetChanged();

                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnRegistro = findViewById(R.id.buttonRegistr);
        Button btnLogin = findViewById(R.id.buttonCancelar);
        TextView txtUser = findViewById(R.id.editTextUser);
        TextView txtPass = findViewById(R.id.editTextPassword);

        elementuak.add(new Usuario("1", "Jons", "Jons", "Masculino", true));
        elementuak.add(new Usuario("2", "Antonio", "Antonios", "Masculino", true));
        elementuak.add(new Usuario("3", "Jesus", "Jesuss", "Masculino", true));
        elementuak.add(new Usuario("4", "Paco", "Pacos", "Masculino", false));
        elementuak.add(new Usuario("5", "Maria", "Marias", "Femenino", true));
        elementuak.add(new Usuario("6", "Ana", "Anas", "Femenino", false));
        elementuak.add(new Usuario("7", "Laura", "Lauras", "Femenino", true));
        elementuak.add(new Usuario("8", "Carlos", "Carlos", "Masculino", true));
        elementuak.add(new Usuario("9", "Lucia", "Lucias", "Femenino", true));
        elementuak.add(new Usuario("10", "Pedro", "Pedros", "Masculino", false));

        // Configurar RecyclerView y su adaptador
        recyclerView = findViewById(R.id.reciclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ElementAdapter(elementuak);
        recyclerView.setAdapter(adapter);
        btnRegistro.setOnClickListener(View->{

            Intent intent = new Intent(MainActivity.this , Registro.class);
            intent.putExtra("elementuak", Parcels.wrap(elementuak));
            elementuaGehituLauncher.launch(intent);


        } );
        btnLogin.setOnClickListener(View->{


            for(Usuario usuario : elementuak){
                if (usuario.getUsername().equals(txtUser.getText().toString()) && usuario.getPassword().equals( txtPass.getText().toString())){
                    Toast.makeText(this, "Usuario Encontrado", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            Toast.makeText(this, "Usuario NO Encontrado", Toast.LENGTH_SHORT).show();

        });

    }
}