package com.example.pm2e10033;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText txtNombre;
    private EditText txtTelefono;
    private EditText txtNota;
    Button btnGuardar, btnVerContactos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);

        // Obtener datos del Intent
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        String nombre = intent.getStringExtra("nombre");
        String telefono = intent.getStringExtra("telefono");
        String nota = intent.getStringExtra("nota");

        // Verificar si los valores son nulos o vacíos y establecer valores predeterminados en su lugar
        if (nombre == null || nombre.isEmpty()) {
            nombre = "";
        }
        if (telefono == null || telefono.isEmpty()) {
            telefono = "";
        }
        if (nota == null || nota.isEmpty()) {
            nota = "";
        }

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtNota = findViewById(R.id.txtNota);

        txtNombre.setText(nombre);
        txtTelefono.setText(telefono);
        txtNota.setText(nota);

        btnGuardar = (Button) findViewById(R.id.btnGuardarContacto);
        btnVerContactos = (Button) findViewById(R.id.btnVerContactos);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString();
                String telefono = txtTelefono.getText().toString();
                String nota = txtNota.getText().toString();

                Contacto nuevoContacto = new Contacto(nombre, telefono, nota );

                // Verificar si los valores son nulos o vacíos y establecer valores predeterminados en su lugar
                if (nombre == null || nombre.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Debe escribir un nombre", Toast.LENGTH_SHORT).show();
                }else
                if (telefono == null || telefono.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Debe escribir un telefono", Toast.LENGTH_SHORT).show();
                }else
                if (nota == null || nota.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Debe escribir una nota", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "el id es "+ id, Toast.LENGTH_SHORT).show();
                    if(id == -1){
                        int id = (int) dbHelper.insertarContacto(nuevoContacto);
                    }else{
                        int id = (int) dbHelper.actualizarContacto(nuevoContacto);
                    }
                }
            }
        });

        btnVerContactos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VercontactoActivity.class);

                startActivity(intent);
            }
        });

    }
}