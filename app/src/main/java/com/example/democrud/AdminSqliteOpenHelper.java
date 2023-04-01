package com.example.democrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSqliteOpenHelper  extends SQLiteOpenHelper {

    private final String sql = "CREATE TABLE articulos(codigo int primary key, descripcion text, precio real, cantidad integer)";
    // private final String sql_clientes = "CREATE TABLE clientes(codigo int primary key, nombre text, direccion text)";

    public AdminSqliteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sql);

        //sqLiteDatabase.execSQL(sql_clientes);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS articulos");
        onCreate(sqLiteDatabase);
    }

}
