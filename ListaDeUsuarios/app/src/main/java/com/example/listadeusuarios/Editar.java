package com.example.listadeusuarios;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.parceler.Parcels;

public class Editar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Usuario usuario = Parcels.unwrap(getIntent().getParcelableExtra("usuario"));



        Button btn_Gorde  = (Button) findViewById(R.id.btn_Gorde);
        Button btn_Itzuli = (Button) findViewById(R.id.btn_Itzuli);
        TextView txtId =  findViewById(R.id.v_idSartu);
        TextView txtIzena =  findViewById(R.id.v_izenaSartu);
        TextView txtUsuario =  findViewById(R.id.v_userSartu);
        TextView txtPass =  findViewById(R.id.v_passSartu);
        CheckBox activoCheck = findViewById(R.id.checkBoxActive);
        Spinner mySpinner = (Spinner) findViewById(R.id.mySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,  R.array.array_idiomas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);


        if (usuario != null){
            txtId.setText(String.valueOf(usuario.getId()));
            txtIzena.setText(usuario.getNombre());
            txtUsuario.setText(usuario.getUser());
            txtPass.setText(usuario.getPass());
            mySpinner.setSelection(adapter.getPosition(usuario.getIdioma()));
            activoCheck.setChecked(usuario.isActivo());
        }


        btn_Gorde.setOnClickListener(v -> {
            if (txtId.getText().toString().isEmpty() || txtIzena.getText().toString().isEmpty() || txtUsuario.getText().toString().isEmpty() || txtPass.getText().toString().isEmpty()) {
                Toast.makeText(Editar.this, "Eremu guztiak bete behar dira!", Toast.LENGTH_SHORT).show();
            }else {
                Usuario usuarioActualizado = new Usuario(Integer.parseInt(txtId.getText().toString()), txtIzena.getText().toString(), txtUsuario.getText().toString(), txtPass.getText().toString(), mySpinner.getSelectedItem().toString(),  activoCheck.isChecked());
                Intent intent = new Intent();
                intent.putExtra("usuario_actualizado", Parcels.wrap(usuarioActualizado));
                setResult(RESULT_OK, intent);
                Toast.makeText(Editar.this, "Datuak ondo gorde dira", Toast.LENGTH_SHORT).show();
                finish(); // Finaliza la actividad correctamente

            }
            finish();
        });
        btn_Itzuli.setOnClickListener(v -> {
            finish();
        });

    }
}