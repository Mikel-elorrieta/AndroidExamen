package com.example.loginregistro;

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
import java.util.ArrayList;

public class Registro extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnRegistro = findViewById(R.id.buttonRegistr);
        Button btnCancel = findViewById(R.id.buttonCancelar);
        TextView txtUser = findViewById(R.id.editTextUser);
        TextView txtPass = findViewById(R.id.editTextPassword);
        Spinner mySpinner = findViewById(R.id.spinner);

        //PONER EL TEXTO DEL ARRAY EN EL SPINNER
        ArrayAdapter<CharSequence>arrayAdapter = ArrayAdapter.createFromResource(this , R.array.Generos , android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(arrayAdapter);
        //PONER EL TEXTO DEL ARRAY EN EL SPINNER


        CheckBox myCheckBox = findViewById(R.id.checkBox);




        ArrayList <Usuario> usuarios = Parcels.unwrap(getIntent().getParcelableExtra("elementuak"));

        System.out.println(usuarios);


        btnRegistro.setOnClickListener(View->{

            if(txtUser.getText().toString().isEmpty()){
                Toast.makeText(this, "Falta el USUARIO ", Toast.LENGTH_SHORT).show();

            } else if (txtPass.getText().toString().isEmpty()) {
                Toast.makeText(this, "Falta la CONTRASEÑA  ", Toast.LENGTH_SHORT).show();

            }else if (mySpinner.getSelectedItem().toString().isEmpty()) {
                Toast.makeText(this, "Falta el GENERO  ", Toast.LENGTH_SHORT).show();

            }else{

                for (Usuario usu : usuarios){
                    if (usu.getUsername().equals(txtUser.getText().toString())){
                        Toast.makeText(this, "ERROR !!! Usuario YA REGISTRADO", Toast.LENGTH_SHORT).show();
                        txtUser.setText("");
                        txtPass.setText("");

                        return;
                    }
                }

                Usuario usuario = new Usuario("ª", txtUser.getText().toString(), txtPass.getText().toString(),mySpinner.getSelectedItem().toString() , myCheckBox.isChecked());
                Intent intent = new Intent();
                intent.putExtra("Usuarionuevo", Parcels.wrap(usuario));
                Toast.makeText(this, "Usuario REGISTRADO", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK, intent);
                finish();
            }






        } );
        btnCancel.setOnClickListener(View->{
            txtUser.setText("");
            txtPass.setText("");
            Toast.makeText(this, "CANCELADO", Toast.LENGTH_SHORT).show();
            finish();


        });

    }
}