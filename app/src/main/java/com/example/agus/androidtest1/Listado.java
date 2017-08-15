package com.example.agus.androidtest1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Listado extends AppCompatActivity
{
    ListView lv_lista;
    ArrayList<String> listado;

    @Override
    protected void onPostResume()
    {
        super.onPostResume();
        CargarListado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        lv_lista = (ListView) findViewById(R.id.lv_lista);
        CargarListado();


        lv_lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                Toast.makeText( Listado.this, listado.get( position ), Toast.LENGTH_SHORT ).show();
                int key = Integer.parseInt( listado.get( position ).split( " " )[0] );
                String nombre = listado.get( position ).split( " " )[1];
                String apellido = listado.get( position ).split( " " )[2];
                Intent intent = new Intent ( Listado.this, Modificar.class);
                intent.putExtra("id", key);
                intent.putExtra("nombre", nombre);
                intent.putExtra("apellido", apellido);
                startActivity( intent );
            }
        });


        if( getSupportActionBar() != null )
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled( true );
            getSupportActionBar().setDisplayShowHomeEnabled( true );
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if( item.getItemId() == android.R.id.home )
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void CargarListado()
    {
        listado = ListaPersonas();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listado);
        lv_lista.setAdapter( adaptador );

    }

    private ArrayList<String> ListaPersonas()
    {
        ArrayList<String> datos = new ArrayList<String>();
        DBHelper db_helper = new DBHelper(this, "db_testing", null, 1);
        SQLiteDatabase db = db_helper.getReadableDatabase();
        String sql = "SELECT id, nombre, apellido FROM personas";
        Cursor c = db.rawQuery(sql, null);

        if( c.moveToFirst() )
            do
            {
                String linea = c.getInt(0) +" "+ c.getString(1)+" "+c.getString(2);
                datos.add(linea);
            }
            while( c.moveToNext() );

        db.close();
        return datos;
    }


























}
