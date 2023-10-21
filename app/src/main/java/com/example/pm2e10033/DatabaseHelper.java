package com.example.pm2e10033;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contactos", NOMBRE_TABLA ="contacto";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(String.format("CREATE TABLE IF NOT EXISTS %s(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, telefono TEXT, nota TEXT, imagen BLOB)", NOMBRE_TABLA));
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertarContacto(Contacto contacto) {
        SQLiteDatabase baseDeDatos = this.getWritableDatabase();

        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre", contacto.getNombre());
        valoresParaInsertar.put("telefono", contacto.getTelefono());
        valoresParaInsertar.put("nota", contacto.getNota());

        long id = baseDeDatos.insert(NOMBRE_TABLA, null, valoresParaInsertar);

        baseDeDatos.close();

        return id;
    }

    public long actualizarContacto(Contacto contacto) {
        SQLiteDatabase baseDeDatos = this.getWritableDatabase();

        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("nombre", contacto.getNombre());
        valoresParaActualizar.put("telefono", contacto.getTelefono());
        valoresParaActualizar.put("nota", contacto.getNota());

        long id = baseDeDatos.update(NOMBRE_TABLA, valoresParaActualizar, "id=?", new String[] { String.valueOf(contacto.getId()) });

        baseDeDatos.close();

        return id;
    }

    public long eliminarContacto(long identificador) {
        SQLiteDatabase baseDeDatos = this.getWritableDatabase();

        long id = baseDeDatos.delete(NOMBRE_TABLA, "id=?", new String[] { String.valueOf(identificador) });

        baseDeDatos.close();

        return id;
    }

    public ArrayList<Contacto> obtenerContactos() {
        ArrayList<Contacto> Contactos = new ArrayList<>();


        SQLiteDatabase baseDeDatos = this.getReadableDatabase();

        String[] columnasAConsultar = {"id","nombre", "telefono", "nota"};
        Cursor cursor = baseDeDatos.query(
                NOMBRE_TABLA,
                columnasAConsultar,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor == null) {

            return Contactos;

        }

        if (!cursor.moveToFirst()) return Contactos;


        do {
            long id = cursor.getLong(0);
            String nombreObtenidoDeBD = cursor.getString(1);
            String telefonoObtenidoDeBD = cursor.getString(2);
            String NotaObtenidoDeBD = cursor.getString(3);
            Contacto ContactosObtenidos = new Contacto(nombreObtenidoDeBD, telefonoObtenidoDeBD, NotaObtenidoDeBD, id);
            Contactos.add(ContactosObtenidos);
        } while (cursor.moveToNext());

        cursor.close();
        return Contactos;
    }

}
