package com.example.pm2e10033;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class VercontactoActivity extends AppCompatActivity {

    Button btnAtras, btnEliminar, btnActualizar;
    long selectedPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vercontacto);
        ListView listView = findViewById(R.id.listView);
        Context contexto = this;
        DatabaseHelper db = new DatabaseHelper(contexto);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Guarda la posición en una variable global
                selectedPosition = position;
                Contacto contacto = (Contacto) listView.getItemAtPosition(position);

                // Obtén el ID del contacto
                long contactoId = contacto.getId();

                // Muestra el ID, por ejemplo, en un Toast
                Toast.makeText(getApplicationContext(), "ID del contacto: " + contactoId, Toast.LENGTH_SHORT).show();
            }
        });

        btnAtras = (Button) findViewById(R.id.btnBack);
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VercontactoActivity.super.onBackPressed();
            }
        });



        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Contacto contacto = (Contacto) listView.getItemAtPosition((int) selectedPosition);

                db.eliminarContacto((contacto.getId()));
                ListarContactos(db);
            }
        });

        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para abrir la actividad de actualización
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                Contacto contacto = (Contacto) listView.getItemAtPosition((int) selectedPosition);

                Toast.makeText(getApplicationContext(), "ID actualizar es: " + contacto.getId(), Toast.LENGTH_SHORT).show();
                // Pasar los datos del contacto al Intent
                intent.putExtra("id", contacto.getId());
                intent.putExtra("nombre", contacto.getNombre());
                intent.putExtra("telefono", contacto.getTelefono());
                intent.putExtra("nota", contacto.getNota());

                // Iniciar la actividad de actualización
                startActivity(intent);
            }
        });

        ListarContactos(db);
    }

    public interface onContactoDeleteListener
    {
        void onContactoDelete();
    }

    private void ListarContactos(DatabaseHelper dbhelper) {
        List<Contacto> listaDeDatos = dbhelper.obtenerContactos();
        ArrayAdapter<Contacto> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaDeDatos);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adaptador);
    }

}