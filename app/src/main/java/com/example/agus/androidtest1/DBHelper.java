package com.example.agus.androidtest1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by agus on 14/08/17.
 */

public class DBHelper extends SQLiteOpenHelper
{
    String table = "CREATE TABLE personas( ID INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT )";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE personas");
    }
}
