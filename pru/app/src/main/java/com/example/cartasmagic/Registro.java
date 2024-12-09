package com.example.cartasmagic;

import static com.example.cartasmagic.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.parceler.Parcels;

public class Registro extends AppCompatActivity {

    // Registro.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);

        Button btn_Enviar = findViewById(R.id.btnEnviar);
        Button btn_Cancelar = findViewById(R.id.btnCancelar);
        CheckBox chkFavorito = findViewById(R.id.chkFavorito);
        TextView txtDescripcion = findViewById(id.edtDescripcion);
        TextView txtNombre = findViewById(R.id.edtNombre);
        TextView txtId = findViewById(id.edtId); // Asegúrate de que txtId es un EditText

        // Spinner de rareza
        Spinner mySpinnerRareza = findViewById(R.id.spinnerRareza);
        ArrayAdapter<CharSequence> adapterRareza = ArrayAdapter.createFromResource(
                this, R.array.rareza, android.R.layout.simple_spinner_item);
        adapterRareza.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinnerRareza.setAdapter(adapterRareza);

        // Spinner de tipo
        Spinner mySpinnerTipo = findViewById(R.id.spinnerTipo);
        ArrayAdapter<CharSequence> adapterTipo = ArrayAdapter.createFromResource(
                this, R.array.tipos, android.R.layout.simple_spinner_item);
        adapterTipo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinnerTipo.setAdapter(adapterTipo);

        // Spinner de color
        Spinner mySpinnerColor = findViewById(R.id.spinnerColor);
        ArrayAdapter<CharSequence> adapterColor = ArrayAdapter.createFromResource(
                this, R.array.colores, android.R.layout.simple_spinner_item);
        adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinnerColor.setAdapter(adapterColor);

        // Cuando el botón de enviar es presionado
        btn_Enviar.setOnClickListener(v -> {
            // Validar si los campos de nombre y descripción están vacíos
            if (txtNombre.getText().toString().isEmpty() || txtDescripcion.getText().toString().isEmpty()) {
                txtNombre.setError("Nombre requerido");
                txtDescripcion.setError("Descripcion requerida");
            } else {


                // Crear la carta con los datos del formulario
                Carta newElementua = new Carta(txtId.getText().toString(), txtNombre.getText().toString(), mySpinnerColor.getSelectedItem().toString(),
                        mySpinnerTipo.getSelectedItem().toString(), mySpinnerRareza.getSelectedItem().toString(),
                        txtDescripcion.getText().toString(), chkFavorito.isChecked());

                // Enviar el objeto Carta de vuelta a MainActivity
                newElementua.mostrarCarta();
                Intent intent = new Intent();
                intent.putExtra("carta_actualizada", Parcels.wrap(newElementua)); // Pasar el objeto como un Parcelable
                setResult(RESULT_OK, intent);

                // Finalizar la actividad
                finish();
            }
        });

        // Botón cancelar (puedes agregar lo que quieras que haga)
        btn_Cancelar.setOnClickListener(v -> finish(


        ));
    }
}