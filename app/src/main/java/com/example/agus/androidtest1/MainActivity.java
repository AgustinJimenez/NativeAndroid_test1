package com.example.agus.androidtest1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    EditText et_nombre, et_apellido;
    Button btn_guardar, btn_mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et_nombre = (EditText) findViewById(R.id.et_nombre);
        et_apellido = (EditText) findViewById(R.id.et_apellido);
        btn_guardar = (Button) findViewById(R.id.btn_guardar);
        btn_mostrar = (Button) findViewById(R.id.btn_mostrar);

        btn_guardar.setOnClickListener( new View.OnClickListener()
        {
            public void onClick( View v )
            {
                guardar( et_nombre.getText().toString(), et_apellido.getText().toString() );
            }
        });

        btn_mostrar.setOnClickListener( new View.OnClickListener()
        {
            public void onClick( View v )
            {
                startActivity( new Intent( MainActivity.this, Listado.class ) );
            }
        });

    }

    private void guardar( String nombre, String apellido )
    {
        String db_name = "db_testing";
        Integer version = 1;
        String table_name = "personas";

        DBHelper db_helper = new DBHelper( this, db_name, null, version );
        SQLiteDatabase db = db_helper.getWritableDatabase();

        try
        {
            ContentValues container = new ContentValues();
            container.put("nombre", nombre);
            container.put("apellido", apellido);
            db.insert(table_name, null, container);
            db.close();
            Toast.makeText( this, "Registro guardado correctamente.", Toast.LENGTH_SHORT ).show();

        }
        catch (Exception e)
        {
            Toast.makeText(this, "ERROR: "+e.getMessage() , Toast.LENGTH_SHORT).show();
        }


    }











}
