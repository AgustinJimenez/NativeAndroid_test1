package com.example.agus.androidtest1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.attr.id;

/**
 * Created by agus on 14/08/17.
 */

public class DBHelper extends SQLiteOpenHelper
{
    /*CONFIG*/
    String database = "testing_database1";
    String table = "personas";

    public void setFields()
    {
        fields_array.put("id", "INTEGER PRIMARY KEY AUTOINCREMENT");
        fields_array.put("nombre", "TEXT");
        fields_array.put("apellido", "TEXT");
    }
    /*END-CONFIG*/


    Map<String, String> fields_array = new HashMap<String, String>();
    String initsql;

    //CONSTRUCTOR
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
        run();
    }
    /*INIT QUERY METHODS*/

    public void get(String [] requested_fields )
    {
        String query = "SELECT ";
    }

    /*END QUERY METHODS*/

    public void run()
    {
        setFields();
        createInitSql();

    }

    public void createInitSql()
    {
        String fields_string_type = "";
        for( Map.Entry<String, String> field : fields_array.entrySet() )
            fields_string_type += field.getKey() + " " + field.getValue() + ", ";

        fields_string_type = remove_lasts_characters( fields_string_type );

        initsql = "CREATE TABLE "+table+"( "+fields_string_type+" )";
    }



    public ArrayList<String> array_fields()
    {
        ArrayList<String> tmp = new ArrayList<String>();

        for( Map.Entry<String, String> field : fields_array.entrySet() )
            tmp.add( field.getKey() );

        return tmp;
    }

    public String string_fields()
    {
        String tmp = "";

        for( Map.Entry<String, String> field : fields_array.entrySet() )
            tmp += field.getKey() + ", ";



        return remove_lasts_characters( tmp );
    }

    public String remove_lasts_characters( String value )
    {
        return value.substring( 0 , value.length() - 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL( initsql );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE "+table);
    }





}
