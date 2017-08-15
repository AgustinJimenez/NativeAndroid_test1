package com.example.agus.androidtest1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Modificar extends AppCompatActivity
{

    EditText et_nombre, et_apellido;
    Button btn_actualizar, btn_eliminar;
    int id;
    String nombre, apellido;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);


        Bundle params = getIntent().getExtras();
        if( params != null)
        {
            id = params.getInt("id");
            nombre = params.getString("nombre");
            apellido = params.getString("apellido");
        }

        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_apellido = (EditText) findViewById(R.id.et_apellido);
        btn_actualizar = (Button) findViewById(R.id.btn_actualizar);
        btn_eliminar = (Button) findViewById(R.id.btn_eliminar);

        et_nombre.setText(nombre);
        et_apellido.setText(apellido);

        btn_actualizar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Modificar(id, et_nombre.getText().toString(), et_apellido.getText().toString() );
                onBackPressed();

            }
        });


        btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Eliminar( id );
                onBackPressed();
            }
        });



    }

    private void Modificar (int id, String nombre, String apellido)
    {
        String db_name = "db_testing";
        Integer version = 1;
        String table_name = "personas";

        DBHelper db_helper = new DBHelper( this, db_name, null, version );
        SQLiteDatabase db = db_helper.getWritableDatabase();

        String sql = "UPDATE " + table_name + " SET nombre = '" + nombre + "', apellido = '" + apellido + "' WHERE id = "+id;
        db.execSQL( sql );
        db.close();
    }


    private void Eliminar (int id)
    {
        String db_name = "db_testing";
        Integer version = 1;
        String table_name = "personas";

        DBHelper db_helper = new DBHelper( this, db_name, null, version );
        SQLiteDatabase db = db_helper.getWritableDatabase();

        String sql = "DELETE FROM "+table_name+" WHERE id = "+id;
        db.execSQL( sql );
        db.close();
    }






}
